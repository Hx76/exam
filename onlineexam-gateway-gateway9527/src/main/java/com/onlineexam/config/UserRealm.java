//package com.onlineexam.config;
//
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.subject.Subject;
//
//import javax.annotation.Resource;
//
//public class UserRealm extends AuthorizingRealm {
//    @Resource
//    //private ShiroService shiroService;
//    //授权
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        System.out.println("执行了授权");
//        SimpleAuthorizationInfo simpInfo = new SimpleAuthorizationInfo();
//        //获取当前用户的对象
//        Subject subject= SecurityUtils.getSubject();
//        //获取用户信息
//        //User user = (User)subject.getPrincipal();
//        //获取数据库权限
//        //simpInfo.addStringPermission(user.getPerm());
//        return simpInfo;
//    }
//    //认证
////    @Override
////    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
////        System.out.println("执行了认证");
////        //获取当前的用户
////        Subject subject = SecurityUtils.getSubject();
////        //获取登录的信息
////        UsernamePasswordToken userToken=(UsernamePasswordToken)authenticationToken;
////        //获取用户名 密码  数据库取
////        System.out.println(userToken.getUsername());
//////        User query = shiroService.queryByEmail(userToken.getUsername());
//////        System.out.println(query);
//////        //没有这个用户
//////        if(query==null){
//////            return null;
//////        }
//////        //获取用户的session
//////        Session session=subject.getSession();
//////        //session.setAttribute("loginuser",query);
//////        //判断登录的用户名密码 匹配数据库是否正确
//////        if(!userToken.getUsername().equals(query.getEmail())){
//////            //抛出异常
//////            return null;
//////        }
//////        //密码认证，shiro做
////        return new SimpleAuthenticationInfo(query,query.getPassword(),"");
////    }
//}
