package team.untitled.unboxingBackend.global.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import team.untitled.unboxingBackend.global.feign.dto.request.GoogleTokenRequestDto
import team.untitled.unboxingBackend.global.feign.dto.response.GoogleTokenResponseDto

@FeignClient(name = "googleAuthClient", url = "https://oauth2.googleapis.com/token")
interface GoogleAuthClient {
    @PostMapping
    fun getGoogleToken(request: GoogleTokenRequestDto): GoogleTokenResponseDto
}
