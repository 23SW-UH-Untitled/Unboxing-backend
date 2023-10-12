package team.untitled.unboxingBackend.domain.logistics.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.untitled.unboxingBackend.domain.product.controller.data.req.QueryByDateReqData
import team.untitled.unboxingBackend.domain.product.controller.data.req.RetailProductReqData
import team.untitled.unboxingBackend.domain.product.controller.data.req.WholesaleProductReqData
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryLogisticsByDateResDate
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryPriceByDateResDate
import team.untitled.unboxingBackend.domain.product.service.ProductService

@RestController
@RequestMapping("/logistics")
class LogisticsController (
    private val productService: ProductService
){
    @GetMapping
    fun queryLogisticsByDate(@RequestBody queryByDateReqData: QueryByDateReqData): ResponseEntity<QueryLogisticsByDateResDate> =
        productService.queryLogisticsByDateService(queryByDateReqData)
            .let { ResponseEntity.ok(it) }
    @GetMapping("/price")
    fun queryPriceByDate(@RequestBody queryByDateReqData: QueryByDateReqData): ResponseEntity<QueryPriceByDateResDate> =
        productService.queryPriceByDateService(queryByDateReqData)
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/wholesale")
    fun updateWholesale(@RequestBody wholesaleProductReqData: WholesaleProductReqData): ResponseEntity<Void> =
        productService.wholesaleProductService(wholesaleProductReqData)
            .let { ResponseEntity.status(HttpStatus.OK).build() }

    @PatchMapping("/retail")
    fun updateRetail(@RequestBody retailProductReqData: RetailProductReqData): ResponseEntity<Void> =
        productService.retailProductService(retailProductReqData)
            .let { ResponseEntity.status(HttpStatus.OK).build() }

}