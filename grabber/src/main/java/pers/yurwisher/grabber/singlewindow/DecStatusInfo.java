package pers.yurwisher.grabber.singlewindow;

import lombok.Data;
import pers.yurwisher.grabber.Utils;

import java.lang.reflect.Field;

/**
 * @author yq
 * @date 2019/05/06 17:17
 * @description 单一窗口查询条件中的DecStatusInfo
 * @since V1.0.0
 */
@Data
public class DecStatusInfo {
    private String cusCiqNoHidden;
    private String dclTrnRelFlagHidden;
    private String transPreNoHidden;
    private String cusOrgCode;
    /**
     * 报关单类型
     * 0-一般报关单
     * 1-转关提前报关单
     * 2-备案清单
     * 3-转关提前备案清单
     * 4-出口二次转关
     */
    private String dclTrnRelFlag = "0";
    private String cusDecStatus;
    /**
     * 企业类别
     * A-报关申报单位
     * B-消费使用/生产销售单位
     * C-报关收发货人
     * D-报关录入单位
     */
    private String etpsCategory = "C";
    /**
     * 进出口标志 进口: I ,出口:E
     */
    private String cusIEFlag = "I";
    /**
     * 报关单号
     */
    private String entryId;
    /**
     * 统一编号
     */
    private String cusCiqNo;
    /**
     * 境内收发货人
     */
    private String cnsnTradeCode;
    /**
     * 提运单号
     */
    private String billNo;
    /**
     * 申报地海关
     */
    private String customMaster;
    /**
     * 是否结关 否:0 是 : 1
     */
    private String tableFlag = "0";
    /**
     * 最近操作时间
     */
    private String updateTime ;
    /**
     * 最近操作时间_结束
     */
    private String updateTimeEnd ;
    private String queryPage = "cusBasicQuery";
    private String operType = "0";

    public String build() {
        Field[] fields = DecStatusInfo.class.getDeclaredFields();
        //开头
        StringBuilder sb = new StringBuilder("%257B");
        String doubleQuotationMarks = "%2522";
        int length = fields.length;
        try{
            int i = 0;
            for (Field field : fields) {
                i ++ ;
                sb.append(doubleQuotationMarks).append(field.getName()).append(doubleQuotationMarks);
                sb.append(":");
                sb.append(doubleQuotationMarks).append(Utils.null2EmptyWithTrimNew(field.get(this))).append(doubleQuotationMarks);
                if(i != length){
                    sb.append(",");
                }
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        //结尾
        sb.append("%257D");
        return sb.toString();
    }

}
