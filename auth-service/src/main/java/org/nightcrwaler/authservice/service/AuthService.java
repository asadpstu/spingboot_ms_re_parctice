package org.nightcrwaler.authservice.service;

import org.nightcrwaler.authservice.entity.UserCredential;
import org.nightcrwaler.authservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public UserCredential saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        return repository.save(credential);
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public String generateRefreshToken(String username) {
        return jwtService.generateRefreshToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}