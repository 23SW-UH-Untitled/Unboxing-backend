package team.untitled.unboxingBackend.domain.auth.presentation

import org.springframework.web.bind.annotation.*
import team.untitled.unboxingBackend.domain.auth.service.OAuth2GoogleService
import team.untitled.unboxingBackend.domain.auth.service.RefreshTokenService
import team.untitled.unboxingBackend.global.exception.UntitledException
import team.untitled.unboxingBackend.global.jwt.dto.TokenResponseDto
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/login")
class AuthController(
    private val googleService: OAuth2GoogleService,
    private val refreshTokenService: RefreshTokenService
) {

    @PostMapping("/google")
    fun loginOfGoogle(@RequestParam(name = "code") code: String?): TokenResponseDto {
        if(code == null) throw UntitledException(400,"No Code")
        return googleService.execute(code)
    }

    @PutMapping
    fun refreshToken(@RequestBody refreshToken: @NotBlank String?): TokenResponseDto {
        if(refreshToken == null) throw UntitledException(400,"No Code")
        return refreshTokenService.execute(refreshToken)
    }
}
