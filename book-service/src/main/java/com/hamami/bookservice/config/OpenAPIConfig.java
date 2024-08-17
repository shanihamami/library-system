package com.hamami.bookservice.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI bookServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Book Service API")
                        .description("This is the REST API for Book Service"));
    }
}
