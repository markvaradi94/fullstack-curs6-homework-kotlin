package ro.fasttrackit.productsclient.service

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod.GET
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForObject
import org.springframework.web.util.UriComponentsBuilder
import ro.fasttrackit.productsclient.model.Product

@Service
class ProductService {

    companion object {
        const val PRODUCTS_URL = "http://localhost:8081/products"
    }

    fun getAllProducts(): List<Product>? {
        val restTemplate = RestTemplate()
        return restTemplate.exchange(
            PRODUCTS_URL,
            GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<Product>>() {}
        ).body
    }

    fun filterProductsByPrice(maxPrice: Double): List<Product>? {
        val restTemplate = RestTemplate()
        val builder = UriComponentsBuilder.fromHttpUrl(PRODUCTS_URL)
            .queryParam("maxPrice", maxPrice)
        return restTemplate.exchange(
            builder.toUriString(),
            GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<Product>>() {}
        ).body
    }

    fun filterProductsByCategory(categoryName: String): List<Product>? {
        val restTemplate = RestTemplate()
        val builder = UriComponentsBuilder.fromHttpUrl(PRODUCTS_URL)
            .queryParam("category", categoryName)
        return restTemplate.exchange(
            builder.toUriString(),
            GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<Product>>() {}
        ).body
    }

    fun getProductById(productId: Long): Product {
        val restTemplate = RestTemplate()
        return restTemplate.getForObject("$PRODUCTS_URL/$productId", Product::class)
    }

    fun addProduct(product: Product): Product {
        val restTemplate = RestTemplate()
        return restTemplate.postForObject(PRODUCTS_URL, product, Product::class)
    }

    fun deleteProduct(productId: Long): Product {
        val restTemplate = RestTemplate()
        val productToDelete = getProductById(productId)
        restTemplate.delete("$PRODUCTS_URL/$productId", Product::class)
        return productToDelete
    }
}
