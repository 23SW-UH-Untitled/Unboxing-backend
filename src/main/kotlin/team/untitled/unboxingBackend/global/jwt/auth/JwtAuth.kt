package team.untitled.unboxingBackend.global.jwt.auth

import io.jsonwebtoken.Claims
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import team.untitled.unboxingBackend.global.exception.UntitledException
import team.untitled.unboxingBackend.global.jwt.util.JwtUtil
import team.untitled.unboxingBackend.global.security.auth.AuthDetailService

@Component
class JwtAuth(
    val jwtUtil: JwtUtil,
    val authDetailService: AuthDetailService
) {
    fun authentication(token: String): UsernamePasswordAuthenticationToken {
        val claims: Claims = jwtUtil.getJwt(token).getBody()
        if (isNotAccessToken(token)) {
            throw UntitledException(401,"Invalid Jwt")
        }
        val userDetails: UserDetails =
            authDetailService.loadByUsername(claims["id"] as Long)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun isNotAccessToken(token: String): Boolean {
        if (token.isEmpty()) {
            throw UntitledException(401,"Invalid Jwt")
        }
        val role: String = jwtUtil.getJwt(token).getHeader().get("type").toString()
        return role != "access_token"
    }
}