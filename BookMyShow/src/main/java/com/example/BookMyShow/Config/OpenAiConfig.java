package com.example.BookMyShow.Config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {

    @Bean
    public OpenAPI bookMyShowOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BookMyShow API")
                        .description("Minimal BookMyShow style API")
                        .version("v1.0"));
    }
}

