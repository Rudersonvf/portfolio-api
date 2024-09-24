package br.com.ruderson.portfolio_api.dto.skill;

public record SkillResponse(Long id, String name, String iconUrl, String docUrl, Integer level) {
}
