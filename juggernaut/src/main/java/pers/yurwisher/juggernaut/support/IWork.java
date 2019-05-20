package pers.yurwisher.juggernaut.support;

import pers.yurwisher.juggernaut.WorkTask;

/**
 * @author yq
 * @date 2019/05/20 16:15
 * @description  具体的工作方法
 * @since V1.0.0
 */
public interface IWork<T> {

    /**
     * 执行
     * @param workTask 待处理任务
     * @throws Exception 异常
     */
    void run(WorkTask<T> workTask) throws Exception;
}
