package br.com.ruderson.portfolio_api.dto.image;

import br.com.ruderson.portfolio_api.dto.project.ProjectDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageDTO {
    private Long id;
    @NotBlank(message = "Field required")
    private String url;
    @JsonIgnore
    private ProjectDTO project;

}
