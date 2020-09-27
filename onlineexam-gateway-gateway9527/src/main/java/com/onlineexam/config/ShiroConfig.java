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
        factoryBean.setLoginUrl("/toLogin");
        /**
         * shiro的内置过滤器
         anon：无需认证就可以访问 默认
         authc：必须认证了才能访问
         user：必须拥有记住我功能才能访问
         perms：必须拥有对某个的权限才能访问
         role：拥有某个角色权限才能访问
         */
        //添加shiro的内置过滤器  设置要拦截的url
        //拦截
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();
        // /add请求必须认证才能访问
        filterChainDefinitionMap.put("/add","authc");
        //del必须认证才能访问
        filterChainDefinitionMap.put("/del","authc");
        //支持通配符
        // filterChainDefinitionMap.put("user/**","authc");
        //授权
        //没有这个user:add权限的会被拦截下来
        filterChainDefinitionMap.put("/add","perms[user:teacher]");
        //没有这个user:delete权限的会被拦截下来
        filterChainDefinitionMap.put("/del","perms[user:student]");
        //未授权的跳转的url
        factoryBean.setUnauthorizedUrl("/Unauthorized");
        //设置注销的url
        //把设置好的过滤设置到ShiroFilterFactoryBean
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
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
