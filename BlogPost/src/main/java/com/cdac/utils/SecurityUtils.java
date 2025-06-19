//package com.cdac.utils;
//
//import com.cdac.security.BlogUserDetails;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.UUID;
//
//public class SecurityUtils {
//
//    public static UUID getCurrentUserId() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication);
//        if (authentication != null && authentication.getPrincipal() instanceof BlogUserDetails userDetails) {
//            return userDetails.getId();
//        }
//        throw new RuntimeException("Unauthenticated user");
//    }
//}
