package team.untitled.unboxingBackend.global.oauth

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

//@Configuration
@ConfigurationProperties("spring.security.oauth2.client.registration.google")
class OauthProperties (
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String
)
