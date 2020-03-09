package br.com.ecommerceapi.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@Configuration
@Profile("dev")
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiPublic() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public_api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.ecommerceapi.controller"))
                .paths(PathSelectors.regex("(?!/api/cart).+"))
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket apiPrivate() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("private_api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.ecommerceapi.controller"))
                .paths(PathSelectors.regex("(?!/api/users).+"))
                .paths(PathSelectors.regex("(?!/api/signin).+"))
                .paths(PathSelectors.regex("(?!/api/products).+"))
                .paths(PathSelectors.regex("(?!/api/tickets).+"))
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Desafio Facilit")
                .description("Documentação da API de acesso aos endpoints do Desafio Facilit.")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes));
    }

}
