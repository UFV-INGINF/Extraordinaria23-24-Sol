package com.ufv.dis.front.exceptions;

public class DataException extends Exception{


    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(Throwable cause) {
        super(cause);
    }

    public DataException() {
        super();
    }
}
