package pers.yurwisher.grabber.maoyan;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.grabber.GrabException;
import pers.yurwisher.grabber.HttpClientHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yq
 * @date 2019/06/18 12:15
 * @description 票房grabber
 * @since V1.0.0
 */
public class BoxOfficeGrabber {

    private static final Logger logger = LoggerFactory.getLogger(BoxOfficeGrabber.class);

    private static final String URL = "https://box.maoyan.com/promovie/api/box/second.json?beginDate=%1$s";

    private static final DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public BoxOffice query(LocalDateTime dateTime) {
        String x = HttpClientHelper.getInstance().sendGet(String.format(URL, dateTime.format(YYYYMMDD_FORMATTER)));
        JSONObject json = JSON.parseObject(x);
        Boolean success = json.getBoolean("success");
        if (success != null && success) {
            return json.getJSONObject("data").toJavaObject(BoxOffice.class);
        } else {
            logger.info("maoyan response: {}",x);
            throw new GrabException("grab box office from http://piaofang.maoyan.com/dashboard error,try it later");
        }
    }

    public static void main(String[] args) {
        BoxOfficeGrabber g = new BoxOfficeGrabber();
        BoxOffice today = g.query(LocalDateTime.now());
        System.out.println(today.getTotalBox());
    }

}
