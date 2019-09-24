package pers.yurwisher.clockwerk.behavioral.command;

/**
 * @author yq
 * @date 2019/09/23 15:46
 * @description 明天天气指令
 * @since V1.0.0
 */
public class TomorrowWeatherCommand implements Command{

    private WeatherReporter weatherReporter;

    public TomorrowWeatherCommand(WeatherReporter weatherReporter) {
        this.weatherReporter = weatherReporter;
    }

    @Override
    public void execute() {
        weatherReporter.tomorrowWeather();
    }
}
