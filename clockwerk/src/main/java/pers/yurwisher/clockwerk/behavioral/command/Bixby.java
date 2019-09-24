package pers.yurwisher.clockwerk.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yq
 * @date 2019/09/23 15:43
 * @description Bixby 语音助手
 * @since V1.0.0
 */
public class Bixby {

    private List<Command> commandList = new ArrayList<>();

    /**
     * 接收指令
     * @param command 指令
     */
    public void receiveCommand(Command command){
        commandList.add(command);
    }

    /**
     * 依次执行指令
     */
    public void executeCommands(){
        for (Command command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}
