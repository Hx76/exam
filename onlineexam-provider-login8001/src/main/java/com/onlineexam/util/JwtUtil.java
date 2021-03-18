package com.onlineexam.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {
    private static final String SIGN = "hx76";

    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        //设置令牌失效时间,默认7天过期
        instance.add(Calendar.DATE,7);
        //创建jwt builder
        final JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC384(SIGN));
        System.out.println(token);
        return token;
    }

    /**
     * 验证token
     */
    public static void verifyToken(String token){
        JWT.require(Algorithm.HMAC384(SIGN)).build().verify(token);
    }

    /**
     * 获取token信息
     */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC384(SIGN)).build().verify(token);
        verify.getClaim("email");
        verify.getClaim("password");
        return verify;
    }
}
