package com.appbuilder.appbuilder.utils.mappers;

import com.appbuilder.appbuilder.dto.auth.UserProfileResponseDto;
import com.appbuilder.appbuilder.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserProfileResponseDto fromUserEntity(UserEntity userEntity) {
        return UserProfileResponseDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .avatarUrl(userEntity.getAvatarUrl())
                .name(userEntity.getName())
                .build();
    }
}
