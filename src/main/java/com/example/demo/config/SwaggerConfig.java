package com.example.demo.config; 
 
import io.swagger.v3.oas.models.Components; 
import io.swagger.v3.oas.models.OpenAPI; 
import io.swagger.v3.oas.models.info.Info; 
import io.swagger.v3.oas.models.security.SecurityRequirement; 
import io.swagger.v3.oas.models.security.SecurityScheme; 
import io.swagger.v3.oas.models.servers.Server; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import java.util.List; 
 
@Configuration 
public class SwaggerConfig { 
 
    @Bean 
    public OpenAPI customOpenAPI() { 
        final String securitySchemeName = "bearerAuth"; 
        return new OpenAPI() 
                .info(new Info() 
                        .title("User Profile API") 
                        .version("1.0") 
                        .description("API for managing user profiles. Use the /api/auth/login endpoint to get a token.")).servers(List.of( new 
Server().url("https://9064.pro604cr.amypo.ai/").description("Development Server") 
                )) 
                // This applies security to EVERY endpoint in the UI 
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName)) 
                .components(new Components() 
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme() 
                                .name(securitySchemeName) 
                                .type(SecurityScheme.Type.HTTP) 
                                .scheme("bearer") 
                                .bearerFormat("JWT") 
                                .in(SecurityScheme.In.HEADER))); // Ensures token is sent in Header 
    } 
}