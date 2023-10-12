package team.untitled.unboxingBackend.domain.product.domain.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
class Retail (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(columnDefinition = "DATE")
    val data: LocalDate,

    @Column
    val count: Int
)