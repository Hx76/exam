package com.onlineexam.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager")DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //没有认证后跳到的页面
        factoryBean.setLoginUrl("/login");
        //设置安全管理器
        factoryBean.setSecurityManager(manager);
        return factoryBean;
    }
    //DefaultWebSecurityManger
    @Bean(name = "manager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
        return manager;
    }
    //创建realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
