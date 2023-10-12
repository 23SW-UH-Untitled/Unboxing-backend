package team.untitled.unboxingBackend.domain.product.service

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import team.untitled.unboxingBackend.domain.product.controller.data.req.*
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryDetailProductResData
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryLogisticsByDateResDate
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryPriceByDateResDate
import team.untitled.unboxingBackend.domain.product.controller.data.res.QueryProductListResData
import team.untitled.unboxingBackend.domain.product.domain.entity.Product
import team.untitled.unboxingBackend.domain.product.domain.entity.Wholesale
import team.untitled.unboxingBackend.domain.product.domain.repository.ProductRepository
import team.untitled.unboxingBackend.domain.user.User
import team.untitled.unboxingBackend.domain.user.repo.UserRepo
import team.untitled.unboxingBackend.global.exception.UntitledException
import team.untitled.unboxingBackend.global.s3.S3Util
import java.io.File
import java.time.LocalDate
import java.time.ZoneId


@Service
class ProductService (
    private val s3Util: S3Util,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepo
    ){
    fun createProductService(createProductReqData: CreateProductReqData){
        val user: User = queryCurrentUser()
        val product = Product(
            0,
            createProductReqData.name,
            createProductReqData.barcode,
            createProductReqData.profile,
            0,
            createProductReqData.wholesalePrice,
            createProductReqData.retailPrice,
            user = user
        )
        productRepository.save(product)
    }

    fun queryProductListService(): List<QueryProductListResData>{
        val user: User = queryCurrentUser()
        return user.Product
            .map {
                QueryProductListResData(
                    it.id,
                    it.profile,
                    it.inventory,
                    it.name
                )
            }
    }

    fun wholesaleProductService(wholesaleProductReqData: WholesaleProductReqData) {
        val product = productRepository.findById(wholesaleProductReqData.id).get()
        product.wholesale.add(Wholesale(
            0,
            LocalDate.now(ZoneId.of("Asia/Seoul")),
            wholesaleProductReqData.count
        ))
        product.inventory += wholesaleProductReqData.count
        productRepository.save(product)
    }

    fun retailProductService(retailProductReqData: RetailProductReqData){
        val product = productRepository.findById(retailProductReqData.id).get()
        product.wholesale.add(Wholesale(
            0,
            LocalDate.now(ZoneId.of("Asia/Seoul")),
            retailProductReqData.count
        ))
        product.inventory -= retailProductReqData.count
        productRepository.save(product)
    }

    fun queryLogisticsByDateService(queryByDateReqData: QueryByDateReqData): QueryLogisticsByDateResDate{
        val user: User = queryCurrentUser()
        val product = productRepository.findByUser(user)

        var retail = 0
        var wholesale = 0
        var inventory = 0

        val queryLogisticsByDateResDate = QueryLogisticsByDateResDate()

        product.map {
            var retailCount = 0
            var wholesaleCount = 0

            inventory += it.inventory

            it.retail.filter { re -> re.data == queryByDateReqData.date }
                .map { re ->
                    retailCount += re.count
                    retail += re.count
                }
            it.wholesale.filter { wh -> wh.data == queryByDateReqData.date }
                .map { wh ->
                    wholesaleCount+=wh.count
                    wholesale+=wh.count
                }

            queryLogisticsByDateResDate.product.add(
                QueryLogisticsByDateResDate.Product(
                    wholesaleCount,
                    retailCount,
                    it.name,
                    it.profile
                )
            )
        }

        queryLogisticsByDateResDate.inventory = inventory
        queryLogisticsByDateResDate.wholesaleCount = wholesale
        queryLogisticsByDateResDate.retailCount = retail

        return queryLogisticsByDateResDate
    }

    fun queryPriceByDateService(queryByDateReqData: QueryByDateReqData): QueryPriceByDateResDate {
        val user: User = queryCurrentUser()
        val product = productRepository.findByUser(user)

        var retail = 0
        var wholesale = 0

        val queryPriceByDateResDate = QueryPriceByDateResDate()

        product.map {

            var retailPrice = 0
            var wholesalePrice = 0

            it.retail.filter { re -> re.data == queryByDateReqData.date }
                .map { re ->
                    retailPrice += re.count * it.retailPrice
                    retail += re.count * it.retailPrice
                }
            it.wholesale.filter { wh -> wh.data == queryByDateReqData.date }
                .map { wh ->
                    wholesalePrice += wh.count * it.wholesalePrice
                    wholesale+=wh.count * it.wholesalePrice
                }

            queryPriceByDateResDate.product.add(
                QueryPriceByDateResDate.Product(
                    wholesalePrice,
                    retailPrice,
                    it.name,
                    it.profile
                )
            )
        }

        queryPriceByDateResDate.price = retail - wholesale
        queryPriceByDateResDate.wholesalePrice = wholesale
        queryPriceByDateResDate.retailPrice = retail

        return queryPriceByDateResDate
    }

    fun queryDetailProductService(queryDetailProductReqData: QueryDetailProductReqData): QueryDetailProductResData {
        val product = productRepository.findById(queryDetailProductReqData.id).get()

        val month = LocalDate.now().month.value
        val day = LocalDate.now().dayOfMonth

        val price = product.retailPrice

        val queryDetailProductResData = QueryDetailProductResData(
            product.profile,
            product.name,
            product.inventory,
            product.barcode,
            product.wholesalePrice,
            product.retailPrice
        )

        for(i in -1..0) {
            for (j in -1..1) {
                var dailyCount = 0
                product.retail.filter { re -> (re.data.dayOfMonth == (day + j)) && (re.data.month.value == (month + i)) }
                    .map { re ->
                        dailyCount += re.count * price
                    }
                queryDetailProductResData.dailyPrice.add(
                    QueryDetailProductResData.DatePrice(
                        month+i,
                        day+j,
                        dailyCount
                    )
                )
            }
        }

        product.retail.filter { re -> re.data.month.value == month }
            .map {
                queryDetailProductResData.monthlyPrice += it.count * price
            }

        return queryDetailProductResData
    }

    private fun queryCurrentUser(): User{
        val authentication: Authentication = SecurityContextHolder.getContext().authentication ?: throw UntitledException(404, "유저를 찾을 수 없음")
        if (authentication.principal is UserDetails) {
            val username = (authentication.principal as UserDetails).username
            return userRepository.findByEmail(username).get()
        }else
            throw UntitledException(500, "서버 내부 에러")
    }
}