package team.untitled.unboxingBackend.domain.auth.presentation

import org.springframework.web.bind.annotation.*
import team.untitled.unboxingBackend.domain.auth.presentation.dto.RefreshTokenReq
import team.untitled.unboxingBackend.domain.auth.service.OAuth2GoogleService
import team.untitled.unboxingBackend.domain.auth.service.RefreshTokenService
import team.untitled.unboxingBackend.global.exception.UntitledException
import team.untitled.unboxingBackend.global.jwt.dto.TokenResponseDto
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/auth")
class AuthController(
    private val googleService: OAuth2GoogleService,
    private val refreshTokenService: RefreshTokenService
) {

    @PostMapping("/google")
    fun loginOfGoogle(@RequestParam(name = "token") token: String?): TokenResponseDto {
        if(token == null) throw UntitledException(400,"No token")
        val a = googleService.execute(token)
        println(a.accessToken)
        println(a.refreshToken)
        return googleService.execute(token)
    }

    @PutMapping("refreshToken")
    fun refreshToken(@RequestBody refreshToken: @NotBlank RefreshTokenReq?): TokenResponseDto {
        if(refreshToken == null) throw UntitledException(400,"No Code")
        return refreshTokenService.execute(refreshToken.refreshToken)
    }
}
