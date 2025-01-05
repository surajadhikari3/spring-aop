package io.reactivestax.rateLimiting.retry.exceptions;

public class RetryException extends RuntimeException{
    public RetryException(String message, Throwable cause){
        super(message, cause);
    }
}
