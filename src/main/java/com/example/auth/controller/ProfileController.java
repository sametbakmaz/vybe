package com.example.auth.controller;

import com.example.auth.dto.Profile.ProfileDto;
import com.example.auth.service.Profile.ProfileServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@Tag(name = "Profile", description = "Kullanıcı profili işlemleri için API endpoint'leri")
public class ProfileController {

    private final ProfileServiceImpl profileService;

    public ProfileController(ProfileServiceImpl profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    @Operation(
        summary = "Kullanıcı profilini getir",
        description = "Oturum açmış kullanıcının profilini getirir",
        security = { @SecurityRequirement(name = "bearerAuth") },
        responses = {
            @ApiResponse(responseCode = "200", description = "Profil başarıyla getirildi"),
            @ApiResponse(responseCode = "401", description = "Kimlik doğrulama yapılmamış"),
            @ApiResponse(responseCode = "404", description = "Profil bulunamadı")
        }
    )
    public ResponseEntity<ProfileDto> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || 
            authentication.getName().equals("anonymousUser")) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        
        ProfileDto profile = profileService.getUserProfile();
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profile);
    }
}