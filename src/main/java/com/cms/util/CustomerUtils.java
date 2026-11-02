package com.cms.util;

import org.springframework.security.core.context.SecurityContextHolder;

public final class CustomerUtils {

    private CustomerUtils(){

    }

    public static String getCurrentCustomer(){
        return  SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
