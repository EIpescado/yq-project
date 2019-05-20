package pers.yurwisher.juggernaut.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.juggernaut.WorkTask;
import pers.yurwisher.juggernaut.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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

    private static final String DEFAULT_GROUP_PREFIX =  "default-group-";

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad, String groupPrefix, int repairNumber , IWork<T> work , IMonitor<T> monitor) {
        this.groupPrefix = groupPrefix;
        this.numberOfWorkers = numberOfWorkers;
        this.maxWorkLoad = maxWorkLoad;
        this.work = work;
        this.repairNumber = repairNumber;
        this.monitor = monitor;
        init();
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad, int repairNumber, IWork<T> work, IMonitor<T> monitor) {
        this(numberOfWorkers,maxWorkLoad,DEFAULT_GROUP_PREFIX,repairNumber,work,monitor);
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad, int repairNumber, IWork<T> work) {
        this(numberOfWorkers,maxWorkLoad,DEFAULT_GROUP_PREFIX,repairNumber,work,null);
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad,String groupPrefix, int repairNumber, IWork<T> work) {
        this(numberOfWorkers,maxWorkLoad,groupPrefix,repairNumber,work,null);
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad, IWork<T> work) {
        this(numberOfWorkers,maxWorkLoad,DEFAULT_GROUP_PREFIX,0,work,null);
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad, IWork<T> work, IMonitor<T> monitor) {
        this(numberOfWorkers,maxWorkLoad,DEFAULT_GROUP_PREFIX,0,work,monitor);
    }

    public DefaultWorkGroup(int numberOfWorkers, int maxWorkLoad,String groupPrefix, IWork<T> work, IMonitor<T> monitor) {
        this(numberOfWorkers,maxWorkLoad,groupPrefix,0,work,monitor);
    }

    /**
     * 招募员工
     */
    private void init() {
        logger.info("已有员工:{} 人", numberOfWorkers);
        workers = new ArrayList<>(numberOfWorkers);
        //站好队
        for (int i = 0; i < numberOfWorkers; i++) {
            workers.add(new Worker<>(groupPrefix() + i , this, getMaxWorkLoad()));
        }
    }

    /**
     * 计算得出工作分配给哪个员工,默认随机分配
     *
     * @param workTask 工作内容
     * @param workers 工作组员工
     * @return 接受此任务的员工
     */
    @Override
    public Worker<T> allot(WorkTask<T> workTask, List<Worker<T>> workers) {
        return workers.get(ThreadLocalRandom.current().nextInt(getNumberOfWorkers()));
    }

    /**
     * 任务接收,并分派给具体工人
     * @param taskContent 具体工作内容
     * @return 是否成功
     */
    @Override
    public final boolean accept(T taskContent){
        WorkTask<T> workTask = new WorkTask<>(taskContent);
        Worker<T> worker = allot(workTask,getWorkers());
        return worker.accept(workTask);
    }

    @Override
    public IWork<T> work() {
        return work;
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
}
