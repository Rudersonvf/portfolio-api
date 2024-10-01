package br.com.ruderson.portfolio_api.dto.project;

import br.com.ruderson.portfolio_api.dto.category.CategoryDTO;
import br.com.ruderson.portfolio_api.dto.image.ImageDTO;
import br.com.ruderson.portfolio_api.dto.skill.SkillDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDTO {
    private Long id;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Between 3 and 80 characters")
    private String title;
    @NotBlank(message = "Field required")
    @Size(min = 30, max = 500, message = "Between 30 and 500 characters")
    private String description;
    private String repositoryUrl;
    private String liveUrl;
    private Instant createdAt;
    private List<CategoryDTO> categories = new ArrayList<>();
    private List<SkillDTO> skills = new ArrayList<>();
    private List<ImageDTO> images = new ArrayList<>();
}
