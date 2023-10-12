package team.untitled.unboxingBackend.global.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.CorsUtils
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import team.untitled.unboxingBackend.global.jwt.auth.JwtAuth
import team.untitled.unboxingBackend.global.jwt.util.JwtUtil
import java.util.*
import java.util.Arrays.*


@EnableWebSecurity
class SecurityConfig(
    val jwtAuth: JwtAuth,
    val jwtUtil: JwtUtil
) {
    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain = http
        .cors(Customizer.withDefaults())
        .csrf().disable()
        .httpBasic().disable()
        .formLogin().disable()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .anyRequest().permitAll()
        .and()
        .apply(FilterConfig(jwtUtil, jwtAuth))
        .and()
        .build()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOriginPatterns = listOf("*")
        configuration.setAllowedMethods(listOf("GET", "POST","PUT","DELETE","OPTIONS"))
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}