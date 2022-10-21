package com.cong.back_end.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cong.back_end.pojo.User;

import java.util.Date;

/**
 * @Author Cong
 * @Create_time 2022-10-16 下午 08:13
 * @Project_name back_end
 */
public class TokenUtils {
    
    // token到期时间为2小时
    private static final long EXPIRE_TIME = 2*60*60*1000;
    // refreshToken到期时间为2天
    private static final long EXPIRE_TIME_REFRESH = 2*24*60*60*1000;
    // 密钥盐
    private static final String TOKEN_SECRET = "lyc**liu1??yun2??cong3??";
    
    /**
     * 生成token
     * @param user
     * @return
     */
    public static String sign(User user) {
        String token = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("Cong")
                    .withClaim("username", user.getUsername())
                    .withClaim("Header", "Access")
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {
            System.out.println(je);
        }
        return token;
    }
    
    /**
     * 生成refreshToken
     * @param user
     * @return
     */
    public static String signForRefresh(User user) {
        String refreshToken = null;
        try {
            Date expireAt = new Date(System.currentTimeMillis()+EXPIRE_TIME_REFRESH);
            refreshToken = JWT.create()
                    .withIssuer("Cong")
                    .withClaim("username", user.getUsername())
                    .withClaim("Header", "Refresh")
                    .withExpiresAt(expireAt)
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (IllegalArgumentException | JWTCreationException je) {
            System.out.println(je);
        }
        return refreshToken;
    }
    
    /**
     * token验证
     * @param token
     * @return
     */
    public static Boolean verify(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer("Cong").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("username: " + decodedJWT.getClaim("username").asString());
            System.out.println("过期时间：\t" + decodedJWT.getExpiresAt());
        } catch (IllegalArgumentException | JWTVerificationException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    /**
     * 通过refreshToken生成新的token
     * @param refreshToken
     * @return
     */
    public static String getNewTokenByRefreshToken(String refreshToken) {
        String token = null;
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer("Cong").build();
            DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
            User temp = new User();
            temp.setUsername(decodedJWT.getClaim("username").asString());
            token = sign(temp);
        } catch (IllegalArgumentException | JWTVerificationException e) {
            System.out.println(e);
        }
        return token;
    }
}
