package com.endproject.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        /**
         * 所有请求都允许跨域，使用这种配置就不需要
         * 在interceptor中配置header了
         */
        corsRegistry.addMapping("/**")
                .allowCredentials(false)
                .allowedOrigins("http://localhost:8001")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE","OPTIONS","TRACE")
                .allowedHeaders("*")
                .exposedHeaders("Access-Control-Allow-Headers",
                        "Access-Control-Allow-Methods",
                        "Access-Control-Allow-Origin",
                        "Access-Control-Max-Age",
                        "X-Frame-Options")
                .maxAge(3600);
    }
    /*@Bean
    public FilterRegistrationBean filter(){
        CorsConfiguration config=new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        //When allowCredentials is true, allowedOrigins cannot contain the special value "*“since that cannot be set on the “Access-Control-Allow-Origin” response header.
        // To allow credentials to a set of origins, list them explicitly or consider using"allowedOriginPatterns” instead.
        config.addAllowedOriginPattern("*");
        UrlBasedCorsConfigurationSource configSource=new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config);
        FilterRegistrationBean registrationBean=new FilterRegistrationBean(new CorsFilter(configSource));
        registrationBean.addUrlPatterns("/*");
        //优先级，越低越优先
        registrationBean.setOrder(1);
        return registrationBean;
    }*/

}
