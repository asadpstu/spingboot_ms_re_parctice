package org.nightcrwaler.authservice.controller;

import org.nightcrwaler.authservice.dto.AuthRequest;
import org.nightcrwaler.authservice.dto.CreateUser;
import org.nightcrwaler.authservice.dto.LoginResponseDto;
import org.nightcrwaler.authservice.entity.UserCredential;
import org.nightcrwaler.authservice.service.AuthService;
import org.nightcrwaler.authservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private  AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    @PostMapping("/register")
    public UserCredential addUser(@RequestBody CreateUser user) {
        UserCredential userCredential = new UserCredential();
        userCredential.setName(user.getName());
        userCredential.setPassword(user.getPassword());
        userCredential.setEmail(user.getEmail());
        return authService.saveUser(userCredential);
    }


    @PostMapping("/token")
    public ResponseEntity<LoginResponseDto> getToken(@RequestBody AuthRequest payload) {
        // Bypassing email
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword()));
        if(authentication.isAuthenticated()) {
            String token =  authService.generateToken(payload.getEmail());
            String refreshToken =  authService.generateRefreshToken (payload.getEmail());
            return new ResponseEntity<>(new LoginResponseDto(token, refreshToken), HttpStatus.OK);
        }
        throw new BadCredentialsException("Invalid username or password");

    }


    @GetMapping("/validate")
    public  String validateToekn(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponseDto> refreshAccessToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(new LoginResponseDto(null, null), HttpStatus.BAD_REQUEST);
        }

        String refreshToken = authHeader.substring(7);
        authService.validateToken(refreshToken);
        String userEmail = jwtService.extractUserEmail(refreshToken);
        String newAccessToken = jwtService.generateToken(userEmail);
        String newRefreshToken = jwtService.generateRefreshToken(userEmail);
        return ResponseEntity.ok(new LoginResponseDto(newAccessToken, newRefreshToken));
    }
}
