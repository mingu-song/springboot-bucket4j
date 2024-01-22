package mingu.spring.bucket4j.aop;

import org.aspectj.lang.annotation.Pointcut;

public class ControllerPointCuts {

    @Pointcut("execution(* *..*Controller.*(..))")
    public void allController() {}
}
