//package com.auto_optimal_mortgage.autoOptimalMortgageApplication.core;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.CacheManager;
//import org.springframework.data.redis.cache.RedisCacheManager;
//
//@Configuration
//@EnableCaching
//public class RedisConfig {
//
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        return RedisCacheManager.builder(redisTemplate.getConnectionFactory()).build();
//    }
//}