package pers.yurwisher.parser.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.parser.exception.PdfParseException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author yq
 * @date 2019/01/18 09:28
 * @description pdf
 * @since V1.0.0
 */
public class PdfParser {


    private static final Logger logger  = LoggerFactory.getLogger(PdfParser.class);

    /**
     * 单个td一行
     * @param inputStream 输入流
     * @return 解析结果
     */
    public static String toText(InputStream inputStream) {
        try {
            PdfReader reader = new PdfReader(inputStream);
            StringBuffer buffer = new StringBuffer();
            for(int i = 1 ;i <= reader.getNumberOfPages() ;i++){
                //读取第i页的文档内容
                buffer.append(PdfTextExtractor.getTextFromPage(reader, i));
            }
            return buffer.toString();
        } catch (IOException e) {
            throw new PdfParseException("parse paf error",e);
        }
    }

    /**
     * 单个tr一行
     * @param fileName 文件名
     * @return 解析结果
     */
    public static String getText(String fileName){
        File file = new File(fileName);
        try {
            //新建一个PDF解析器对象
            PDFParser parser = new PDFParser(new RandomAccessFile(file,"r"));
            return getText(parser);
        }catch (IOException e){
            throw new PdfParseException("parse paf error",e);
        }
    }

    /**
     * 单个tr一行
     * @param inputStream 输入流
     * @return 解析结果
     */
    public static String getText(InputStream inputStream){
        try {
            //新建一个PDF解析器对象
            PDFParser parser = new PDFParser(new RandomAccessBuffer(inputStream));
            return getText(parser);
        }catch (IOException e){
            throw new PdfParseException("parse paf error",e);
        }
    }

    private static String getText(PDFParser parser) throws IOException{
        //新建一个PDF解析器对象
        //对PDF文件进行解析
        parser.parse();
        //获取解析后得到的PDF文档对象
        PDDocument document = parser.getPDDocument();
        //新建一个PDF文本剥离器
        PDFTextStripper stripper = new PDFTextStripper();
        //从PDF文档对象中剥离文本
        return stripper.getText(document);
    }

    /**
     *  单个内容解析
     * @param fileName 文件名
     * @param textChunk 单个label 分隔符,方便切分
     * @return 解析结果
     */
    public static String toText(String fileName,String textChunk){
        try {
            PdfReader reader = new PdfReader(fileName);
            return parse(reader,textChunk);
        } catch (IOException e) {
            throw new PdfParseException("parse paf text error",e);
        }
    }

    public static String toText(InputStream inputStream,String textChunk){
        try {
            PdfReader reader = new PdfReader(inputStream);
            return parse(reader,textChunk);
        } catch (IOException e) {
            throw new PdfParseException("parse paf text error",e);
        }
    }

    private static String parse(PdfReader reader,String textChunk) throws IOException {
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        StringBuffer buff = new StringBuffer();
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i,
                    new YurwisherTextExtractionStrategy(textChunk));
            buff.append(strategy.getResultantText());
        }
        return buff.toString();
    }

    public static void main(String[] args) {
        System.out.println(getText("D:/qr/demo.pdf")) ;
    }
}
