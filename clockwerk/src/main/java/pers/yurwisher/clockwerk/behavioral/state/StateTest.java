package pers.yurwisher.clockwerk.behavioral.state;

/**
 * 状态模式测试
 */
public class StateTest {

    public static void testState() {
        StateContext stateContext = new StateContext();
        StartStateImpl startState = new StartStateImpl();
        startState.doAction(stateContext);
        System.out.println(stateContext.getState());
        EndStateImpl endState = new EndStateImpl();
        endState.doAction(stateContext);
        System.out.println(stateContext.getState());
    }

    public static void main(String[] args) {
        testState();
    }
}
