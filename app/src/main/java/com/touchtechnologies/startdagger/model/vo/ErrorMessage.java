package com.touchtechnologies.startdagger.model.vo;

/**
 * Created by dev-touch on 4/10/2017.
 */

public class ErrorMessage {

    private int code;
    private String message;
    private Throwable throwable;
    private Exception exception;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
