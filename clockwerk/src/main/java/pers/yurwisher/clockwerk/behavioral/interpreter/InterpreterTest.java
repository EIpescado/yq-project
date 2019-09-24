package pers.yurwisher.clockwerk.behavioral.interpreter;

/**
 * @author yq
 * @date 2019/09/23 16:16
 * @description 解释器模式
 * @since V1.0.0
 */
public class InterpreterTest {

    /**
     * 力量英雄
     */
    private static Expression strengthExpression() {
        Expression robert = new TerminalExpression("屠夫");
        Expression john = new TerminalExpression("船长");
        return new OrExpression(robert, john);
    }

    /**
     * 男性敏捷英雄
     */
    private static Expression agilityMaleExpression() {
        Expression julie = new TerminalExpression("力");
        Expression married = new TerminalExpression("丸");
        return new AndExpression(julie, married);
    }

    public static void main(String[] args) {
        Expression strength = strengthExpression();
        Expression agilityMale = agilityMaleExpression();

        System.out.println("赏金是力量英雄么? " + strength.interpret("赏金"));
        System.out.println("力丸是男性敏捷英雄? " + agilityMale.interpret("力丸"));
    }
}
