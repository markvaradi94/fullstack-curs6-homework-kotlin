package ro.fasttrackit.productsapp.service

import org.springframework.stereotype.Service
import ro.fasttrackit.productsapp.domain.Product
import ro.fasttrackit.productsapp.domain.ProductCategory
import ro.fasttrackit.productsapp.repository.ProductRepository
import java.util.*

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getProducts(categoryName: String?, maxPrice: Double?): List<Product> {
        return checkRequestParams(categoryName, maxPrice)
    }

    fun getProduct(productId: Long): Optional<Product> = productRepository.findById(productId)

    fun addProduct(product: Product) = productRepository.save(product)

    fun deleteProduct(productId: Long): Optional<Product> {
        val productToDelete = getProduct(productId)
        productRepository.deleteById(productId)
        return productToDelete
    }

    private fun getAllProducts(): List<Product> = productRepository.findAll().toList()

    private fun filterProductByPrice(maxPrice: Double) = productRepository.findAllByPriceIsLessThanEqual(maxPrice)

    private fun filterProductsByCategory(categoryName: String) = findCategoryAndFilter(categoryName)

    private fun filterProductsByCategoryAndPrice(categoryName: String, maxPrice: Double): List<Product> {
        val productCategory = ProductCategory.getCategoryByName(categoryName)
        return productRepository.findAllByProductCategoryAndPriceIsLessThanEqual(productCategory, maxPrice)
    }

    private fun findCategoryAndFilter(categoryName: String): List<Product> {
        val productCategory = ProductCategory.getCategoryByName(categoryName)
        return productRepository.findAllByProductCategory(productCategory)
    }

    private fun checkRequestParams(categoryName: String?, maxPrice: Double?): List<Product> {
        return if (categoryName == null && maxPrice == null) getAllProducts()
        else if (categoryName != null && maxPrice == null) filterProductsByCategory(categoryName)
        else if (categoryName == null && maxPrice != null) filterProductByPrice(maxPrice)
        else filterProductsByCategoryAndPrice(categoryName!!, maxPrice!!)
    }
}
