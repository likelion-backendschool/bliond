package com.likelion.bliond.domain.question.exception;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException() {
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionNotFoundException(Throwable cause) {
        super(cause);
    }

    protected QuestionNotFoundException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
