package br.com.ruderson.portfolio_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))

                .info(new Info()
                        .title("Portfolio API")
                        .description("Documentation to my personal portfólio.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Ruderson Florentino")
                                .url("https://ruderson.com.br")
                                .email("contato@ruderson.com.br")
                        )
                        .license(new License()
                                .name("Licence MIT")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}

