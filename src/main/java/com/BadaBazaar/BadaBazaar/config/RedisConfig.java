package com.BadaBazaar.BadaBazaar.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class RedisConfig implements CachingConfigurer {

  private final RedisProps redisProps;

  @Override
  public CacheErrorHandler errorHandler() {
    return new RedisCacheErrorHandler();
  }

  @Bean
  public LettuceConnectionFactory getLettuceConnectionFactory() {
    RedisStandaloneConfiguration conf = new
      RedisStandaloneConfiguration(redisProps.getHost(), redisProps.getPort());
    return new LettuceConnectionFactory(conf);

  }

//  @Bean
//  public RedisTemplate<String, Object> redisTemplate() {
//    log.info("-----redisTemplate bean creation-----");
//    final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//    redisTemplate.setKeySerializer(new StringRedisSerializer());
//    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//    redisTemplate.setConnectionFactory(getLettuceConnectionFactory());
//    return redisTemplate;
//  }
//
//  @Bean
//  public CacheManager cacheManager() {
//    RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//        .disableCachingNullValues();
//    return RedisCacheManager.builder(getLettuceConnectionFactory())
//        .cacheDefaults(cacheConfiguration).build();
//  }
}
