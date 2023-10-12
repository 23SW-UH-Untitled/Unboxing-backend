package team.untitled.unboxingBackend.domain.product.domain.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
class Wholesale (
    @Column(name = "wholesale_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    @Temporal(TemporalType.DATE)
    val data: LocalDate,

    @Column
    val count: Int
)