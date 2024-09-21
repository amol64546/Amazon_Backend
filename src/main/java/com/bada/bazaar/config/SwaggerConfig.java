package com.bada.bazaar.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
      .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
      .components(
        new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
      .info(apiInfo());
  }

  private SecurityScheme createAPIKeyScheme() {
    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
      .bearerFormat("JWT")
      .scheme("bearer");
  }

  private Info apiInfo() {
    return new Info()
      .title("Bada Bazaar")
      .version("1.0.0")
      .description("")
      .contact(new Contact().name("Amol Nakhate"));
  }
}
