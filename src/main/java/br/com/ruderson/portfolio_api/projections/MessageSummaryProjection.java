package br.com.ruderson.portfolio_api.projections;

public interface MessageSummaryProjection {
    Long getId();
    String getName();
    String getSubject();
    Boolean getIsRead();
}
