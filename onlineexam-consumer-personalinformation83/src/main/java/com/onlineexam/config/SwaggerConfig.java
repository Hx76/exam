package com.onlineexam.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value(value = "${ifCloseSwagger}")
    boolean ifCloseSwagger;

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                //配置信息
                .apiInfo(apiInfo())
                //配置是否启用swagger
                .enable(ifCloseSwagger);
    }

    public ApiInfo apiInfo(){
        //作者信息
        final Contact contact = new Contact("李东伟","https://github.com/Hx76","947769582@qq.com");

        return new ApiInfo(
                "用户个人信息的接口文档",
                "用户个人信息的接口文档",
                "v1.0",
                "https://github.com/Hx76",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }


}
