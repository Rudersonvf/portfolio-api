package br.com.ruderson.portfolio_api.repositories;

import br.com.ruderson.portfolio_api.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
}
