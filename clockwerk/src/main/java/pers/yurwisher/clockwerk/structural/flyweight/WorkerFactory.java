package pers.yurwisher.clockwerk.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yq
 * @date 2019/09/20 17:51
 * @description 工厂
 * @since V1.0.0
 */
public class WorkerFactory {

    private static final Map<Long,Worker> MAP = new HashMap<>();

    public Worker getWorker(Long no) {
        Worker worker = MAP.get(no);

        if(worker == null) {
            worker = things -> System.out.println(no + " making " + things);
            MAP.put(no, worker);
            System.out.println("creating worker of no : " + no);
        }
        return worker;
    }

}
