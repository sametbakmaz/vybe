package com.example.auth.controller;

import com.example.auth.dto.Auth.LoginRequest;
import com.example.auth.dto.Auth.RegisterRequest;
import com.example.auth.dto.HoroscopeResponse;
import com.example.auth.service.Auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Kimlik doğrulama işlemleri için API endpoint'leri")
public class AuthController {
    
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(
        summary = "Yeni kullanıcı kaydı",
        description = "Sistemde yeni bir kullanıcı hesabı oluşturur",
        responses = {
            @ApiResponse(responseCode = "200", description = "Kullanıcı başarıyla kaydedildi"),
            @ApiResponse(responseCode = "400", description = "Geçersiz istek veya kullanıcı zaten mevcut")
        }
    )
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(
        summary = "Kullanıcı girişi",
        description = "Mevcut kullanıcı hesabı ile giriş yapar",
        responses = {
            @ApiResponse(responseCode = "200", description = "Giriş başarılı"),
            @ApiResponse(responseCode = "401", description = "Geçersiz kimlik bilgileri")
        }
    )
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/horoscope")
    @Operation(
        summary = "Burç hesaplama",
        description = "Doğum tarihine göre burç hesaplar",
        responses = {
            @ApiResponse(responseCode = "200", description = "Burç başarıyla hesaplandı"),
            @ApiResponse(responseCode = "400", description = "Geçersiz doğum tarihi")
        }
    )
    public ResponseEntity<HoroscopeResponse> getHoroscope(@RequestParam LocalDate birthDate) {
        return ResponseEntity.ok(authService.calculateHoroscope(birthDate));
    }
} 