package br.com.ruderson.portfolio_api.repositories;

import br.com.ruderson.portfolio_api.projections.ProjectDetailsProjection;
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

    @Query(nativeQuery = true, value = "SELECT p.id AS id, p.title AS title, p.description AS description, p.live_url AS liveUrl, " +
            "p.repository_url AS repositoryUrl, " +
            "GROUP_CONCAT(DISTINCT c.name SEPARATOR ', ') AS categories, " +
            "GROUP_CONCAT(DISTINCT i.url SEPARATOR ', ') AS imageUrls, " +
            "GROUP_CONCAT(DISTINCT sk.id SEPARATOR ', ') AS skills " +
            "FROM tb_project p " +
            "LEFT JOIN tb_project_category pc ON p.id = pc.project_id " +
            "LEFT JOIN tb_category c ON pc.category_id = c.id " +
            "LEFT JOIN tb_image i ON i.project_id = p.id " +
            "LEFT JOIN tb_project_skill ps ON p.id = ps.project_id " +
            "LEFT JOIN tb_skill sk ON ps.skill_id = sk.id " +
            "GROUP BY p.id")
    List<ProjectDetailsProjection> findAllProjectDetails();
}
