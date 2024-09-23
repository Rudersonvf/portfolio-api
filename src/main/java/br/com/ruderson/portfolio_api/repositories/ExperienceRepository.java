package br.com.ruderson.portfolio_api.repositories;

import br.com.ruderson.portfolio_api.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}
