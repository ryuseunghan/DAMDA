package com.mansa.damda.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("UserAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mansa.damda.user"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket storeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("StoreAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mansa.damda.store"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ProductAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mansa.damda.product"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket orderApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("OrderAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mansa.damda.order"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket marketApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("MarketAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mansa.damda.market"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket largeLocationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("LargeLocationAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mansa.damda.largelocaiton"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket fineLocationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("FineLocationAPI")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mansa.damda.finelocaiton"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DamdaSwagger")
                .description("멋사 하계 중앙 해커톤 담다 스웨거입니다.")
                .version("1.0.0")
                .build();
    }
}
