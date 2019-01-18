package pers.yurwisher.parser.exception;

/**
 * @author yq
 * @date 2019/01/17 17:31
 * @description qr code
 * @since V1.0.0
 */
public class PdfParseException extends ParseException {
    private static final long serialVersionUID = -6143641515343731911L;

    public PdfParseException() {
    }

    public PdfParseException(String message) {
        super(message);
    }

    public PdfParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public PdfParseException(Throwable cause) {
        super(cause);
    }

    public PdfParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
