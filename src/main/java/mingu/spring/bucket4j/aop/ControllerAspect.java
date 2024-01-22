package mingu.spring.bucket4j.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mingu.spring.bucket4j.service.RateLimitService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerAspect {

    private final RateLimitService rateLimitService;

    @Around("mingu.spring.bucket4j.aop.ControllerPointCuts.allController() && args(id)")
    public Object doController(ProceedingJoinPoint joinPoint, String id) throws Throwable {
        try {
            log.warn("[AOP] before controller : {}", joinPoint.getTarget().getClass());
            rateLimitService.checkBucketCounter(id);
        } catch (Exception e) {
            log.error("[AOP] exception", e);
            throw e;
        }
        return joinPoint.proceed();
    }
}
