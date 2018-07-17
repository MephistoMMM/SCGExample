package com.scg.gatewayfilter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


public class AddIPRequestParameterGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory{

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            String ip = exchange.getRequest().getRemoteAddress().getHostName();
            URI uri = exchange.getRequest().getURI();
            StringBuilder query = new StringBuilder();
            String originalQuery = uri.getRawQuery();

            if (StringUtils.hasText(originalQuery)) {
                query.append(originalQuery);
                if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                    query.append('&');
                }
            }
            System.out.println(ip);

            query.append(config.getName());
            query.append('=');
            query.append(ip);

            try {
                URI newUri = UriComponentsBuilder.fromUri(uri)
                        .replaceQuery(query.toString())
                        .build(true)
                        .toUri();

                ServerHttpRequest request = exchange.getRequest().mutate().uri(newUri).build();

                return chain.filter(exchange.mutate().request(request).build());
            } catch (RuntimeException ex) {
                throw new IllegalStateException("Invalid URI query: \"" + query.toString() + "\"");
            }
        };
    }

}
