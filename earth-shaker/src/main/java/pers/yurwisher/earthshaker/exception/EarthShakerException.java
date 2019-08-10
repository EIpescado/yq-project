package pers.yurwisher.earthshaker.exception;

/**
 * @author yq
 * @date 2019/08/08 16:00
 * @description 异常
 * @since V1.0.0
 */
public class EarthShakerException extends RuntimeException {
    private static final long serialVersionUID = -5890787028928802171L;

    public EarthShakerException() {
    }

    public EarthShakerException(String message) {
        super(message);
    }

    public EarthShakerException(String message, Throwable cause) {
        super(message, cause);
    }

    public EarthShakerException(Throwable cause) {
        super(cause);
    }

    public EarthShakerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
