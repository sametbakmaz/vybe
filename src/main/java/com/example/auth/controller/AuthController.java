package com.example.auth.controller;

import com.example.auth.dto.Auth.LoginRequest;
import com.example.auth.dto.Auth.RegisterRequest;
import com.example.auth.dto.HoroscopeResponse;
import com.example.auth.service.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/horoscope")
    public ResponseEntity<HoroscopeResponse> getHoroscope(@RequestParam LocalDate birthDate) {
        return ResponseEntity.ok(authService.calculateHoroscope(birthDate));
    }
} 