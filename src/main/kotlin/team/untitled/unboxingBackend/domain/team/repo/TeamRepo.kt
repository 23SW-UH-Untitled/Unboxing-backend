package team.untitled.unboxingBackend.domain.team.repo

import org.springframework.data.jpa.repository.JpaRepository
import team.untitled.unboxingBackend.domain.team.Team

interface TeamRepo: JpaRepository<Team,Long> {
}