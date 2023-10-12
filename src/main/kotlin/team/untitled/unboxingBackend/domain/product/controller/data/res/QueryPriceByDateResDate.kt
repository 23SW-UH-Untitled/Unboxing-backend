package team.untitled.unboxingBackend.domain.product.controller.data.res

data class QueryPriceByDateResDate(
    var price: Int = 0,
    var wholesalePrice: Int = 0,
    var retailPrice: Int = 0,
    val product: MutableList<Product> = mutableListOf()
){
    data class Product(
        val wholesalePrice: Int,
        val retailPrice: Int,
        val name: String,
        val profile: String
    )
}
