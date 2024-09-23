package br.com.ruderson.portfolio_api.dto.experience;

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
public class ExperienceDTO {
    private Long id;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Between 3 and 80 characters")
    private String position;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Between 3 and 80 characters")
    private String company;
    private String description;
    @NotNull(message = "Field required")
    private LocalDate startDate;
    private LocalDate endDate;
}
