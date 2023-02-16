package com.example.psi.commons.security;

import org.springframework.security.core.Authentication;

public interface JwtUtil {

    String generateJwtToken(Authentication authentication);

    String getUserNameFromJwtToken(String token);

}
