package team.untitled.unboxingBackend.global.jwt.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

class TokenResponseDto (
     val accessToken: String,
     val refreshToken: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
     val expiredAt: ZonedDateTime,
)
