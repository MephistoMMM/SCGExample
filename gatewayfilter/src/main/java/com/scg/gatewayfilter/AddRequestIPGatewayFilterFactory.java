package com.scg.gatewayfilter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;

public class AddRequestIPGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(AbstractNameValueGatewayFilterFactory.NameValueConfig config) {
        return (exchange, chain) -> {
            String ip = exchange.getRequest().getRemoteAddress().getHostName();
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(config.getName(), ip)
                    .build();

            System.out.println(ip);

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

}
