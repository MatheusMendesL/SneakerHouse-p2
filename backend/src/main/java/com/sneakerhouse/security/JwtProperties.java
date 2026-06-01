package com.sneakerhouse.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "sneakerhouse.jwt")
public class JwtProperties {

    private String secret;
    private long expirationMs;
}
