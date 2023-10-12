package team.untitled.unboxingBackend.domain.auth

import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class RefreshToken(
    @Id
    val refreshToken: String,

    val userId: Long,

    val expiredAt: ZonedDateTime
)