package com.example.auth.service.Auth;

import com.example.auth.dto.Auth.AuthResponse;
import com.example.auth.dto.HoroscopeResponse;
import com.example.auth.dto.Auth.LoginRequest;
import com.example.auth.dto.Auth.RegisterRequest;
import com.example.auth.entity.Gender;
import com.example.auth.entity.UserEntity;
import com.example.auth.entity.HoroscopeEntity;
import com.example.auth.repository.UserRepository;
import com.example.auth.repository.HoroscopeRepository;
import com.example.auth.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final HoroscopeRepository horoscopeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UserRepository userRepository, 
                      HoroscopeRepository horoscopeRepository,
                      PasswordEncoder passwordEncoder,
                      JwtService jwtService) {
        this.userRepository = userRepository;
        this.horoscopeRepository = horoscopeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        // Şifre kontrolü
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Şifreler eşleşmiyor!");
        }

        // Kullanıcı adı ve email kontrolü
        if (userRepository.existsByUsernameOrEmail(request.getUsername(), request.getEmail())) {
            throw new RuntimeException("Bu kullanıcı adı veya email zaten kullanımda!");
        }

        // Burç hesaplama
        HoroscopeResponse horoscopeResponse = calculateHoroscope(request.getBirthDate());
        HoroscopeEntity horoscopeEntity = horoscopeRepository.findById(horoscopeResponse.getId())
                .orElseThrow(() -> new RuntimeException("Burç bulunamadı!"));

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setEmail(request.getEmail());
        userEntity.setBirthDate(request.getBirthDate());
        userEntity.setGender(Gender.valueOf(request.getGender().toUpperCase()));
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setProfilePhoto(request.getProfilePhoto());
        userEntity.setBiography(request.getBiography());
        userEntity.setHoroscope(horoscopeEntity);

        userRepository.save(userEntity);

        String token = jwtService.generateToken(userEntity);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        UserEntity userEntity = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(),
                request.getUsernameOrEmail())
                .orElseThrow(() -> new BadCredentialsException("Kullanıcı bulunamadı!"));

        if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
            throw new BadCredentialsException("Hatalı şifre!");
        }

        String token = jwtService.generateToken(userEntity);
        return new AuthResponse(token);
    }

    public HoroscopeResponse calculateHoroscope(LocalDate birthDate) {
        int month = birthDate.getMonthValue();
        int day = birthDate.getDayOfMonth();
        
        HoroscopeEntity horoscopeEntity = horoscopeRepository.findByMonthAndDay(month, day)
                .orElseThrow(() -> new RuntimeException("Burç hesaplanamadı!"));
        
        return new HoroscopeResponse(horoscopeEntity.getId(), horoscopeEntity.getName());
    }
} 