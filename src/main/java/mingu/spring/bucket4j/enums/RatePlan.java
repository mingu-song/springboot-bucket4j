package mingu.spring.bucket4j.enums;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BandwidthBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mingu.spring.bucket4j.exception.RateLimiterException;

import java.time.Duration;

@Getter
@RequiredArgsConstructor
public enum RatePlan {

    TEST("test") {
        @Override
        public Bandwidth getLimit() {
            return BandwidthBuilder.builder()
                    .capacity(100)
                    .refillIntervally(100, Duration.ofMinutes(1))
                    .build();
        }
    },
    LOCAL("local") {
        @Override
        public Bandwidth getLimit() {
            return BandwidthBuilder.builder()
                    .capacity(5)
                    .refillIntervally(5, Duration.ofMinutes(1))
                    .build();
        }
    };

    public abstract Bandwidth getLimit();

    private final String planName;

    public static Bandwidth resolvePlan(String targetPlan) {
        try {
            return RatePlan.valueOf(targetPlan.toUpperCase()).getLimit();
        } catch (Exception e) {
            throw new RateLimiterException(RateLimiterException.NOT_FOUND);
        }
    }

}
