package pers.yurwisher.parser.exception;

/**
 * @author yq
 * @date 2019/01/17 17:31
 * @description qr code
 * @since V1.0.0
 */
public class QrCodeParseException extends ParseException {
    private static final long serialVersionUID = -6143641515343731911L;

    public QrCodeParseException() {
    }

    public QrCodeParseException(String message) {
        super(message);
    }

    public QrCodeParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public QrCodeParseException(Throwable cause) {
        super(cause);
    }

    public QrCodeParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
