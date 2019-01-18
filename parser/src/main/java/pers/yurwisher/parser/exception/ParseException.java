package pers.yurwisher.parser.exception;

/**
 * @author yq
 * @date 2019/01/17 17:31
 * @description
 * @since V1.0.0
 */
public class ParseException extends RuntimeException {
    private static final long serialVersionUID = -5708091660472690896L;

    public ParseException() {
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    public ParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
