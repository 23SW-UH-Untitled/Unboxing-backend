package team.untitled.unboxingBackend.domain.product.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import team.untitled.unboxingBackend.domain.product.controller.data.req.CreateProductReqData
import team.untitled.unboxingBackend.domain.product.controller.data.req.QueryDetailProductReqData
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryDetailProductResData
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryProductListResData
import team.untitled.unboxingBackend.domain.product.service.ProductService

@RestController
@RequestMapping("/product")
class ProductController (
    private val productService: ProductService
){
    @GetMapping
    fun queryAllProduct(): ResponseEntity<List<QueryProductListResData>> =
        productService.queryProductListService()
            .let { ResponseEntity.ok(it) }

    @GetMapping("/detail")
    fun queryDetailProduct(@RequestBody queryDetailProductReqData: QueryDetailProductReqData): ResponseEntity<QueryDetailProductResData> =
        productService.queryDetailProductService(queryDetailProductReqData)
            .let { ResponseEntity.ok(it) }

    @PostMapping
    fun createProduct(@RequestBody createProductReqData: CreateProductReqData): ResponseEntity<Void> =
        productService.createProductService(createProductReqData)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }


}