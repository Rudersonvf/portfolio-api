package br.com.ruderson.portfolio_api.projections;

public interface ProjectDetailsProjection {
    Long getId();
    String getTitle();
    String getDescription();
    String getRepositoryUrl();
    String getLiveUrl();
    String getCategories();
    String getImageUrls();
    String getSkills();
}

