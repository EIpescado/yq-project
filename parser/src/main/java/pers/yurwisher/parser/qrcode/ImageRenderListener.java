package pers.yurwisher.parser.qrcode;

import com.itextpdf.text.exceptions.UnsupportedPdfException;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.parser.utils.Utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yq
 * @date 2018/11/27 15:52
 * @description 图片
 * @since V1.0.0
 */
public class ImageRenderListener implements RenderListener {

    private static final Logger logger = LoggerFactory.getLogger(ImageRenderListener.class);

    /**
     * 识别出的二维码数据 集合
     */
    private List<String>  dataList;

    @Override
    public void beginTextBlock() {

    }

    @Override
    public void renderText(TextRenderInfo textRenderInfo) {

    }

    @Override
    public void endTextBlock() {

    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {
        PdfImageObject image0;
        try {
            image0 = renderInfo.getImage();
            if(image0 != null){
                byte[] imageByte = image0.getImageAsBytes();
                //识别二维码获取单号
                String context = QrCodeParser.recognizeQR(new ByteArrayInputStream(imageByte));
                if(Utils.isNotEmpty(context)){
                    if(Utils.isNotEmpty(dataList)){
                        if(!dataList.contains(context)){
                            dataList.add(context);
                        }
                    }else {
                        dataList = new ArrayList<>();
                        dataList.add(context);
                    }
                }
            }
        }catch (UnsupportedPdfException e){
            logger.error("字体,颜色不支持",e);
        }catch (IOException e) {
            logger.error("提取pdf中图片异常",e);
        }
    }

    public List<String> getDataList() {
        return dataList;
    }
}
