package com.myweb.service;

import com.myweb.dto.AuthenticateDTO;
import com.myweb.dto.LoginDTO;
import com.myweb.dto.UserDTO;
import com.myweb.entity.User;
import com.myweb.mapper.UserMapper;
import com.myweb.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserDTO authenticate(LoginDTO loginDTO){
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(()-> new RuntimeException("User not found!"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        if(!authenticated){
            throw new RuntimeException("Authentication failed");
        }
        else{
            return userMapper.toUserDto(user);
        }
    }


//    @NonFinal
//    @Value("${jwt.signer-key}")
//    protected String SIGNER_KEY;

//    public AuthenticateDTO authenticate(LoginDTO loginDto){
//        User user =  userRepository.findByUsername(loginDto.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not existed!"));
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//
//        boolean authenticated = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
//        if(!authenticated){
//            throw new RuntimeException("Authencate failed");
//        }
//        var token = generateToken(loginDto.getUsername());
//        return AuthenticateDTO.builder()
//                .token(token)
//                .authenticated(true)
//                .build();
//    }

//    public String generateToken(String username){
//            return Jwts.builder()
//                    .setSubject(username)
//                    .setIssuer("quanvjppro.com")
//                    .setIssuedAt(new Date()) // Thời gian phát hành là thời gian hiện tại
//                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Hết hạn sau 10 giờ
//                    .signWith(SignatureAlgorithm.HS512, SIGNER_KEY)
//                    .compact();
//
//    }



}
