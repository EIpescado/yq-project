package pers.yurwisher.grabber.singlewindow;



import lombok.Data;
import pers.yurwisher.grabber.Utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yq
 * @date 2019/05/06 16:35
 * @description 单一窗口查询条件
 * @since V1.0.0
 */
@Data
public class SingleWindowQueryParams implements Serializable {

    private static final long serialVersionUID = 66766639469490512L;
    /**
     * =============================url上参数
     */
    private Integer limit = 10;

    private Long offset = 0L;

    private String stName = "updateTime";

    private String stOrder = "desc";

    /**
     * 主参数查询报文 包含下列所有
     */
    private String decStatusInfo;

    private DecStatusInfo info ;

    public String build(){
        Field[] fields = SingleWindowQueryParams.class.getDeclaredFields();
        List<Field> list = Arrays.asList(fields);
        //开头
        StringBuilder sb = new StringBuilder();
        try{
            int i = 0;
            list = list.stream().filter(s -> !"info".equals(s.getName()) && !"serialVersionUID".equals(s.getName())).collect(Collectors.toList());
            int length = list.size();
            for (Field field : list) {
                i ++ ;
                sb.append(field.getName()).append("=").append(Utils.null2EmptyWithTrimNew(field.get(this)));
                if(i != length){
                    sb.append("&&");
                }
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

}
