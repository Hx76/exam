package com.onlineexam.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.onlineexam.utils.GetClientInfo;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

@Component
public class GlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {
    private static final String SIGN = "hx76";

    public GlobalFilter() throws URISyntaxException {
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(String.valueOf(request.getHeaders()));
        String os = userAgent.getOperatingSystem().getName();

        //操作系统类型
        System.out.println("操作系统："+os);
        String clientType = userAgent.getOperatingSystem().getDeviceType().toString();
        //客户端类型  手机、电脑、平板
        String ip = GetClientInfo.getIpAddr(request);
        System.out.println("ip:"+ip);
        //请求i
        String browser = userAgent.getBrowser().toString();
        System.out.println(clientType+os+browser);
        //浏览器类型
        System.out.println(request.getMethod());
        if (request.getMethod() == HttpMethod.POST) {
            System.out.println("是POST啊");
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        System.out.println(exchange.getRequest().getURI());

        if (token == null){
            System.out.println("没有token");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC384(SIGN)).build().verify(token);
        System.out.println(verify.getClaim("email").asString());
        System.out.println(verify.getClaim("password").asString());
        return verify;
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
