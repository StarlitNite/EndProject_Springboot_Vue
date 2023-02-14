package com.endproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * EnableScheduling 定时启动
 * @EnableOpenApi Swagger3
 */
@SpringBootApplication(scanBasePackages = "com.endproject")
@MapperScan("com.endproject.dao")
//@EnableScheduling
@EnableOpenApi
public class EndProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EndProjectApplication.class, args);
    }

}
