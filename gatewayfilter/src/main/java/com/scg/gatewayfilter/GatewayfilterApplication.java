package com.scg.gatewayfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class GatewayfilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayfilterApplication.class, args);
    }


    @GetMapping("/getremoteip")
    public String getip(ServerHttpRequest request) {
        String ip = request.getRemoteAddress().toString();
        return ip;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        AbstractNameValueGatewayFilterFactory.NameValueConfig config = new AbstractNameValueGatewayFilterFactory.NameValueConfig();
//        config.setName("X-IP");
        config.setName("key");
        return builder.routes()
                .route(t -> t.path("/getremoteip")
                        .and()
                        .uri(Config.getRemoteIPUri)
                )
                .route(r -> r.order(-1)
                        .path("/IP2")
                        .filters(f -> f.filter(new AddRequestIPGatewayFilterFactory().apply(config)))
                        .uri(Config.ip2AaddrUri)
                )

                .route(r -> r.order(-1)
                        .path("/IP")
                        .filters(f -> f.filter(new AddIPRequestParameterGatewayFilterFactory().apply(config)))
                        .uri(Config.ip2AaddrUri)
        )
                .build();

    }

}

