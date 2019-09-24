package pers.yurwisher.clockwerk.behavioral.state;

/**
 * @author yq
 * @date 2019/09/16 16:53
 * @description 开始状态
 * @since V1.0.0
 */
public class StartStateImpl implements State {
    @Override
    public void doAction(StateContext context) {
        System.out.println("start state action...");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "current state is start";
    }
}
