package com.zerobase.fastlms.util;


import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class RequestUtils {

    public static String getUserAgent(HttpServletRequest request) {

        return request.getHeader("User-Agent").toString();
    }

    public static String getUserIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");

        if(ip == null || ip.length() == 0) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 ) {
            ip = request.getHeader("WL-Proxy=Client-IP");
        }

        if(ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }

        return ip;

    }
}
