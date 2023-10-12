package team.untitled.unboxingBackend.domain.product.controller.data.res

data class QueryDetailProductResData (
    val profile: String,
    val name: String,
    val inventory: Int,
    val barcode: String,
    val wholesalePrice: Int,
    val retailPrice: Int,
    var dailyPrice: MutableList<DatePrice> = mutableListOf(),
    var monthlyPrice: Int = 0
){
    data class DatePrice(
        val month: Int,
        val date: Int,
        val retailPrice: Int
    )
}