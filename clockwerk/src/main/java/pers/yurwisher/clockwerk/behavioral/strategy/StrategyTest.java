package pers.yurwisher.clockwerk.behavioral.strategy;

/**
 * 策略模式测试
 */
public class StrategyTest {

    public static void testStrategy(){
        System.out.println(testStrategy(new AddStrategyImpl()));
        System.out.println(testStrategy(new SubtractStrategyImpl()));
        System.out.println(testStrategy(new MultiplyStrategyImpl()));
        System.out.println(testStrategy((num1,num2) -> num2 / num1));
    }

    private static int testStrategy(Strategy strategy) {
        int a = 1;
        int b = 2;
        return strategy.doOperation(a,b);
    }


    public static void main(String[] args) {
        testStrategy();
    }
}
