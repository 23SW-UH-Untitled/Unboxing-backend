package team.untitled.unboxing.domain.product.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.untitled.unboxing.domain.product.domain.entity.Wholesale

@Repository
interface WholesaleRepository : CrudRepository<Wholesale, Long>