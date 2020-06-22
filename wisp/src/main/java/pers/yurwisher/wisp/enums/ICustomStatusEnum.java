package pers.yurwisher.wisp.enums;

import pers.yurwisher.wisp.wrapper.CustomStatus;

/**
 * @author yq
 * @date 2019/12/16 16:01
 * @description 状态枚举
 * @since V1.0.0
 */
public interface ICustomStatusEnum {

    /**
     * 自定义状态
     * @return 状态
     */
    CustomStatus status();

    /**
     * 状态描述
     * @return 状态描述
     */
    default String getDesc() {
        return status().getDesc();
    }

    /**
     * 状态排序号
     * @return 状态排序号
     */
    default Integer getSort() {
        return status().getSort();
    }
}
