package cn.wolfcode.wms.util;

public class MySecurityException extends RuntimeException {
    public MySecurityException() {
    }

    public MySecurityException(String message) {
        super(message);
    }

    public MySecurityException(Throwable cause) {
        super(cause);
    }
}
