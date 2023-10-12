package team.untitled.unboxingBackend.global.oauth

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

//@Configuration
@ConfigurationProperties("spring.security.oauth2.client.registration.google")
class OauthProperties (
    @Value("spring.security.oauth2.client.registration.google.clientId")
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String
)
