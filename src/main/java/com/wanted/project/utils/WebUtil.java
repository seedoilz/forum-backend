package com.wanted.project.utils;

import com.wanted.project.core.ServiceException;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
    public static Long getCurrentId(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if(!StringUtils.hasText(authorization)){
            return -1L;
        }
        String token = authorization.split(" ")[1];//从请求头获取token
        //解析token
        String id;
        try{
            Claims claims = JwtUtil.parseJWT(token);
            id = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("token非法");
        }
        return Long.parseLong(id);
    }
}
