package team.untitled.unboxingBackend.global.jwt.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import team.untitled.unboxingBackend.global.jwt.util.JwtUtil
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtFilter(val jwtAuth: JwtAuth,val jwtUtil: JwtUtil): OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token: String? = jwtUtil.resolveToken(request)
        SetAuthenticationInSecurityContext(token)
        filterChain.doFilter(request, response)
    }

    // 토큰이 정상이면 security context에 저장
    private fun SetAuthenticationInSecurityContext(token: String?) {
        if (token != null) {
            val authentication = jwtAuth.authentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
    }

}
