package id.my.hendisantika.springdocjwt.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : springdoc-jwt
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/28/24
 * Time: 06:14
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerOpenApiConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("SpringBoot API")
                        .description("SpringBoot sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot Wiki Documentation")
                        .url("https://springboot.wiki.github.org/docs"));
//		.addSecurityItem(new SecurityRequirement()
//		        .addList(securitySchemeName))
//		      .components(new Components()
//		        .addSecuritySchemes(securitySchemeName, SecurityScheme)
//		        //  .name(securitySchemeName)
//		          //.type()
//		          .scheme("bearer")
//		          .bearerFormat("JWT")));
    }

    //Add custom header param to all API operations
//	@Bean
//    public OpenApiCustomizer openApiCustomiser() {
//        return openApi -> openApi.getPaths().values().stream()
//                .flatMap(pathItem -> pathItem.readOperations().stream().filter(operation -> !operation.getOperationId().equals("authenticate"))   )
//                .forEach(operation ->
//                operation.addParametersItem(
//                		new HeaderParameter()
//                		.required(false)
//						.name("Authorization")
//						.description("Bearer Token")
//                        .$ref("#/components/parameters/Authorization")));
//
//    }
}
