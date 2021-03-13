package com.onlineexam.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.net.URI;
import java.net.URISyntaxException;

@Component
public class GlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {
    public GlobalFilter() throws URISyntaxException {
    }

    public Boolean ifPermission(URI uri){
        String uri1 = uri.toString();
        System.out.println(uri1);
        String[] words = uri1.split("/");
        for (String word : words) {
            System.out.println(word);
            if (word == "login"||word == "register");
            return true;
        }
        return false;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        System.out.println(request.getMethod());
        if (request.getMethod() == HttpMethod.POST) {
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        System.out.println(exchange.getRequest().getURI());
        if (ifPermission(exchange.getRequest().getURI())){
            return chain.filter(exchange);
        }
        if (token == null){
            System.out.println("没有token");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 加载过滤器的顺序，越小越靠前
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
