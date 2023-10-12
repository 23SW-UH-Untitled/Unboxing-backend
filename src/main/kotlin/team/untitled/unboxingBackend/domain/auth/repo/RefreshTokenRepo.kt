package team.untitled.unboxingBackend.domain.auth.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import team.untitled.unboxingBackend.domain.auth.RefreshToken

interface RefreshTokenRepo:JpaRepository<RefreshToken,String> {
}