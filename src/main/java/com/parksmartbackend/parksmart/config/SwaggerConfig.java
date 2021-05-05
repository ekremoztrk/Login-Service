package com.parksmartbackend.parksmart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Import({ BeanValidatorPluginsConfiguration.class, SpringDataRestConfiguration.class })
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Creates the Swagger configuration bean.
     *
     * @return docket bean
     */
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).groupName("DELTA").select()
                // .apis(RequestHandlerSelectors.basePackage(
                // "com.basaki.controller"))
                // .apis(exactPackages("com.basaki.controller", "com.basaki.model",
                // "com.basaki.data.repository"))
                // .paths(PathSelectors.any())
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo("Parksmart API",
                        "This api adheres to the RESTfull properties and all entities can be accessed with pagination and sorting. Full table is not sent, only pages. "
                                + "For more info, search pagination on google.\n" + "\n"
                                + "## There are 2 kinds of items in this document, Entity and Controller. Entity items document the direct access to an entity for CRUD operations, while controllers provide custom endpoints.\n"
                                + "\n" + "## EDITING AN ENTRY\n"
                                + "For editing, use PATCH commands and give only the thin you edit. Put replaces the whole object, while patch only updates it with the given attributes.\n"
                                + "\n" + "## REFERENCING OBJECTS\n"
                                + "For example, while assigning a company to user, send a POST request to the /user/id/company address with the content type \"text/uri-list\" and body \"../api/companies/id\"."));
    }

    /**
     * Creates an object containing API information including author name,
     * email, version, license, etc.
     *
     * @param title API title
     * @param description API description
     * @return API information
     */
    private ApiInfo apiInfo(String title, String description) {

        return new ApiInfoBuilder().title(title).description(description).version("1.0.0-Snapshot").build();
    }
}