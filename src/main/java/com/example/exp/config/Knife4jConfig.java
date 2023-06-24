package com.example.exp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Description: Knife4jConfig
 * @Author: zsp
 * @Date: 2023/6/23 17:39
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("我的标题1")
                        .description("我的描述")
                        // .termsOfServiceUrl("http://www.xx.com/")
                        .contact(new Contact("hello", "https://knife.blog.csdn.net", "xx@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("all")
                .select()
                //指定Controller扫描路径。可以不具体到controller，它会扫描指定路径下的所有
                .apis(RequestHandlerSelectors.basePackage("com.example.exp"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
