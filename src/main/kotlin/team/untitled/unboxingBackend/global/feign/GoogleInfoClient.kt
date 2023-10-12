package team.untitled.unboxingBackend.global.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import team.untitled.unboxingBackend.global.feign.dto.response.GoogleInfoResponseDto

@FeignClient(name = "googleInfoClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
interface GoogleInfoClient {
    @GetMapping("?access_token={TOKEN}")
    fun getUserInfo(@PathVariable("TOKEN") token: String): GoogleInfoResponseDto
}
