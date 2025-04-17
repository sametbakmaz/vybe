package com.example.auth.service.Profile;

import com.example.auth.dto.Profile.ProfileDto;
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
        String userEmail = authentication.getName();

        return userRepository.findByUsernameOrEmail(userEmail, userEmail)
                .map(profileDtoMapper::map)
                .orElse(null); // ya da throw new RuntimeException("User not found");
    }
}