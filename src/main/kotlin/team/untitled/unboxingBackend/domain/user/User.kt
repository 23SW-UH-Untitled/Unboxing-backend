package team.untitled.unboxingBackend.domain.user

import org.springframework.security.core.authority.SimpleGrantedAuthority
import team.untitled.unboxingBackend.domain.user.authority.Authority
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name:String,

    val email:String,

    val authority: Authority,

    var profileImage:String
) {
    fun update(name: String, picture: String): User {
        this.name = name
        this.profileImage = picture

        return this
    }
}