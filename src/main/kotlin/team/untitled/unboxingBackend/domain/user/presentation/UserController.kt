package team.untitled.unboxingBackend.domain.user.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import team.untitled.unboxingBackend.domain.user.User
import team.untitled.unboxingBackend.domain.user.presentation.dto.UserRes
import team.untitled.unboxingBackend.global.utils.SecurityUtils

@RestController
@RequestMapping("/user")
class UserController(
) {
    @GetMapping("me")
    fun findMyInfo():UserRes{
        val user: User = SecurityUtils.getUser()
        return UserRes(user.id,user.name,user.email,user.profileImage)
    }

}