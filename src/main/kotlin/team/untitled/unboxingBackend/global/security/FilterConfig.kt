package team.untitled.unboxingBackend.global.security

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import team.untitled.unboxingBackend.global.exception.ExceptionFilter
import team.untitled.unboxingBackend.global.jwt.auth.JwtAuth
import team.untitled.unboxingBackend.global.jwt.auth.JwtFilter
import team.untitled.unboxingBackend.global.jwt.util.JwtUtil

class FilterConfig(
    val jwtUtil: JwtUtil,
    val jwtAuth: JwtAuth
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtFilter(jwtAuth,jwtUtil),UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionFilter(),JwtFilter::class.java)
    }
}