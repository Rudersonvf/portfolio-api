package br.com.ruderson.portfolio_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VisitorDto {
    private Long id;
    private String pageVisited;
    private Double timeSpent;
    private String browser;
    private String device;
}
