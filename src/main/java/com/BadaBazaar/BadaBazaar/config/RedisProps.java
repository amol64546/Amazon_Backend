package com.BadaBazaar.BadaBazaar.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.redis", ignoreInvalidFields = true)
public class RedisProps {

    private String host;
    private int port;

}

