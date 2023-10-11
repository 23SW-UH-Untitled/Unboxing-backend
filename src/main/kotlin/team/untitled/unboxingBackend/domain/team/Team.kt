package team.untitled.unboxingBackend.domain.team

import team.untitled.unboxingBackend.domain.user.User
import javax.persistence.*

@Entity
class Team (
    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val name:String,

    val profileImage:String,

    @OneToMany
    @JoinColumn(name = "user_id")
    val user: List<User>
)