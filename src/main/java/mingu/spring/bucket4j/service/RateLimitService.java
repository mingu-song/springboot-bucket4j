package mingu.spring.bucket4j.service;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import lombok.RequiredArgsConstructor;
import mingu.spring.bucket4j.exception.RateLimiterException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class RateLimitService {

    private final BucketConfiguration bucketConfiguration;
    private final LettuceBasedProxyManager<byte[]> lettuceBasedProxyManager;

    private Bucket bucket(String key) {
        return lettuceBasedProxyManager.builder()
                .build(key.getBytes(StandardCharsets.UTF_8), () -> bucketConfiguration);
    }

    public boolean checkBucketCounter(String key) {
        Bucket bucket = bucket(key);
        if (!bucket.tryConsume(1))
            throw new RateLimiterException(RateLimiterException.TOO_MANY_REQUEST);
        return true;
    }

    public long getAvailableTokens(String key) {
        return bucket(key).getAvailableTokens();
    }
}
