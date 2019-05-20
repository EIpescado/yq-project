package pers.yurwisher.juggernaut.support;

import pers.yurwisher.juggernaut.WorkTask;

/**
 * @author yq
 * @date 2019/05/20 16:57
 * @description 监控.异常回调
 * @since V1.0.0
 */
public interface IMonitor<T> {

    /**
     * 异常回调
     * @param workTask 待处理任务
     * @param e 异常
     */
    void handle(WorkTask<T> workTask, Throwable e);
}
