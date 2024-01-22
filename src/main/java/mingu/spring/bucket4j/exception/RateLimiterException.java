package mingu.spring.bucket4j.exception;

public class RateLimiterException extends RuntimeException {

    public static final String TOO_MANY_REQUEST = "Too many requests.";
    public static final String NOT_FOUND = "The plan is not exists.";

    public RateLimiterException() {
        super();
    }

    public RateLimiterException(String message) {
        super(message);
    }

    public RateLimiterException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RateLimiterException(Throwable throwable) {
        super(throwable);
    }
}
