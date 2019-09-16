package pers.yurwisher.juggernaut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.juggernaut.support.IWorkGroup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yq
 * @date 2019/05/20 11:27
 * @description 员工,工人,执行具体的工作的人
 * @since V1.0.0
 */
public class Worker<T> {

    private static final Logger logger = LoggerFactory.getLogger(Worker.class);

    /**员工编号*/
    private String workId;

    /**员工归属的工作组*/
    private IWorkGroup<T> workGroup;

    /**单个员工最大的工作量*/
    private int maxWorkLoad;

    /**员工当前工作量  LongAdder比AtomicLong性能更好(减少乐观锁的重试次数)*/
    private LongAdder currentWorkTaskNumber;

    /**员工工作队列*/
    private LinkedBlockingQueue<WorkTask<T>> workQueue;

    private ExecutorService executorService;

    public Worker(String workId, IWorkGroup<T> workGroup, Integer maxWorkLoad) {
        this.workId = workId;
        this.workGroup = workGroup;
        this.maxWorkLoad = maxWorkLoad;
        this.workQueue = new LinkedBlockingQueue<>(maxWorkLoad);
        this.currentWorkTaskNumber = new LongAdder();
        execute();
    }

    /**
     * 员工接受任务,置入队列,等待执行
     * @param task 具体的工作
     * @return 受理是否成功
     */
    public boolean accept(WorkTask<T> task){
        boolean f = workQueue.offer(task);
        //工作量+1
        currentWorkTaskNumber.increment();
        return f;
    }

    private void execute() {
        this.executorService = createSingleWorkerThread();
        executorService.execute(() -> {
            while (true) {
                WorkTask<T> task = null;
                try {
                    task = workQueue.take();
                } catch (InterruptedException e) {
                    logger.error("线程异常", e);
                }
                try {
                    //执行次数 +1
                    task.setInvokeCount(task.getInvokeCount() + 1);
                    workGroup.work().run(task);
                } catch (Exception e) {
                    logger.error("员工工作异常",e);
                    //达到返修次数上限 退出重试
                    if(task.getInvokeCount() >= workGroup.repairNumber() + 1){
                        //异常回调
                        if(workGroup.monitor() != null){
                            workGroup.monitor().handle(task,e);
                        }
                    }else {
                        //异常 丢回去接着处理
                        workGroup.accept(task);
                    }
                } finally {
                    //总工作量-1
                    workGroup.totalWork().decrement();
                    //当前工人剩余工作量-1
                    currentWorkTaskNumber.decrement();
                    logger.info("{} 剩余工作量:{},工作组剩余总工作量:{}",workId,currentWorkTaskNumber.sum(),workGroup.totalWork().sum());
                }
            }
        });
    }

    private ExecutorService createSingleWorkerThread(){
        ThreadFactory factory =  (Runnable r)->{
            Thread thread = new Thread(r);
            thread.setName(workId);
            //守护线程
            thread.setDaemon(true);
            return thread;
        };
        //初始化一个线程去监控此队列
        return new ThreadPoolExecutor(
                //核心线程,最大线程
                1, 1,
                //回收空闲线程时间
                0L, TimeUnit.MILLISECONDS,
                //阻塞队列
                new LinkedBlockingQueue<>(maxWorkLoad),
                factory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 员工离职
     */
    public void dismiss(){
        workQueue.clear();
        executorService.shutdown();
    }
}
