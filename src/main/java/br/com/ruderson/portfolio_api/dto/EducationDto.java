package br.com.ruderson.portfolio_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EducationDto {
    private Long id;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Between 3 and 80 characters")
    private String courseName;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Between 3 and 80 characters")
    private String institution;
    @Size(min = 15, max = 700, message = "Between 15 and 700 characters")
    private String description;
    private Integer workload;
    private String certificateUrl;
    @NotNull(message = "Field required")
    private LocalDate startDate;
    private LocalDate endDate;
}
