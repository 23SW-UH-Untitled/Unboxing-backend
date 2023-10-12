package team.untitled.unboxingBackend.domain.auth.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import team.untitled.unboxingBackend.domain.user.User
import team.untitled.unboxingBackend.domain.user.authority.Authority
import team.untitled.unboxingBackend.domain.user.repo.UserRepo
import team.untitled.unboxingBackend.global.feign.GoogleInfoClient
import team.untitled.unboxingBackend.global.feign.dto.response.GoogleInfoResponseDto
import team.untitled.unboxingBackend.global.jwt.dto.TokenResponseDto
import team.untitled.unboxingBackend.global.jwt.util.JwtProvider
import java.util.*

@Service
@Transactional
class OAuth2GoogleService(
    private val googleInfoClient: GoogleInfoClient,
    private val jwtProvider: JwtProvider,
    private val userRepo: UserRepo,
) {

    fun execute(token: String): TokenResponseDto {
        val userInfo: GoogleInfoResponseDto = googleInfoClient.getUserInfo(token)
        val user: User = saveOrUpdate(userInfo)
        return jwtProvider.generateToken(user.id, user.authority.toString())
    }

    private fun saveOrUpdate(response: GoogleInfoResponseDto): User {
        val user: Optional<User> = userRepo.findByEmail(response.email)
        if (user.isEmpty) {
            return userRepo.save(
                User(
                    email = response.email,
                    name = response.name,
                    authority = Authority.USER,
                    profileImage = response.picture
                )
            )
        }
        return user.get().update(response.name,response.picture)
    }
}
