package pers.yurwisher.wisp.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yq
 * @date 2019/06/05 12:30
 * @description 中文数字工具
 * @since V1.0.0
 */
public class ChineseNumberUtils {

    private ChineseNumberUtils() {

    }

    /**
     * 数字
     */
    private static final Map<String, BigDecimal> CN_NUMBER_MAP = new HashMap<String, BigDecimal>() {
        private static final long serialVersionUID = -7427736462660213802L;

        {
            put("零", new BigDecimal("0"));
            put("一", new BigDecimal("1"));
            put("二", new BigDecimal("2"));
            put("三", new BigDecimal("3"));
            put("四", new BigDecimal("4"));
            put("五", new BigDecimal("5"));
            put("六", new BigDecimal("6"));
            put("七", new BigDecimal("7"));
            put("八", new BigDecimal("8"));
            put("九", new BigDecimal("9"));
        }
    };
    /**
     * 单位
     */
    private static final Map<String, BigDecimal> CN_UNIT_MAP = new HashMap<String, BigDecimal>() {
        private static final long serialVersionUID = -7427736462660213802L;

        {
            put("分", new BigDecimal("0.01"));
            put("角", new BigDecimal("0.1"));
            put("元", new BigDecimal(1));
            put("十", new BigDecimal(10));
            put("百", new BigDecimal(100));
            put("千", new BigDecimal(1000));
            put("万", new BigDecimal(10000));
            put("亿", new BigDecimal(100000000));
        }
    };

    private static final String[] CN_UNIT_ARRAY = {"亿", "万", "千", "百", "十", "元", "角", "分"};

    /**
     * 中文转阿拉伯数字
     */
    private static BigDecimal chinese2Number(String text) {
        if (StringUtils.isNotEmpty(text)) {
            text = replaceAllChineseSpecialExpress(text);
            //亿级以上单独处理
            if (text.contains("亿")) {
                return textToBigDecimalOverYi(text);
            } else {
                return textToBigDecimal(text);
            }
        }
        return null;
    }

    /**
     * 块 -> 元  , 毛  -> 角 ,两 -> 二
     *
     * @param text 原始文本
     */
    private static String replaceAllChineseSpecialExpress(String text) {
        return text.replaceAll("块", "元")
                .replaceAll("毛", "角")
                .replaceAll("两", "二");
    }

    private static BigDecimal textToBigDecimalOverYi(String text) {
        if (StringUtils.isNotEmpty(text)) {
            //当前单位在数组的索引值
            int nowUnitIndex;
            //拆分后的单个文本
            // 如 八万六千七百三十二亿九千七百二十三万五千七百三十二元六角七分 拆出
            //  八万六千七百三十二亿
            //  九千七百二十三万
            //  五千      七百      三十      二元      六角      七分
            String textValue;
            //拆出来后的剩余文本
            String nowText = text;
            BigDecimal value = BigDecimal.ZERO;
            for (int i = 0, length = CN_UNIT_ARRAY.length; i < length; i++) {
                nowUnitIndex = nowText.lastIndexOf(CN_UNIT_ARRAY[i]);
                if (nowUnitIndex != -1) {
                    //按单位拆分出文本值,然后挨个解析
                    textValue = nowText.substring(0, nowUnitIndex + 1);
                    nowText = nowText.substring(nowUnitIndex + 1);
                } else {
                    textValue = null;
                }
                if (textValue != null) {
                    value = value.add(textToBigDecimal(textValue));
                }
            }
            return value;
        }
        return null;
    }

    private static BigDecimal textToBigDecimal(String oneUnitText) {
        BigDecimal value = BigDecimal.ZERO;
        //在单位之前的值
        BigDecimal beforeUnitValue = null;
        String lastUnit = null;
        //按字符循环字符串
        for (int i = 0; i < oneUnitText.length(); i++) {
            String iChar = String.valueOf(oneUnitText.charAt(i));
            //值
            if (CN_NUMBER_MAP.containsKey(iChar)) {
                beforeUnitValue = CN_NUMBER_MAP.get(iChar);
                //最后数字可能不带单位 如 九千三
                if (i == oneUnitText.length() - 1 && StringUtils.isNotEmpty(lastUnit)) {
                    //加上最后一个单位 的 后一个单位与此数字的乘积
                    Integer indexForNow = findNowIndex(lastUnit);
                    if (indexForNow != null) {
                        //值
                        value = value.add(CN_UNIT_MAP.get(CN_UNIT_ARRAY[indexForNow]).multiply(beforeUnitValue));
                    }
                }
            } else if (CN_UNIT_MAP.containsKey(iChar)) {
                //单位
                //当前的单位基数
                BigDecimal nowUnitNumber = CN_UNIT_MAP.get(iChar);
                //之前的单位基数
                BigDecimal lastUnitNumber = CN_UNIT_MAP.get(lastUnit);

                //当前基数 是否大于 之前的基数
                boolean nowGreaterThenLast = lastUnitNumber != null && NumberUtils.greaterOrEqual(nowUnitNumber, lastUnitNumber);
                beforeUnitValue = beforeUnitValue == null ? BigDecimal.ZERO : beforeUnitValue;
                //当前基数 >= 之前的基数 则代表是 九千四百三十二万 | 三千四百三十万 这种情况
                if (nowGreaterThenLast) {
                    //单位 原值先加再乘
                    value = value.add(beforeUnitValue).multiply(nowUnitNumber);
                } else {
                    //单位 原值先乘再加
                    value = value.add(nowUnitNumber.multiply(beforeUnitValue));
                }
                beforeUnitValue = null;
                lastUnit = iChar;
            } else {
                //既不是值也不是单位
                beforeUnitValue = null;
            }
        }
        return value;
    }

