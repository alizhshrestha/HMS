package com.icodify.multitenant.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
//        ParameterBuilder parameterBuilder = new ParameterBuilder();
//        parameterBuilder.name("tenant-id")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .description("Tenant Id")
//                .required(true)
//                .build();
//
//        List<Parameter> parameters = new ArrayList<>();
//        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(asList(apiKey()))
                .securityContexts(singletonList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(Arrays.asList(
//                        new RequestParameterBuilder()
//                                .name("X-global-header-1")
//                                .description("Remote User")
//                                .in(ParameterType.HEADER)
//                                .required(true)
//                                .query(simpleParameterSpecificationBuilder ->
//                                        simpleParameterSpecificationBuilder
//                                                .allowEmptyValue(false)
//                                                .model(modelSpecificationBuilder ->
//                                                        modelSpecificationBuilder.scalarModel(ScalarType.STRING)))
//                                .build(),
                        new RequestParameterBuilder()
                                .name("tenant-id")
                                .description("Tenant Id")
                                .in(ParameterType.HEADER)
                                .required(false)
                                .query(simpleParameterSpecificationBuilder ->
                                        simpleParameterSpecificationBuilder
                                                .allowEmptyValue(false)
                                                .model(modelSpecificationBuilder ->
                                                        modelSpecificationBuilder.scalarModel(ScalarType.STRING)))
                                .build()
                ));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return singletonList(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

}
