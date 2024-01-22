package mingu.spring.bucket4j.config;


import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.distributed.ExpirationAfterWriteStrategy;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import lombok.extern.slf4j.Slf4j;
import mingu.spring.bucket4j.enums.RatePlan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Slf4j
@Configuration
public class RateLimiterConfig {

    @Value("${bucket.plan}")
    private String bucketPlan;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create(RedisURI.builder()
                .withHost(redisHost)
                .withPort(redisPort)
                .build());
    }

    @Bean
    public LettuceBasedProxyManager<byte[]> lettuceBasedProxyManager() {
        return LettuceBasedProxyManager
                .builderFor(redisClient())
                .withExpirationStrategy(ExpirationAfterWriteStrategy
                        .basedOnTimeForRefillingBucketUpToMax(Duration.ofSeconds(10)))
                .build();
    }

    @Bean
    public BucketConfiguration bucketConfiguration() {
        log.warn("RateLimit Strategy init = {}", bucketPlan);
        return BucketConfiguration.builder()
                .addLimit(RatePlan.resolvePlan(bucketPlan))
                .build();
    }
}
