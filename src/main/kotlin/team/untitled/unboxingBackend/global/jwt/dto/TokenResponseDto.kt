package team.untitled.unboxingBackend.global.jwt.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

class TokenResponseDto (
    private val accessToken: String,
    private val refreshToken: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private val expiredAt: ZonedDateTime,
)
