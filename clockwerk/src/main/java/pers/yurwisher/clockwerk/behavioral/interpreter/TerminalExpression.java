package pers.yurwisher.clockwerk.behavioral.interpreter;

/**
 * @author yq
 * @date 2019/09/23 16:17
 * @description 主解释器
 * @since V1.0.0
 */
public class TerminalExpression implements Expression{

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
