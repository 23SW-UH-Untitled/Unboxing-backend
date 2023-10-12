package team.untitled.unboxingBackend.domain.product.domain.entity
import team.untitled.unboxingBackend.domain.user.User
import javax.persistence.*

@Entity
class Product (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val name: String,

    @Column
    val barcode: String,

    @Column
    val profile: String,

    @Column
    var inventory: Int,

    @Column(name = "wholesale_price")
    val wholesalePrice: Int,

    @Column(name = "retail_price")
    val retailPrice: Int,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    val wholesale: MutableList<Wholesale> = mutableListOf(),

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    val retail: MutableList<Retail> = mutableListOf(),

    @ManyToOne
    @JoinColumn
    val user: User
)