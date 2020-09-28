package com.onlineexam.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class QuestionFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getEmail(), user.getPassword());
        //执行登录的方法 没有异常就成功了
        subject.login(usernamePasswordToken);
        return chain.filter(exchange);
    }

    /**
     * 过滤器的级别
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
