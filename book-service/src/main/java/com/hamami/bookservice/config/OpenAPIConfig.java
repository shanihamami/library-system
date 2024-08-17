package com.hamami.bookservice.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration for the OpenAPI documentation of the Borrow Service.
 * This class sets up the OpenAPI details that will be visible on the Swagger UI.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * Creates an OpenAPI configuration bean to customize the API documentation.
     * @return OpenAPI object configured with specific metadata for the Borrow Service API.
     */
    @Bean
    public OpenAPI bookServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Book Service API")
                        .description("This is the REST API for Book Service"));
    }
}
