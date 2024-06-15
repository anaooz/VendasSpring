package io.github.anaooz.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//http://localhost:8080/swagger-ui/index.html
//https://github.com/springdoc/springdoc-openapi-demos/blob/master/demo-spring-boot-3-webmvc/src/main/java/org/springdoc/demo/app2/Application.java
//https://www.baeldung.com/openapi-jwt-authentication
@Configuration
@OpenAPIDefinition
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi apiGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("vendas")
                .addOpenApiCustomizer(openApi -> openApi.setInfo(new Info()
                        .title("API Vendas")
                        .contact(contact())
                        .version(appVersion)
                        .description("Api do projeto de vendas")
                ))
                .packagesToScan("io.github.anaooz.rest.controller")
                .build();
    }

    public Contact contact() {
        Contact contact = new Contact();

        contact.setName("Mateus");
        contact.setEmail("mateusmarchetti.vieira@gmail.com");
        contact.setUrl("https://github.com/anaooz");

        return contact;
    }
}
