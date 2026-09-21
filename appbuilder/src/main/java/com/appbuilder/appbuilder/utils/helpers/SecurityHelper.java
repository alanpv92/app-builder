package com.appbuilder.appbuilder.utils.helpers;

import com.appbuilder.appbuilder.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityHelper {



    public static  UserEntity getCurrentUser() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

   public static String getId(){
       return SecurityHelper.getCurrentUser().getId();
    }

    public static String getEmail(){
       return SecurityHelper.getCurrentUser().getEmail();
    }
}
