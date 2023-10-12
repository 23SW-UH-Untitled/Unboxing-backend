package team.untitled.unboxingBackend.domain.product.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import team.untitled.unboxingBackend.domain.product.domain.entity.Product
import team.untitled.unboxingBackend.domain.user.User

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    fun findByUser(user: User): List<Product>
}