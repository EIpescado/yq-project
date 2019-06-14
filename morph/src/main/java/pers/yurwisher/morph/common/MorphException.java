package pers.yurwisher.morph.common;

/**
 * @author yq
 * @date 2019/06/11 12:24
 * @description
 * @since V1.0.0
 */
public class MorphException extends RuntimeException{
    private static final long serialVersionUID = 1769807255065697498L;

    public MorphException() {
    }

    public MorphException(String message) {
        super(message);
    }

    public MorphException(String message, Throwable cause) {
        super(message, cause);
    }

    public MorphException(Throwable cause) {
        super(cause);
    }

    public MorphException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
