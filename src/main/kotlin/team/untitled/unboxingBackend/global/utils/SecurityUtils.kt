package team.untitled.unboxingBackend.global.utils

import org.springframework.security.core.context.SecurityContextHolder
import team.untitled.unboxingBackend.domain.user.User
import team.untitled.unboxingBackend.global.exception.UntitledException
import team.untitled.unboxingBackend.global.security.auth.AuthDetails

class SecurityUtils {
    companion object {
        fun getCurrentUserOrNull(): User? {
            return try {
                getUser()
            } catch (e: Exception) {
                null
            }
        }

        fun getUser(): User {
            val principal = SecurityContextHolder.getContext()
                .authentication
                .principal
            if (principal is String) {
                throw UntitledException(401, "User Not Login")
            }
            val authDetails: AuthDetails = principal as AuthDetails
            return authDetails.user
        }
    }
}