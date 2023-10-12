package team.untitled.unboxingBackend.global.security.auth

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import team.untitled.unboxingBackend.domain.user.User
import team.untitled.unboxingBackend.domain.user.repo.UserRepo

@Service
class AuthDetailService(
    val userRepo: UserRepo
) {
    fun loadByUsername(username: String):UserDetails{
        val user:User =  userRepo.findByEmail(username).orElseThrow();
        return AuthDetails(user)
    }
}