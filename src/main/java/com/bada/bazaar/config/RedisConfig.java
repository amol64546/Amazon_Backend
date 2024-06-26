package com.bada.bazaar.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class RedisConfig implements CachingConfigurer {

  @Override
  public CacheErrorHandler errorHandler() {
    return new RedisCacheErrorHandler();
  }

}
