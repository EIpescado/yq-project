package pers.yurwisher.parser.qrcode;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.parser.exception.QrCodeParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.List;

/**
 * @author yq
 * @date 2018/11/27 11:09
 * @description 二维码工具
 * @since V1.0.0
 */
public class QrCodeParser {

    private static final Logger logger = LoggerFactory.getLogger(QrCodeParser.class);

    private static final EnumMap<DecodeHintType, Object>  HINTS = new EnumMap<>(DecodeHintType.class);

    static {
        //指定编码方式,防止中文乱码
        HINTS.put(DecodeHintType.CHARACTER_SET, "UTF-8");
    }

    /**
     * 解析二维码图片
     * @param filePath 图片路径
     */
    public static String recognizeQR(String filePath) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        return recognizeQR(fileInputStream);
    }

    /**
     * 解析二维码图片
     * @param inputStream 输入流
     */
    public static String recognizeQR(InputStream inputStream) {
        String content;
        try {
            BufferedImage image = ImageIO.read(inputStream);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, HINTS);
            content = result.getText();
        } catch (NotFoundException e) {
            //不是二维码图片
            throw new QrCodeParseException("image not a qr code");
        } catch (IOException e) {
            throw new QrCodeParseException("parse qr code error",e);
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("关闭流异常",e);
                }
            }

        }
        return content;
    }

    public static List<String> recognizeQRInPdf(String fileName) {
        PdfReader reader = null;
        try {
            reader = new PdfReader(fileName);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            ImageRenderListener listener = new ImageRenderListener();
            int pageNumber = reader.getNumberOfPages();
            logger.info("总页数:{}",pageNumber);
            for (int i = 1; i <= pageNumber; i++) {
                parser.processContent(i, listener);
            }
            return listener.getDataList();
        } catch (IOException e) {
            throw new QrCodeParseException("parse qr code in pdf error",e);
        } finally {
            if(reader != null){
                reader.close();
            }
        }
    }

}
