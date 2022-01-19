package com.xujiahui.base;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 使用swagger快速生成接口文档，使用@EnableSwagger2注解标注
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2) //使用swargger的版本
                .groupName("webApi") //组
                .apiInfo(webApiInfo()) //信息
                .select()
                //当你的接口中包含amidn，error时，他就不进行显示
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error/.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return  new ApiInfoBuilder()
                .title("卒業研究api")
                .description("APIの定義")
                .version("1.0")
//                .contact(new Contact("java"))
                .build();
    }
}
