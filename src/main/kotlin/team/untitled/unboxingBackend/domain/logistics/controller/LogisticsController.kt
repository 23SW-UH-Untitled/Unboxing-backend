package team.untitled.unboxingBackend.domain.logistics.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.untitled.unboxingBackend.domain.product.controller.data.req.QueryProductByDateReqData
import team.untitled.unboxingBackend.domain.product.controller.data.req.RetailProductReqData
import team.untitled.unboxingBackend.domain.product.controller.data.req.WholesaleProductReqData
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryProductByDateResDate
import team.untitled.unboxingBackend.domain.product.service.ProductService

@RestController
@RequestMapping("/logistics")
class LogisticsController (
    private val productService: ProductService
){
    @GetMapping
    fun queryLogisticsByDate(queryProductByDateReqData: QueryProductByDateReqData): ResponseEntity<List<QueryProductByDateResDate>> =
        productService.queryProductByDateService(queryProductByDateReqData)
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/wholesale")
    fun updateWholesale(wholesaleProductReqData: WholesaleProductReqData): ResponseEntity<Void> =
        productService.wholesaleProductService(wholesaleProductReqData)
            .let { ResponseEntity.status(HttpStatus.OK).build() }

    @PatchMapping("/retail")
    fun updateRetail(retailProductReqData: RetailProductReqData): ResponseEntity<Void> =
        productService.retailProductService(retailProductReqData)
            .let { ResponseEntity.status(HttpStatus.OK).build() }

}