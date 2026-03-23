package com.dphuong.go.gscores.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${urls.backend}")
    private String backendUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("G-Scores API")
                        .version("1.0")
                        .description("G-Scores API Documentation"))
                .servers(List.of(
                        new Server().url(backendUrl).description("Production"),
                        new Server().url("http://localhost:8888").description("Local")
                ));
    }
}
