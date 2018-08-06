package kotlinddd.web.models


data class AddProductRequest(
        var productId: String = "",
        var quantity: Int = 0
)