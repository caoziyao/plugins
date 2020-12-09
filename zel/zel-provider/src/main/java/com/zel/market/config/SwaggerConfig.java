package com.zel.market.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger 配置类
 *
 * todo swagger导入YApi
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    // Controller所在包 (api文档扫描范围)
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.zel.market.controller";

    @Bean
    public Docket createRestApi() {
        logger.info("SwaggerConfig createRestApi 构建REST API");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        logger.info("SwaggerConfig apiInfo API信息");
        return new ApiInfoBuilder()
                .title("项目api文档")  // 标题
                .description("api文档")  // 描述
                .version("1.0") // 版本
                .contact(new Contact("XXX", "url", "email")) // 联系人信息
                .build();
    }

}
