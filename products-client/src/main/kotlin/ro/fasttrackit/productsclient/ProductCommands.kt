package ro.fasttrackit.productsclient

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ro.fasttrackit.productsclient.model.Product
import ro.fasttrackit.productsclient.model.ProductCategory.ELECTRONICS
import ro.fasttrackit.productsclient.service.ProductService
import java.util.*


@ShellComponent
class ProductCommands(private val productService: ProductService) {

    @ShellMethod("Print all products")
    fun printAllProducts() = productService.getAllProducts()?.forEach { println(it) }

    @ShellMethod("Filter products by price")
    fun priceFilteredProducts() {
        print("maxPrice: ")
        val scanner = Scanner(System.`in`)
        val maxPrice = scanner.nextDouble()
        productService.filterProductsByPrice(maxPrice)?.forEach { println(it) }
    }

    @ShellMethod("Filter products by category")
    fun categoryFilteredProducts() {
        print("category name: ")
        val scanner = Scanner(System.`in`)
        val category = scanner.next()
        productService.filterProductsByCategory(category)?.forEach { println(it) }
    }

    @ShellMethod("Get product by id")
    fun getProduct() {
        print("id: ")
        val scanner = Scanner(System.`in`)
        val lookupId = scanner.nextLong()
        println(productService.getProductById(lookupId))
    }

    @ShellMethod("Add new product")
    fun addProduct() {
        println(
            productService.addProduct(
                Product(
                    id = 100,
                    name = "Toaster",
                    price = 150.50,
                    description = "Transparent toaster",
                    productCategory = ELECTRONICS
                )
            )
        )
    }

    @ShellMethod("Delete product")
    fun deleteProduct() {
        print("id: ")
        val scanner = Scanner(System.`in`)
        val lookupId = scanner.nextLong()
        println(productService.deleteProduct(lookupId))
    }
}
