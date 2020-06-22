package pers.yurwisher.wisp.wrapper;

/**
 * @author yq
 * @date 2019/12/16 16:01
 * @description 自定义状态
 * @since V1.0.0
 */
public class CustomStatus {
    /**状态描述*/
    private String desc;
    /**状态排序*/
    private Integer sort;

    public CustomStatus(String desc, Integer sort) {
        this.desc = desc;
        this.sort = sort;
    }

    public static CustomStatus of(String desc){
        return new CustomStatus(desc,0);
    }

    public static CustomStatus of(String desc, Integer sort){
        return new CustomStatus(desc,sort);
    }

    public String getDesc() {
        return desc;
    }

    public Integer getSort() {
        return sort;
    }

}
