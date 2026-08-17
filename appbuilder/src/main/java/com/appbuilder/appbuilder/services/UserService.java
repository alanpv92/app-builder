package com.appbuilder.appbuilder.services;

import com.appbuilder.appbuilder.dto.auth.UserProfileResponseDto;

public interface UserService {

    UserProfileResponseDto getProfile(String userId);
}
