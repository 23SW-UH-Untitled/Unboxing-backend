package team.untitled.unboxingBackend.domain.user

import team.untitled.unboxingBackend.domain.product.domain.entity.Product
import javax.persistence.*

@Entity
class User(

    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name:String,

    val profileImage:String,

    @OneToMany
    @JoinColumn(name = "user_id")
    val Prodect: List<Product>
)