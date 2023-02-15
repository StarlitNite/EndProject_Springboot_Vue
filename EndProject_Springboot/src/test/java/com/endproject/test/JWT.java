/*
package com.endproject.test;

import com.endproject.Model.dto.LoginInfo;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

*/
/**
 * @author WangNaiLinn
 * @Description:
 * @date create in 2023/1/30 4:55
 *//*


public class JWT {

    private long expireTime = 1000*60*30 ;//

    private String signature = "WangNaiLinn";//密钥

   */
/* @Value("${token.expireTime}")
    public void setTime(long time)  {this.expireTime = time;}
    @Value("${token.signature}")
    public void setSignature(String signature) {this.signature = signature;}*//*


    public void CreateJwt(LoginInfo loginInfo){
        JwtBuilder jwtBuilder = Jwts.builder();
        HashMap<String,Object> map = new HashMap<>();
        map.put("username",loginInfo.getUsername());
        map.put("role",loginInfo.getRole_id());
        String jwtToken = jwtBuilder
                //Header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //payload
                */
/*.claim("username","WangNaiLinn")
                .claim("role","admin")*//*

                .setClaims(map)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.println(jwtToken);
    }

    @Test
    public void parse(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbi10ZXN0Iiwicm9sZSI6MywiZXhwIjoxNjc1MjQxODI2LCJqdGkiOiJhZDhmMGE0ZC04Yzc3LTQ3NWYtYmJlOC1mMGUyMzMxODg5MWUiLCJ1c2VybmFtZSI6ImFkbWluIn0.j-4MeaMItVCK6bzxAx4Qh2lrnLCXmC-H8puxvv9gezc";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        //System.out.println(claims);
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());




    }

    @Test
    public void test1(){
        LoginInfo map = new LoginInfo();
        map.setUsername("admin");
        map.setRole_id(3);
       CreateJwt(map);
    }
}
*/
