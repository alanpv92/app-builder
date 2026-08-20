package com.appbuilder.appbuilder.utils.mappers;

import com.appbuilder.appbuilder.dto.auth.AuthResponseDto;
import com.appbuilder.appbuilder.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthMapper {

    private final UserMapper userMapper;

    public AuthResponseDto fromUserEntity(String token,UserEntity userEntity) {
        return AuthResponseDto.builder()
                .token(token)
                .userProfileDto(userMapper.fromUserEntity(userEntity))
                .build();
    }
}
