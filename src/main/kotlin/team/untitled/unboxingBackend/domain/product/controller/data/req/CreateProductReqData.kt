package team.untitled.unboxingBackend.domain.product.controller.data.req

data class CreateProductReqData(
    val name: String,
    val barcode: String,
    val wholesalePrice: Int,
    val retailPrice: Int,
    val profile: String
)
