package team.untitled.unboxingBackend.domain.auth.presentation.dto.response;

data class TokenResponse (
    val token: String,
    val validate: String
)
