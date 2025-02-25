package com.prueba.tecnica.backend.config;



import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2

public class SwaggerConfig  {

    @Autowired
    private TypeResolver typeResolver;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stratio.sbs.api.controller"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaData())
                .securitySchemes(Lists.newArrayList(apiKey()))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.POST, getCustomizedResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, getCustomizedResponseMessages());

    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Prueba tecnica REST API")
                .description("\"API\"")
                .version("1.0.0")
                .license("")
                .licenseUrl("")
                .contact(new Contact("", "", ""))
                .build();
    }

    private List<ResponseMessage> getCustomizedResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder()
                .code(200)
                .message("Response obtained successful")
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(401)
                .message("You are not authorized to view the resource")
                .responseModel(new ModelRef("ResponseDTO"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(403)
                .message("Accessing the resource you were trying to reach is forbidden")
                .responseModel(new ModelRef("ResponseDTO"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(404)
                .message("The resource you were trying to reach is not found")
                .responseModel(new ModelRef("ResponseDTO"))
                .build());
        return responseMessages;
    }
}
