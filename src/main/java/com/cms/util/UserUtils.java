package com.cms.util;

import org.springframework.security.core.context.SecurityContextHolder;

public final class UserUtils {

    private UserUtils(){

    }

    public static String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
