package pers.yurwisher.juggernaut.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.juggernaut.WorkTask;
import pers.yurwisher.juggernaut.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yq
 * @date 2019/05/20 11:23
 * @description 默认工作组, 一组人员执行单一工作
 * @since V1.0.0
 */
public class DefaultWorkGroup<T> implements IWorkGroup<T> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultWorkGroup.class);

    /**
     * 组名前缀
     */
    private String groupPrefix;

    /**
     * 组员数量
     */
    private int numberOfWorkers;

    /**
     * 单个组员最大的工作量
     */
    private int maxWorkLoad;

    /**
     * 返修次数,即重试次数
     */
    private int repairNumber;

    /**
     * 组员集合
     */
    private List<Worker<T>> workers;

    /**
     * 具体工作方式
     */
    private IWork<T> work;

    /**
     * 异常回调
     */
    private IMonitor<T> monitor;

    /**
     * 工作组承接的总工作量
     */
    private LongAdder totalWork;

    private static final String DEFAULT_GROUP_PREFIX = "default-group-";
    private static final int DEFAULT_MAX_WORKLOAD = 1024;

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad, String groupPrefix, int repairNumber) {
        this.groupPrefix = groupPrefix;
        this.numberOfWorkers = numberOfWorkers;
        this.maxWorkLoad = maxWorkLoad;
        this.repairNumber = repairNumber;
        this.totalWork = new LongAdder();
    }

    public DefaultWorkGroup(int numberOfWorkers) {
        this(numberOfWorkers, DEFAULT_MAX_WORKLOAD, DEFAULT_GROUP_PREFIX, 0);
    }

    public DefaultWorkGroup(int numberOfWorkers, String groupPrefix, int repairNumber) {
        this(numberOfWorkers, DEFAULT_MAX_WORKLOAD, groupPrefix, repairNumber);
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad, int repairNumber) {
        this(numberOfWorkers, maxWorkLoad, DEFAULT_GROUP_PREFIX, repairNumber);
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad) {
        this(numberOfWorkers, maxWorkLoad, DEFAULT_GROUP_PREFIX, 0);
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad, String groupPrefix) {
        this(numberOfWorkers, maxWorkLoad, groupPrefix, 0);
    }

    @Override
    public void reGroup() {
        //先解散员工
        dismiss();
        logger.info("招募员工:{} 人", numberOfWorkers);
        workers = new ArrayList<>(numberOfWorkers);
        //站好队
        for (int i = 0; i < numberOfWorkers; i++) {
            workers.add(new Worker<>(groupPrefix() + i, this));
        }
    }

    @Override
    public boolean noWorker() {
        return (workers != null ? workers.size() : 0) == 0;
    }

    /**
     * 计算得出工作分配给哪个员工,默认随机分配
     *
     * @param workTask 工作内容
     * @param workers  工作组员工
     * @return 接受此任务的员工
     */
    @Override
    public Worker<T> allot(WorkTask<T> workTask, List<Worker<T>> workers) {
        return workers.get(ThreadLocalRandom.current().nextInt(getNumberOfWorkers()));
    }

    /**
     * 任务接收,并分派给具体工人
     *
     * @param taskContent 具体工作内容
     * @return 是否成功
     */
    @Override
    public final boolean accept(T taskContent) {
        WorkTask<T> workTask = new WorkTask<>(taskContent);
        return accept(workTask);
    }

    @Override
    public boolean accept(WorkTask<T> workTask) {
        //总工作量 + 1
        totalWork.increment();
        logger.info("工作组:{} 当前总工作量:{}", groupPrefix, totalWork.sum());
        Worker<T> worker = allot(workTask, getWorkers());
        return worker.accept(workTask);
    }

    @Override
    public IWork<T> work() {
        return work;
    }

    @Override
    public void setWork(IWork<T> iWork) {
        this.work = iWork;
    }

    @Override
    public int repairNumber() {
        return repairNumber;
    }

    @Override
    public IMonitor<T> monitor() {
        return monitor;
    }

    @Override
    public void setMonitor(IMonitor<T> monitor) {
        this.monitor = monitor;
    }

    @Override
    public String groupPrefix() {
        return groupPrefix;
    }

    @Override
    public int getNumberOfWorkers() {
        return numberOfWorkers;
    }

    @Override
    public int getMaxWorkLoad() {
        return maxWorkLoad;
    }

    @Override
    public List<Worker<T>> getWorkers() {
        return workers;
    }

    private void dismiss() {
        if (workers != null && workers.size() > 0) {
            logger.info("解散工作组:{} 人", workers.size());
            //站好队
            for (int i = 0; i < workers.size(); i++) {
                workers.get(i).dismiss();
            }
        }
    }

    @Override
    public LongAdder totalWork() {
        return totalWork;
    }
}
