package com.msa.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) { SpringApplication.run(GatewayApplication.class, args);	}

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private String serverPort;
    @Value("${posts.api.url}")
    private String postsUri;
    @Value("${comments.api.url}")
    private String commentsUri;

    //private String postsUri = "http://localhost:8081";
    //private String commentsUri = "http://localhost:8082";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        System.out.println(applicationName+":"+serverPort);
        return builder.routes()
                .route(p -> p
                        .path("/posts/**") //http://localhost:8080/posts
                        .uri("lb://posts-api")) //http://localhost:8081/posts
                .route(p -> p
                        .path("/comments/**") //http://localhost:8080/comments
                        .uri(commentsUri)) //http://localhost:8082/comments
                .build();
    }

}
