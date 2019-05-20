package pers.yurwisher.juggernaut.support;

import pers.yurwisher.juggernaut.WorkTask;
import pers.yurwisher.juggernaut.Worker;

import java.util.List;

/**
 * @author yq
 * @date 2019/05/20 14:01
 * @description 工作组
 * @since V1.0.0
 */
public interface IWorkGroup<T> {

    /**
     * 工作组名称前缀
     * @return  工作组名称前缀
     */
    String groupPrefix();

    /**
     * 工人数量
     * @return  工人数量
     */
    int getNumberOfWorkers();

    /**
     * 工人最大工作量
     * @return  工人最大工作量
     */
    int getMaxWorkLoad();

    /**
     * 组内所有工人
     * @return 工人集合
     */
    List<Worker<T>> getWorkers();

    /**
     * 计算得出工作分配给哪个员工,默认随机分配
     *
     * @param work    工作内容
     * @param workers 工作组员工
     * @return 接受此任务的员工
     */
    Worker<T> allot(WorkTask<T> work, List<Worker<T>> workers);

    /**
     * 任务接收,并分派给具体工人
     * @param taskContent 具体工作内容,即具体的参数
     * @return 是否成功
     */
    boolean accept(T taskContent);

    /**
     * 此工作组的工作职责,即进行何种操作
     * @return 具体工作方式
     */
    IWork<T> work();

    /**
     * 返修次数
     * @return int
     */
    int repairNumber();

    /**
     * 超出返修次数后的异常回调
     * @return 异常回调
     */
    IMonitor<T> monitor();
}
