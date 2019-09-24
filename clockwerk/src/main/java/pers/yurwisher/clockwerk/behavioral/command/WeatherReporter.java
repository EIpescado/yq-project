package pers.yurwisher.clockwerk.behavioral.command;

/**
 * @author yq
 * @date 2019/09/23 15:48
 * @description 天气软件
 * @since V1.0.0
 */
public class WeatherReporter {

    /**
     * 明天天气
     */
    public void tomorrowWeather(){
        System.out.println("tomorrow weather: sunshiny");
    }

    /**
     * 未来一周的天气天气
     */
    public void weeklyWeather(){
        System.out.println("Weather conditions for the coming week: sunshiny");
    }

}
