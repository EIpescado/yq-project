package pers.yurwisher.juggernaut;

/**
 * @author yq
 * @date 2019/05/20 11:24
 * @description 工作任务
 * @since V1.0.0
 */
public class WorkTask<T> {

    /**
     * 待处理消息内容
     */
    private T task;
    /**
     * 执行次数
     */
    private int invokeCount;

    public WorkTask(T task) {
        this.task = task;
    }

    public T getTask() {
        return task;
    }

    public void setTask(T task) {
        this.task = task;
    }

    public int getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(int invokeCount) {
        this.invokeCount = invokeCount;
    }
}
