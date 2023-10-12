package team.untitled.unboxingBackend.domain.product.controller.data.res

data class QueryProductListResData(
    val id: Long,
    val profile: String,
    val inventory: Int,
    val name: String,
)
