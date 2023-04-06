package com.endproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/3/28 15:08
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {
    /**
    * @Description: 注入一个ServerEndpointExporter，该Bean会自动注册使用@ServerEndpoint注解申明WebSocket endpoint
    * @date 2023/3/28 15:09
    * @author WangNaiLinn
    **/


    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
