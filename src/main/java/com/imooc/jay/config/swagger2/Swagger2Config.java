package com.imooc.jay.config.swagger2;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by jay
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Value("${swagger.show}")
    private boolean swaggerShow;


    @Bean("area模块")
    public Docket areaApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("area模块")
                .enable(swaggerShow)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/area.*"))
                .build()
                .apiInfo(metadata());
    }

    @Bean("test模块")
    public Docket testApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("test模块")
                .enable(swaggerShow)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/test.*"))
                .build()
                .apiInfo(metadata());
    }


    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("jay-api")
                .description("test-swagger2")
                .version("1.0")
                .build();
    }




//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(swaggerShow)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.dadaabc.teachermgtapi"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metadata());
//    }
//
//
//    private ApiInfo metadata() {
//        return new ApiInfoBuilder()
//                .title("techermgt-iapi")
//                .version("1.0")
//                .build();
//    }
}
