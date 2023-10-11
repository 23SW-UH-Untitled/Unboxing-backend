package team.untitled.unboxingBackend.domain.user.domian.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.untitled.unboxingBackend.domain.user.User

@Repository
interface UserRepository : JpaRepository<User, Long>