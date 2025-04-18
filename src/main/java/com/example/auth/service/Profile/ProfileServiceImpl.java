package com.example.auth.service.Profile;

import com.example.auth.dto.Profile.ProfileDto;
import com.example.auth.entity.UserEntity;
import com.example.auth.mapper.ProfileDtoMapper;
import com.example.auth.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final ProfileDtoMapper profileDtoMapper;

    public ProfileServiceImpl(UserRepository userRepository, ProfileDtoMapper profileDtoMapper) {
        this.userRepository = userRepository;
        this.profileDtoMapper = profileDtoMapper;
    }

    @Override
    public ProfileDto getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || 
            authentication.getName().equals("anonymousUser")) {
            return null; // Kullanıcı kimlik doğrulaması yapmamış
        }
        
        String userIdentifier = authentication.getName();
        
        return userRepository.findByUsernameOrEmail(userIdentifier, userIdentifier)
                .map(profileDtoMapper::map)
                .orElse(null);
    }
}