    private static Integer findNowIndex(String x) {
        int length = CN_UNIT_ARRAY.length;
        Integer value = null;
        for (int i = 0; i < length; i++) {
            if (CN_UNIT_ARRAY[i].equals(x)) {
                value = i + 1;
                break;
            }
        }
        return value == null ? null : (value < length ? value : null);
    }

    /**
     * 中文表达转数字,如吃饭一百 转为吃饭100
     * @param text 原始文本
     * @return 拆分后结果
     */
    public static String chineseToNumber(String text){
        StringBuilder sb = new StringBuilder();
        text = replaceAllChineseSpecialExpress(text);
        //上一个字符串是否是值或者单位
        boolean previousOneIsValueOrUnit =false ;
        //数值builder
        StringBuilder numberBuilder = new StringBuilder();
        //文本builder
        StringBuilder textBuilder = new StringBuilder();
        //拆分文本 比如 吃饭一百五好贵啊 ,拆分成 吃饭 ,一百五,好贵啊
        for (int i = 0, length = text.length(); i < length; i++) {
            String iChar = String.valueOf(text.charAt(i));
            if(i == 0){
                previousOneIsValueOrUnit = CN_NUMBER_MAP.containsKey(iChar) || CN_UNIT_MAP.containsKey(iChar);
            }
            //值 或者 单位
            if (CN_NUMBER_MAP.containsKey(iChar) || CN_UNIT_MAP.containsKey(iChar)) {
                if(!previousOneIsValueOrUnit){
                    sb.append(textBuilder.toString());
                    textBuilder.delete(0,textBuilder.length());
                }
                numberBuilder.append(iChar);
                previousOneIsValueOrUnit = true;
            } else {
                //既不是值也不是单位
                if(previousOneIsValueOrUnit){
                    sb.append(chinese2Number(numberBuilder.toString()));
                    numberBuilder.delete(0,numberBuilder.length());
                }
                textBuilder.append(iChar);
                previousOneIsValueOrUnit = false;
            }
        }
        if(textBuilder.length() > 0){
            sb.append(textBuilder.toString());
        }
        if(numberBuilder.length() > 0){
            sb.append(chinese2Number(numberBuilder.toString()));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(chineseToNumber("六万亿"));
        System.out.println(chineseToNumber("六元五"));
        System.out.println(chineseToNumber("七千二"));
        System.out.println(chineseToNumber("九千三"));
        System.out.println(chineseToNumber("三十二千"));
        System.out.println(chineseToNumber("零一千万零八块九毛零分"));
        System.out.println(chineseToNumber("一千零一万七千零三十"));
        System.out.println(chineseToNumber("三千四百三十万"));
        System.out.println(chineseToNumber("三千四百三十二万"));
        System.out.println(chineseToNumber("三千四百三十二万八"));
        System.out.println(chineseToNumber("三千四百三十二万八千四百三十三元五角六分"));
        System.out.println(chineseToNumber("六亿三元四角八分"));
        System.out.println(chineseToNumber("八万六千七百三十二亿九千七百二十三万五千七百三十二元六角七分"));
        System.out.println(chineseToNumber("六千七百三十二亿九千七百二十三万五千七百三十二元六角七分"));
        System.out.println(chineseToNumber("吃饭原价一百五打完折一百"));
        System.out.println(chineseToNumber("吃饭一百五好贵啊"));
        System.out.println(chineseToNumber("一百万人吃饭花了两千四百万,你们也是厉害"));
        System.out.println(chineseToNumber("一百万人两千四百万的花费打完折三千"));
    }
}
