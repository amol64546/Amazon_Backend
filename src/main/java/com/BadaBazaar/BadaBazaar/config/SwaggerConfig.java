
/*
 * Copyright (c) 2024.
 * Gaian Solutions Pvt. Ltd.
 * All rights reserved.
 */

package com.BadaBazaar.BadaBazaar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

  @Bean
  public OpenAPI api() {
    log.info("-----creating swagger config bean for groupOpenApi-----");
    return new OpenAPI()
      .info((new Info().title("Bada Bazaar")
        .description("Bada Bazaar")
        .version("v1.0")
        .termsOfService("Bada Bazaar")
        .description("Bada Bazaar")))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("bearerAuth",
              new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                .bearerFormat("JWT")));
  }


}
