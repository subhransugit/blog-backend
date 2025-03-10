package com.blog.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class JwtConfig {
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expirationMs}")
    private long expirationMs;

}
