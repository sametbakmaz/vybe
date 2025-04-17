package com.example.auth.mapper;

import com.example.auth.dto.Profile.ProfileDto;
import com.example.auth.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfileDtoMapper {

    public ProfileDto map(UserEntity entity) {
        ProfileDto dto = new ProfileDto();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setBirthDate(entity.getBirthDate());
        dto.setHoroscopeName(entity.getHoroscope() != null ? entity.getHoroscope().getName() : null);
        dto.setGender(entity.getGender() != null ? entity.getGender().name() : null);
        dto.setProfilePhoto(entity.getProfilePhoto());
        dto.setBiography(entity.getBiography());
        dto.setPremium(entity.isPremium());
        dto.setVerified(entity.isVerified());

        return dto;
    }
}