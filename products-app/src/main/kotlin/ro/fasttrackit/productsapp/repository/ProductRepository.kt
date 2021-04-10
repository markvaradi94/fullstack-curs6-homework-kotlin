package ro.fasttrackit.productsapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import ro.fasttrackit.productsapp.domain.Product
import ro.fasttrackit.productsapp.domain.ProductCategory

interface ProductRepository : JpaRepository<Product, Long> {

    fun findAllByProductCategory(category: ProductCategory): List<Product>

    fun findAllByPriceIsLessThanEqual(maxPrice: Double): List<Product>

    fun findAllByProductCategoryAndPriceIsLessThanEqual(category: ProductCategory, maxPrice: Double): List<Product>
}
