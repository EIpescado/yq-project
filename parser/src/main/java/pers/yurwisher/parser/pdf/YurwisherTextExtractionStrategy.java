package pers.yurwisher.parser.pdf;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.LineSegment;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.itextpdf.text.pdf.parser.Vector;

/**
 * @author yq
 * @date 2018/12/03 15:40
 * @description 自定义策略
 * @since V1.0.0
 */
public class YurwisherTextExtractionStrategy implements TextExtractionStrategy {

    /**
     * 行分隔符
     */
    private String textChunk;

    public YurwisherTextExtractionStrategy(String textChunk) {
        this.textChunk = textChunk;
    }

    private Vector lastStart;
    private Vector lastEnd;
    private final StringBuilder result = new StringBuilder();

    private  void appendTextChunk(CharSequence text) {
        this.result.append(text);
    }

    @Override
    public String getResultantText() {
        return this.result.toString();
    }

    @Override
    public void beginTextBlock() {

    }

    @Override
    public void renderText(TextRenderInfo renderInfo) {
        boolean firstRender = this.result.length() == 0;
        boolean hardReturn = false;
        LineSegment segment = renderInfo.getBaseline();
        Vector start = segment.getStartPoint();
        Vector end = segment.getEndPoint();
        if (!firstRender) {
            Vector x1 = this.lastStart;
            Vector x2 = this.lastEnd;
            float dist = x2.subtract(x1).cross(x1.subtract(start)).lengthSquared() / x2.subtract(x1).lengthSquared();
            float sameLineThreshold = 1.0F;
            if (dist > sameLineThreshold) {
                hardReturn = true;
            }
        }

        if (hardReturn) {
            this.appendTextChunk("\n");
        } else if (!firstRender
                && this.result.charAt(this.result.length() - 1) != ' '
                && renderInfo.getText().length() > 0
                && renderInfo.getText().charAt(0) != ' ') {
            float spacing = this.lastEnd.subtract(start).length();
            if (spacing > renderInfo.getSingleSpaceWidth() / 2.0F) {
                this.appendTextChunk(textChunk);
            }
        }
        this.appendTextChunk(renderInfo.getText());
        this.lastStart = start;
        this.lastEnd = end;
    }

    @Override
    public void endTextBlock() {

    }

    @Override
    public void renderImage(ImageRenderInfo imageRenderInfo) {

    }
}
