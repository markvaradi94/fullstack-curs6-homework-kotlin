package ro.fasttrackit.productsapp.bootstrap

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ro.fasttrackit.productsapp.domain.Product
import ro.fasttrackit.productsapp.domain.ProductCategory.*
import ro.fasttrackit.productsapp.service.ProductService

@Component
class DataLoader(private val productService: ProductService) : CommandLineRunner {
    override fun run(vararg args: String?) {
        productService.addProduct(
            Product(
                name = "TV",
                price = 2299.50,
                description = "Big and shiny",
                productCategory = ELECTRONICS
            )
        )
        productService.addProduct(
            Product(
                name = "Apples",
                price = 2.50,
                description = "Tasty",
                productCategory = FOOD
            )
        )
        productService.addProduct(
            Product(
                name = "HAMMER",
                price = 29.13,
                description = "Handy",
                productCategory = DIY
            )
        )
        productService.addProduct(
            Product(
                name = "SOFA",
                price = 1199.50,
                description = "Big and comfy",
                productCategory = FURNITURE
            )
        )
        productService.addProduct(
            Product(
                name = "PC",
                price = 5299.50,
                description = "Fast",
                productCategory = ELECTRONICS
            )
        )
        productService.addProduct(
            Product(
                name = "Radio",
                price = 299.50,
                description = "Portable",
                productCategory = ELECTRONICS
            )
        )
    }

}
