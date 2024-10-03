package br.com.ruderson.portfolio_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDto {
    private Long id;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Between 3 and 80 characters")
    private String name;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 80, message = "Between 3 and 80 characters")
    private String email;
    @NotBlank(message = "Field required")
    @Size(min = 3, max = 120, message = "Between 3 and 120 characters")
    private String subject;
    @NotBlank(message = "Field required")
    @Size(min = 15, max = 500, message = "Between 15 and 500 characters")
    private String message;
    @NotNull(message = "Sent date and time is required")
    private Instant sentAt;
    private Boolean isRead;
}
