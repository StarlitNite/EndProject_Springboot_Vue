package com.endproject.util;

import com.endproject.Model.dto.LoginInfo;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/2/1 16:13
 */
@Component
public class JwtUtil {
    //持续时间
    private static long expireTime ;
    //密钥
    private static String signature;

    @Value("${token.expireTime}")
    public void setTime(long expireTime)  {this.expireTime = expireTime;}
    @Value("${token.signature}")
    public void setSignature(String signature) {this.signature = signature;}

    //创建Token
    public static String CreateJwt(LoginInfo loginInfo){
        JwtBuilder jwtBuilder = Jwts.builder();
        HashMap<String,Object> map = new HashMap<>();
        map.put("username",loginInfo.getUsername());
        map.put("role",loginInfo.getRole_id());
        String jwtToken = jwtBuilder
                //Header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                /*.claim("username","WangNaiLinn")
                .claim("role","admin")*/
                .setClaims(map)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                //拼接
                .compact();
        /*System.out.println(jwtToken);*/
        return jwtToken;
    }
}
