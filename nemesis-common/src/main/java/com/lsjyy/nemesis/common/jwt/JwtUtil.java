package com.lsjyy.nemesis.common.jwt;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.lsjyy.nemesis.common.security.DESCoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * @Authoer LsjYy
 * @DATE 2020-02-09 9:41
 * @Description: Jwt工具类
 */
public class JwtUtil {
    private static final String KEY = "";


    /**
     * 创建秘钥
     *
     * @return
     * @throws Exception
     */
    public static SecretKey generalKey() throws Exception {
        byte[] encodedKey = DESCoder.encrypt(KEY.getBytes(), DESCoder.DES_KEY);
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey key = generalKey();  //签名秘钥，和生成的签名的秘钥一模一样
        Claims claims = Jwts.parser()  //得到DefaultJwtParser
                .setSigningKey(key)                 //设置签名的秘钥
                .parseClaimsJws(jwt).getBody();     //设置需要解析的jwt
        return claims;
    }

    /**
     * 得到token
     *
     * @param claims
     * @return
     */
    public static Token generateToken(Map<String, Object> claims) throws Exception {
        Token token = new Token();
        SecretKey secret = generalKey();
        long now = System.currentTimeMillis();
        // 下面就是在为payload添加各种标准声明和私有声明了
        String accessToken = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims) // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setExpiration(new Date(now + (long) 1000 * 60 * 60 * 24)) //设置过期时间
                .signWith(SignatureAlgorithm.HS512, secret) //// 设置签名使用的签名算法和签名使用的秘钥,不一定非要采用HS512
                .compact();

        token.setAccessToken(accessToken);
        token.setExpTime(new Timestamp(now + (long) 1000 * 60 * 60 * 24));
        return token;
    }
}
