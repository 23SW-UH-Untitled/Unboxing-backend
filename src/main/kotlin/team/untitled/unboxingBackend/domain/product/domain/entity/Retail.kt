package team.untitled.unboxingBackend.domain.product.domain.entity

import java.util.*
import javax.persistence.*

@Entity
class Retail (
    @Column(name = "retail_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    @Temporal(TemporalType.DATE)
    val data: Date,

    @Column
    val count: Int
)