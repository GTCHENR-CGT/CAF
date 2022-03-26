package com.gtchenr.utils;


import com.alibaba.fastjson.JSON;
import com.gtchenr.pojo.Role;
import com.gtchenr.pojo.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author gtchenr
 * @Date 2022/3/25 17:27
 * @Description |使用HS256算法生成签名signature的jws，在这个utils中，因为设置私有声明会导致公有声明无法设置，所以必须用两个token,一个存储用户的
 *              |的登录认证，一个存储用户的信息。
 * @Since version-1.0
 */
public class TokenUtils {

    private static final String KEY = "78b5ae63a1d14bef89957faf0a49e09e";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
    private static final Integer TIME = 2;//token有效时间,单位min
    /**
     * 获取包含用户信息的token，
     * @param user
     * @return
     */
    public static String getAccessToken(User user){
        Date date = new Date();
        Map<String,Object> map = new HashMap<>();
        map.put("userId",user.getUserId());
        map.put("loginName",user.getLoginName());
        map.put("role",user.getRole());
        String jws = Jwts.builder()
                .setClaims(map)
                .signWith(SECRET_KEY,SignatureAlgorithm.HS256)
                .compact();
        return jws;
    }

    /**
     * 解析token，验证token是否被修改过，以及是否超过验证时间，如果超过验证时间或者不通过验证则返回null，如果
     * 解析成功，返回一个User对象.只有在通过refreshToken验证过后才能使用accessToken,否则不允许解析accessToken
     * 判断refreshToken验证通过的条件是refreshToken能被解析，并且要满足和accessToken某个一样的条件，如果不满足，则不允许解析。
     * @param accessToken
     * @param refreshToken
     * @return
     */
    public static User parseAccessToken(String accessToken , String refreshToken){

        if(!parseRefreshToken(refreshToken))
            return null;

        User user = new User();
        Claims body;
        try {
            body = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        }catch (Exception e){
            return null;
        }
        //body获取到的是一个map对象，转成json格式再转会原先格式
        System.out.println(body.getSubject());
        String roleJson = JSON.toJSONString(body.get("role"));
        Role role = JSON.parseObject(roleJson,Role.class);
        user.setRole(role);
        user.setUserId((Integer) body.get("userId"));
        user.setLoginName((String) body.get("loginName"));
        return user;
    }

    /**
     * 获取用户登录的验证token
     * @param user
     * @return
     */
    public static String getRefreshToken(User user){
        Date date = new Date();
        String jws = Jwts.builder()
                .setSubject("gtchenr")
                .setAudience(user.getName())
                .setExpiration(DateUtils.addMinutes(date,TIME))
                .setNotBefore(date)
                .setIssuedAt(date)
                .setId(UUID.randomUUID().toString())
                .signWith(SECRET_KEY,SignatureAlgorithm.HS256)
                .compact();
        return jws;
    }


    public static boolean parseRefreshToken(String jws){
        try {
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(jws)
                    .getBody();
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
