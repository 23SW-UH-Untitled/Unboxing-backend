package team.untitled.unboxingBackend.domain.user.repo

import org.springframework.data.jpa.repository.JpaRepository
import team.untitled.unboxingBackend.domain.user.User
import java.util.*

interface UserRepo: JpaRepository<User,Long> {
    fun findByEmail(email: String): Optional<User>
}