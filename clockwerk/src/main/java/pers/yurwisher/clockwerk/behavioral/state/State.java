package pers.yurwisher.clockwerk.behavioral.state;

/**
 * @author yq
 * @date 2019/09/16 16:51
 * @description 状态接口
 * @since V1.0.0
 */
public interface State {

    /**
     * 状态执行的对应操作
     * @param context 上下文
     */
    void doAction(StateContext context);
}
