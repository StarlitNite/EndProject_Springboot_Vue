package com.endproject.util;

import com.endproject.Model.dto.LoginInfo;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
        map.put("userNumber",loginInfo.getSnum());
        map.put("role",loginInfo.getRole_id());
        String jwtToken = jwtBuilder
                //Header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
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

    /**
    * @Description:Token解析
    * @date 2023/2/26 15:59
    * @author WangNaiLinn
    **/

    public static Object parse(String token){

        JwtParser jwtParser = Jwts.parser();
        System.out.println(expireTime);
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Object role_id = claims.get("role");
        Object snum = claims.get("snum");
        return role_id;
    }

}
