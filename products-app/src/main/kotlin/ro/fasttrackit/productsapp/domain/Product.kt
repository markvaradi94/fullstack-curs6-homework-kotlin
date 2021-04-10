package ro.fasttrackit.productsapp.domain

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

@Entity
class Product(
    @JsonProperty var name: String,
    @JsonProperty var price: Double,
    @JsonProperty var description: String,
    @JsonProperty var productCategory: ProductCategory
) {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long = 0
}

enum class ProductCategory {
    DIY,
    ELECTRONICS,
    FOOD,
    FURNITURE;

    companion object {
        fun getCategoryByName(categoryName: String) = values()
            .first { it.name.equals(categoryName, ignoreCase = true) }
    }
}
