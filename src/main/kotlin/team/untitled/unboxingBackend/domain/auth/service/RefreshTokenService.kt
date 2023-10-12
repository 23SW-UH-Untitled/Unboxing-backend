package team.untitled.unboxingBackend.domain.auth.service

import org.springframework.stereotype.Service
import team.untitled.unboxingBackend.domain.auth.repo.RefreshTokenRepo
import team.untitled.unboxingBackend.domain.user.User
import team.untitled.unboxingBackend.domain.user.UserFacade
import team.untitled.unboxingBackend.global.exception.UntitledException
import team.untitled.unboxingBackend.global.jwt.dto.TokenResponseDto
import team.untitled.unboxingBackend.global.jwt.util.JwtProvider
import team.untitled.unboxingBackend.global.jwt.util.JwtUtil

@Service
class RefreshTokenService(
    private val jwtUtil: JwtUtil,
    private val jwtProvider: JwtProvider,
    private val refreshTokenRepo: RefreshTokenRepo,
    private val userFacade: UserFacade
) {
    fun execute(token: String): TokenResponseDto {

        val refreshToken = refreshTokenRepo.findById(token)
            .orElseThrow{UntitledException(401,"Not Login")}

        jwtUtil.getJws(jwtUtil.parseToken(token))

        val user: User = userFacade.getUserById(refreshToken.userId)

        return TokenResponseDto(
            jwtProvider.generateAccessToken(refreshToken.userId,user.authority.toString()),
            refreshToken.refreshToken,
            refreshToken.expiredAt)

    }
}
