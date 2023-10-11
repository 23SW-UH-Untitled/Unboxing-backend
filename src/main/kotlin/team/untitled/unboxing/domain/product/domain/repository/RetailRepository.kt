package team.untitled.unboxing.domain.product.domain.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import team.untitled.unboxing.domain.product.domain.entity.Retail

@Repository
interface RetailRepository : CrudRepository<Retail, Long>