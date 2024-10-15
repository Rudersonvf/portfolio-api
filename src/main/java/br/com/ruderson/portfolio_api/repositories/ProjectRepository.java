package br.com.ruderson.portfolio_api.repositories;

import br.com.ruderson.portfolio_api.projections.ProjectSummaryProjection;
import br.com.ruderson.portfolio_api.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(nativeQuery = true, value =
    "SELECT tb_project.id AS id, tb_project.title as title " +
            "FROM tb_project")
    List<ProjectSummaryProjection>findAllProjectedBy();
}
