package ro.fasttrackit.productsclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductsClientApplication

fun main(args: Array<String>) {
    runApplication<ProductsClientApplication>(*args)
}
