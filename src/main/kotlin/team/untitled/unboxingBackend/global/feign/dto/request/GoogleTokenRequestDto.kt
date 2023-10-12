package team.untitled.unboxingBackend.global.feign.dto.request

class GoogleTokenRequestDto (
    private val code: String,
    private val clientId: String,
    private val clientSecret: String,
    private val redirectUri: String,
    private val grantType: String = "authorization_code"
)
