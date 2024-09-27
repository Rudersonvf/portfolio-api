package br.com.ruderson.portfolio_api.dto.message;

public interface MessageSummaryProjection {
    Long getId();
    String getName();
    String getSubject();
    Boolean getIsRead();
}
