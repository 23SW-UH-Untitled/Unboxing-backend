package team.untitled.unboxingBackend.domain.user

import team.untitled.unboxingBackend.domain.product.domain.entity.Product
import team.untitled.unboxingBackend.domain.user.authority.Authority
import javax.persistence.*

@Entity
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name:String,

    val email:String,

    val authority: Authority,

    var profileImage:String,

    @OneToMany
    @JoinColumn
    val Product: MutableList<Product> = mutableListOf()
) {
    fun update(name: String, picture: String): User {
        this.name = name
        this.profileImage = picture

        return this
    }
}