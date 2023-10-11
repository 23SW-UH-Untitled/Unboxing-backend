package team.untitled.unboxing.domain.product.domain.entity

import javax.persistence.*

@Entity
class Product (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    val id: Long,

    @Column
    val name: String,

    @Column
    val profile: String,

    @Column(name = "wholesale_price")
    val wholesalePrice: Int,

    @Column(name = "retail_price")
    val retailPrice: Int,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_id")
    val wholesale: List<Wholesale>,

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "product_id")
    val reataile: List<Retail>,
)