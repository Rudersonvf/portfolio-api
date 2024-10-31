package br.com.ruderson.portfolio_api.repositories;

import br.com.ruderson.portfolio_api.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
