package br.com.ruderson.portfolio_api.dto;

import java.time.LocalDate;

public record ExperienceResponse(Long id, String position, String company, String description, LocalDate startDate, LocalDate endDate) {
}
