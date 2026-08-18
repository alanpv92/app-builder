package com.appbuilder.appbuilder.services.impl;

import com.appbuilder.appbuilder.dto.auth.UserProfileResponseDto;
import com.appbuilder.appbuilder.services.UserService;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponseDto getProfile(String userId) {
        return null;
    }
}
