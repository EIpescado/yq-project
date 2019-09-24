package pers.yurwisher.clockwerk.behavioral.command;

/**
 * @author yq
 * @date 2019/09/23 15:52
 * @description 命令模式测试
 * @since V1.0.0
 */
public class CommandTest {

    public static void main(String[] args) {
        Bixby bixby = new Bixby();
        WeatherReporter reporter = new WeatherReporter();
        bixby.receiveCommand(new TomorrowWeatherCommand(reporter));
        bixby.receiveCommand(new WeeklyWeatherCommand(reporter));
        bixby.receiveCommand(() -> System.out.println("new command....sing jay chou's new song 'don't cry'"));
        bixby.executeCommands();
    }
}
