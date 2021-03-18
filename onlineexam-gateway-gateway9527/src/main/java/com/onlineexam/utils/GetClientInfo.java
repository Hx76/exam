package com.onlineexam.utils;

import com.netflix.client.http.HttpHeaders;
import com.netflix.client.http.HttpRequest;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.http.server.reactive.ServerHttpRequest;

public class GetClientInfo {
        public static String getIpAddr(ServerHttpRequest request) {

            String ip = request.getHeaders().getFirst("x-forwarded-for");

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("X-Real-IP");
                //LOGGER.error("X-Real-IP:"+ip);
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("http_client_ip");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddress().toString();

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");

            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("HTTP_X_FORWARDED_FOR");
            }
       // 如果是多级代理，那么取第一个ip为客户ip
            if (ip != null && ip.indexOf(",") != -1) {
                ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
//            LOGGER.error("ip:"+ip);
            }
            return ip;
        }
}
