package br.com.ruderson.portfolio_api.dto.education;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EducationResponse(Long id, String courseName, String institution, String description, Integer workload,
                                String certificateUrl, LocalDate startDate, LocalDate endDate) {
}
