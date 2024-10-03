package br.com.ruderson.portfolio_api.dto;

import java.time.LocalDate;

public record EducationResponse(Long id, String courseName, String institution, String description, Integer workload,
                                String certificateUrl, LocalDate startDate, LocalDate endDate) {
}
