package team.untitled.unboxingBackend.global.exception

class UntitledException(
    val status:Int,
    message:String
):RuntimeException(message) {
}