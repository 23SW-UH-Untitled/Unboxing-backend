package team.untitled.unboxingBackend.global.jwt.util

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding




@ConstructorBinding
@ConfigurationProperties(prefix = "auth.jwt")
class JwtProperties(
    val header: String,
    val secret: String,
    val accessExp: Long,
    val refreshExp: Long,
    val prefix: String
)