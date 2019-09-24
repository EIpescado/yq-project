package pers.yurwisher.clockwerk.structural.decorator;

/**
 * @author yq
 * @date 2019/09/20 14:39
 * @description 屠夫
 * @since V1.0.0
 */
public class Pudge implements Hero {
    @Override
    public void kill() {
        System.out.print("Pudge have killed someone ");
    }
}
