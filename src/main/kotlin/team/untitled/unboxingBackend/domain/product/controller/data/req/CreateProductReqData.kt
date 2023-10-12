package team.untitled.unboxingBackend.domain.product.controller.data.req

import org.springframework.web.multipart.MultipartFile

data class CreateProductReqData(
    val name: String,
    val barcode: String,
    val wholesalePrice: Int,
    val retailPrice: Int,
    val profile: String
)
