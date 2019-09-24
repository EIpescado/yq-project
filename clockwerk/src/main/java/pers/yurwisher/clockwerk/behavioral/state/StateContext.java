package pers.yurwisher.clockwerk.behavioral.state;

/**
 * @author yq
 * @date 2019/09/16 16:52
 * @description
 * @since V1.0.0
 */
public class StateContext {

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
