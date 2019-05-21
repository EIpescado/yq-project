package pers.yurwisher.wisp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by yq on 2017/01/10 15:57.
 * number工具类
 */
public class NumberUtils {

    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

    /**
    * 汉语中货币单位大写，这样的设计类似于占位符
    */
    private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰",
                                                             "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟" };

    /**
     * 特殊字符：整
    */
    private static final String CN_FULL = "整";

   /**
    * 特殊字符：负
    */
    private static final String CN_NEGATIVE = "负";

    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;

    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    private NumberUtils() {
    }

    /**
     * 字符串转 BigDecimal
     * @param str
     */
    public static BigDecimal string2BigDecimal(String str){
        try{
            if(str == null || str.length() == 0){
                return null;
            }
            return new BigDecimal(str);
        }catch (Exception e){
            return null ;
        }
    }

    public static BigDecimal double2BigDecimal(double d){
        try{
            return new BigDecimal(d);
        }catch (Exception e){
            return null ;
        }
    }

    /**
     * BigDecimal四舍五入只保留整数
     * @param bigDecimal
     */
    public static BigDecimal round0(BigDecimal bigDecimal){
        if(bigDecimal != null){
            return  bigDecimal.setScale(0,RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO ;
    }

    /**
     * BigDecimal四舍五入保留2位小数
     * @param bigDecimal
     */
    public static BigDecimal round2(BigDecimal bigDecimal){
        if(bigDecimal != null){
            return  bigDecimal.setScale(2,RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO ;
    }

    /**
     * BigDecimal四舍五入保留3位小数
     */
    public static BigDecimal round3(BigDecimal bigDecimal){
        if(bigDecimal != null){
            return  bigDecimal.setScale(3,RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO ;
    }

    /**
     * BigDecimal四舍五入保留4位小数
     */
    public static BigDecimal round4(BigDecimal bigDecimal){
        if(bigDecimal != null){
            return  bigDecimal.setScale(4,RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO ;
    }

    /**
     * 判断String是否为BigDecimal
     */
    public static Boolean isBigDecimal(String arg) {
        try {
            new BigDecimal(arg) ;
            return true ;
        } catch (Exception ex) {
            return false ;
        }
    }

    /**
     * 数字转中文表达
     * @param money
     */
    public static String number2Chinese(BigDecimal money){
        StringBuilder sb = new StringBuilder();
        //获取符号位 -1:负数 ; 0 ;  1:正数
        int signum = money.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }

        //小数点右移2位(不保留小数)  四舍五入后的绝对值
        long number = money.movePointRight(MONEY_PRECISION).setScale(0, RoundingMode.HALF_UP).abs().longValue();

        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }

    /**
     * a 是否大于 b
     */
    public static boolean greaterThan(BigDecimal a , BigDecimal b){
        if(a != null && b != null){
            return a.compareTo(b) == 1;
        }else {
            throw new IllegalArgumentException("参数异常");
        }
    }

    /**
     * a 是否大于 0
     */
    public static boolean greaterThenZero(BigDecimal a){
        return greaterThan(a,BigDecimal.ZERO);
    }

    /**
     * a 是否大于等于 b
     */
    public static boolean greaterOrEqual(BigDecimal a ,BigDecimal b){
        if(a != null && b != null){
            return a.compareTo(b) >= 0;
        }else {
            throw new IllegalArgumentException("参数异常");
        }
    }

    /**
     * a 是否大于等于 0
     */
    public static boolean greaterOrEqualZero(BigDecimal a){
        return greaterOrEqual(a,BigDecimal.ZERO);
    }


    /**
     * a 是否小于 b
     */
    public static boolean lessThen(BigDecimal a ,BigDecimal b){
        if(a != null && b != null){
            return a.compareTo(b) == -1;
        }else {
            throw new IllegalArgumentException("参数异常");
        }
    }

    /**
     * a 是否小于 0
     */
    public static boolean lessThenZero(BigDecimal a){
        return lessThen(a,BigDecimal.ZERO);
    }

    /**
     * a 是否小于等于 b
     */
    public static boolean lessOrEqual(BigDecimal a ,BigDecimal b){
        if(a != null && b != null){
            return a.compareTo(b) <= 0;
        }else {
            throw new IllegalArgumentException("参数异常");
        }
    }

    /**
     * a 是否小于等于 0
     */
    public static boolean lessOrEqualZero(BigDecimal a){
        return lessOrEqual(a,BigDecimal.ZERO);
    }

    /**
     * a 是否等于 b
     */
    public static boolean equalTo(BigDecimal a ,BigDecimal b){
        if(a != null && b != null){
            return a.compareTo(b) == 0;
        }else {
            throw new IllegalArgumentException("参数异常");
        }
    }

    /**
     * a 是否等于0
     */
    public static boolean equalZero(BigDecimal a){
        return equalTo(a,BigDecimal.ZERO);
    }

    /**
     * a / b 保留6位小数 四舍五入
     */
    public static BigDecimal divide(BigDecimal a ,BigDecimal b){
        if(b != null && !equalZero(b)){
            return a.divide(b,6,BigDecimal.ROUND_HALF_UP);
        }else {
            throw new IllegalArgumentException("除数不能为0");
        }
    }

    /**
     * a / b 保留6位小数 四舍五入
     */
    public static BigDecimal divide(Number a ,Number b){
        if(b != null && b.intValue() != 0){
            return new BigDecimal(a.doubleValue()).divide(new BigDecimal(b.doubleValue()),6,BigDecimal.ROUND_HALF_UP);
        }else {
            throw new IllegalArgumentException("除数不能为0");
        }
    }

    public static boolean isNumberHaveChange(BigDecimal a,BigDecimal b){
        if(a == null){
            return b != null ;
        }
        if(b != null){
            return !NumberUtils.equalTo(a,b);
        }else{
            return false;
        }
    }

    /**
     * 数相加
     */
    public static BigDecimal add(BigDecimal a ,BigDecimal b){
        BigDecimal a1 = a != null ? a : BigDecimal.ZERO;
        BigDecimal b1 = b != null ? b : BigDecimal.ZERO;
        return a1.add(b1);
    }

    /**
     * 数相减
     */
    public static BigDecimal subtract(BigDecimal a ,BigDecimal b){
        BigDecimal a1 = a != null ? a : BigDecimal.ZERO;
        BigDecimal b1 = b != null ? b : BigDecimal.ZERO;
        return a1.subtract(b1);
    }

    /**
     * 随机生成6位随机数
     */
    public static int getSixRandom(){
        return  (int)((Math.random()*9+1)*100000);
    }
}
