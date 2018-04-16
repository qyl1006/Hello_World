package cn.wolfcode.wms.util;

public class MySecurityException extends RuntimeException {
    public MySecurityException() {
        super();
    }

    public MySecurityException(String message) {
        super(message);
    }

    /**
     *
     * @param message 当前异常的原因/信息
     * @param cause  当前异常的根本原因
     */
    public MySecurityException(String message, Throwable cause) {
        super(message, cause);
    }


}
