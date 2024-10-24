package com.devhub.website.io.vn.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.devhub.website.io.vn.service.JwtUserDetailsService;

import java.util.Date;


@Component
public class JwtProvider {

    private final String secretKey = "mySecretKey"; // Khóa bí mật để mã hóa token
    private final long validityInMilliseconds = 3600000; // Thời gian tồn tại của token (1 giờ)

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService; // Tiêm `JwtUserDetailsService`

    // Tạo token với tên người dùng
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username); // Đặt claims với username
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Lấy đối tượng Authentication từ token
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(getUsername(token)); // Lấy thông tin người dùng
        return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Lấy tên người dùng từ token
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Giải mã token từ request
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // Xác thực token
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date()); // Kiểm tra ngày hết hạn
        } catch (Exception e) {
            throw e;
        }
    }
}