package com.vinco_orbis.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Api Vinco Orbis")
                        .description("Este es una api para prueba tecnica de java.")
                        .version("v1.0")
                        .termsOfService("Términos del servicio")
                        .contact(new Contact()
                                .name("Ricardo Altamirano")
                                .url("www.ejemplo.com")
                                .email("altamiranoricardo546@gmail.com"))
                                .license(new License().name("Licencia").url("URL de licencia")));
    }
}

