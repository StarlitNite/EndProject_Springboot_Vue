package com.endproject.config;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author：乃王木木
 * @date 2022/12/21 20:11
 */

@Configuration
@EnableOpenApi
@Slf4j
public class Swagger3Config implements ApplicationListener<WebServerInitializedEvent> {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                //.withMethodAnnotation(ApiOperation.class)是为了扫描包内所有带有@Api注解的项目
                //.basePackage("com.endproject.controller")可扫描指定位置的接口
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())

                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("接口文档")
                .description("WangNaiLinn毕设")
                .version("1.0")
                .build();
    }

    @Override
    public void onApplicationEvent (WebServerInitializedEvent event) {
        try {
            // 获取IP
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            // 获取端口号
            int port = event.getWebServer().getPort();
            // 获取应用名
            String applicationName = event.getApplicationContext().getApplicationName();
            // 打印 swagger 文档地址
            log.info("接口文档地址: http://" + "localhost" + ":" + port + applicationName + "/swagger-ui/index.html");
            // 打印 swagger2 文档地址
            //log.info("项目启动成功！接口文档地址: http://" + "localhost" + ":" + port + applicationName + "/v3/api-docs");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
