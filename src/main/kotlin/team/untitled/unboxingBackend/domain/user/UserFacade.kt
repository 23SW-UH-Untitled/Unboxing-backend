package team.untitled.unboxingBackend.domain.user

import org.springframework.stereotype.Component
import team.untitled.unboxingBackend.domain.user.repo.UserRepo
import team.untitled.unboxingBackend.global.exception.UntitledException


@Component
class UserFacade(
    val userRepo: UserRepo
){
    fun getUserById(id: Long): User {
        return userRepo.findById(id)
            .orElseThrow{UntitledException(404,"User Not Found")}
    }

    fun getUserByEmail(email: String): User {
        return userRepo.findByEmail(email)
            .orElseThrow{UntitledException(404,"User Not Found")}
    }
}

