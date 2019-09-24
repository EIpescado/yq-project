package pers.yurwisher.clockwerk.behavioral.template;

/**
 * @author yq
 * @date 2019/09/24 14:50
 * @description 模版模式测试
 * @since V1.0.0
 */
public class TemplateTest {

    public static void main(String[] args) {
        Programmer java = new JavaProgrammer();
        Programmer c = new CProgrammer();
        java.spendDay();
        c.spendDay();
    }
}
