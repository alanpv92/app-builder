package com.appbuilder.appbuilder.utils.helpers;

import com.appbuilder.appbuilder.entity.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityHelper {

   public static String getId(){
       return ((UserEntity) Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal())).getId();
    }
}
