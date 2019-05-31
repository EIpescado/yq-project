package pers.yurwisher.parser;

import pers.yurwisher.parser.pdf.HSBCParser;
import pers.yurwisher.parser.pdf.PdfParser;
import pers.yurwisher.parser.qrcode.QrCodeParser;

/**
 * @author yq
 * @date 2019/01/18 10:38
 * @description 解析
 * @since V1.0.0
 */
public class Parsers {

    private Parsers(){

    }

    private static QrCodeParser qrCodeParser = new QrCodeParser();

    private static PdfParser pdfParser = new PdfParser();

    private static HSBCParser hsbcParser = new HSBCParser();

    public static QrCodeParser qrCodeParser() {
        return qrCodeParser;
    }

    public static PdfParser pdfParser() {
        return pdfParser;
    }

    public static HSBCParser hsbcParser() {
        return hsbcParser;
    }
}
