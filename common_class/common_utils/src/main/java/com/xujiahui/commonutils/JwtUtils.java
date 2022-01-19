package com.xujiahui.commonutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * JTW Utils
 */
public class JwtUtils {
//    设置token的过期时间
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
//    密钥，实际开发时，这个肯定是公司按照自己规则生成的
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /*
       生成token字符串方法
     */
    public static String getJwtToken(String id, String nickname){
//        构建JWT字符串
        String JwtToken = Jwts.builder()
//              第一部分  头
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
//                setSubject随便写，
                .setSubject("xujiahui")
//                设置过期时间
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
//              第二部分  设置token主体部分（也就是用户信息部分）
                .claim("id", id)
                .claim("nickname", nickname)
//              第三部分 签名哈希，根据什么密钥，用什么样的方式进行字符串的编码
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }
        /**
         * 判断token是否存在与有效
         */
    public static boolean checkToken(String jwtToken) {
        if(!StringUtils.hasLength(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

        /**

         * 判断token是否存在与有效
*/
    public static boolean checkToken(HttpServletRequest request) {
//        getHeader判断头里有没有token、
        try {
            String jwtToken = request.getHeader("token");
            if(!StringUtils.hasLength(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
        /**
         * 根据token获取会员id
         */


        public static String getMemberIdByJwtToken(HttpServletRequest request) {
            String jwtToken = request.getHeader("token");
            if(StringUtils.hasLength(jwtToken)!=true) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            Claims claims = claimsJws.getBody();
            return (String)claims.get("id");
        }
    }
