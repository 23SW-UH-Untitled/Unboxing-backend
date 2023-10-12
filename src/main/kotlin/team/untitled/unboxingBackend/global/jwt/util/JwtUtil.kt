package team.untitled.unboxingBackend.global.jwt.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import team.untitled.unboxingBackend.domain.auth.repo.RefreshTokenRepo
import team.untitled.unboxingBackend.global.exception.UntitledException
import javax.servlet.http.HttpServletRequest


@Component
class JwtUtil(
    val jwtProperties: JwtProperties,
    val refreshTokenRepo: RefreshTokenRepo
) {

    fun resolveToken(request: HttpServletRequest): String? {
        val bearer:String? = request.getHeader(jwtProperties.header)
        return parseToken(bearer)
    }
    fun parseToken(bToken:String?):String? {
        if(!bToken.isNullOrBlank()){
           return bToken.replace("Bearer","").trim()
        }
        return null
    }
    fun getJwt(token: String): Jws<Claims> {
        return Jwts.parser().setSigningKey(jwtProperties.secret).parseClaimsJws(token)
    }

    fun getJws(token: String?): Jws<Claims> {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secret)
                .parseClaimsJws(token)
        } catch (e: ExpiredJwtException) {
            throw UntitledException(401,"User not Login")
        } catch (e: Exception) {
            throw  UntitledException(401,"User not Login")
        }
    }

}