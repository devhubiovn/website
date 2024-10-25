package com.devhub.website.io.vn.jwt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.List;

import com.devhub.website.io.vn.jwt.helper.JwtHelper;
import com.devhub.website.io.vn.jwt.plugins.request.AuthenRequest;
import com.devhub.website.io.vn.jwt.plugins.response.BaseResponse;
import com.devhub.website.io.vn.jwt.service.AuthenService;

@RestController
@RequestMapping("/authen")
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper; //

    @PostMapping
    public ResponseEntity<?>authen(@Valid @RequestBody AuthenRequest authenRequest) throws JsonProcessingException {

//        // Tạo key
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        // Biến key thành chuỗi để lưu trữ
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());

//        boolean isSuccess = authenService.checkLogin(authenRequest);

        UsernamePasswordAuthenticationToken authenToken =
                new UsernamePasswordAuthenticationToken(authenRequest.email(),authenRequest.password());

        Authentication authentication = authenticationManager.authenticate(authenToken);

        List<GrantedAuthority> listRole = (List<GrantedAuthority>) authentication.getAuthorities();

        String data = objectMapper.writeValueAsString(listRole);
        System.out.println("kiemtra " + data);
        String token = jwtHelper.generateToken(data);

        BaseResponse response = new BaseResponse();
        response.setData(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAuthen(){

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}