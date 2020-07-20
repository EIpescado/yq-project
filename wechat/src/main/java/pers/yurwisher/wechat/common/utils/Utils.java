package pers.yurwisher.wechat.common.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author yq
 * @date 2018/12/10 10:22
 * @description 工具类
 * @since V1.0.0
 */
public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static boolean isNotEmpty(Collection collection){
        return collection != null && ! collection.isEmpty();
    }

    public static boolean isNotEmpty(Map map){
        return map != null && ! map.isEmpty();
    }

    public static Integer size(Collection collection){
        return collection != null ? collection.size() : 0;
    }

    /**
     * 获取第一个匹配项的值(已去除前后空格)
     * @param doc 文档对象
     * @param xPathExpression xpath 表达式
     * @return 结果
     */
    public static String obtainFirstMatchResult(Document doc, String xPathExpression){
        //获取元素
        XPathExpression<Element> xpath =  XPathFactory.instance().compile(xPathExpression, Filters.element());
        Element element =xpath.evaluateFirst(doc) ;
        if(element == null){
            logger.error("[{}] no match result",xPathExpression);
            return  null ;
        }
        return element.getTextTrim();
    }

    /**
     * 创建Document
     */
    public static Document buildDocument(InputStream in){
        SAXBuilder sb = new SAXBuilder();
        Document document = null;
        try {
            document = sb.build(in) ;
        } catch (JDOMException | IOException e) {
            logger.error("document build error : {}",e.getMessage());
        }
        return  document;
    }

    /**
     * 字符串去左右空格
     */
    public static  String null2EmptyWithTrimNew(Object s)
    {

        if (s == null || "NULL".equalsIgnoreCase(s.toString())) {
            return "";
        } else {
            return s.toString().trim();
        }
    }

    /**
     * 字符串是否为空
     */
    public static  boolean isEmpty(String foo) {
        return (foo == null || foo.trim().length() == 0);
    }

    /**
     * 字符串是否不为空
     */
    public static  boolean isNotEmpty(String foo) {
        return (null != foo && foo.trim().length() > 0);
    }


    /**
     * 按分隔符分隔字符串
     */
    @Deprecated
    public static String[] split(String text, String separator) {

        StringTokenizer st = new StringTokenizer(text, separator);
        //分隔符数量大小的字符串数组
        String[] values = new String[st.countTokens()];
        int pos = 0;
        //是否还有分隔符
        while (st.hasMoreTokens()){
            //返回从当前位置到下一个分隔符的字符串
            values[pos++] = st.nextToken();
        }
        return values;
    }

    public static boolean isAnyEmpty(CharSequence... css) {
        if(css == null || css.length == 0) {
            return true;
        } else {
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = css[var3];
                if(isEmpty(cs)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
