package ro.fasttrackit.productsclient.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Product(
    @JsonProperty var id: Long,
    @JsonProperty var name: String,
    @JsonProperty var price: Double,
    @JsonProperty var description: String,
    @JsonProperty var productCategory: ProductCategory
)

enum class ProductCategory {
    DIY,
    ELECTRONICS,
    FOOD,
    FURNITURE;
}
