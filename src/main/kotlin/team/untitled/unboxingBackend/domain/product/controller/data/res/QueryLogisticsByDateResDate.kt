package team.untitled.unboxingBackend.domain.product.controller.data.res

data class QueryLogisticsByDateResDate(
    var inventory: Int = 0,
    var wholesaleCount: Int = 0,
    var retailCount: Int = 0,
    val product: MutableList<Product> = mutableListOf()
){
    data class Product(
        val wholesaleCount: Int,
        val retailCount: Int,
        val name: String,
        val profile: String
    )
}
