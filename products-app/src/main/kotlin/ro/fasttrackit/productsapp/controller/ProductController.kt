package ro.fasttrackit.productsapp.controller

import org.springframework.web.bind.annotation.*
import ro.fasttrackit.productsapp.domain.Product
import ro.fasttrackit.productsapp.exceptions.ProductNotFoundException
import ro.fasttrackit.productsapp.service.ProductService

@RestController
@RequestMapping("products")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun getProducts(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) maxPrice: Double?
    ): List<Product> = productService.getProducts(category, maxPrice)

    @GetMapping("{productId}")
    fun getProduct(@PathVariable productId: Long): Product = productService.getProduct(productId)
        .orElseThrow { ProductNotFoundException("Could not find product") }

    @PostMapping
    fun addProduct(@RequestBody product: Product): Product = productService.addProduct(product)

    @DeleteMapping("{productId}")
    fun removeProduct(@PathVariable productId: Long): Product = productService.deleteProduct(productId)
        .orElseThrow { ProductNotFoundException("Could not find product") }
}
