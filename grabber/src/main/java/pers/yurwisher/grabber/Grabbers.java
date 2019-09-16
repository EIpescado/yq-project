package pers.yurwisher.grabber;

import pers.yurwisher.grabber.customs.CustomsGrabber;
import pers.yurwisher.grabber.exchangerate.ExchangeRateGrabber;
import pers.yurwisher.grabber.express.Kuaidi100Grabber;
import pers.yurwisher.grabber.maoyan.BoxOfficeGrabber;
import pers.yurwisher.grabber.singlewindow.SingleWindowGrabber;

/**
 * @author yq
 * @date 2018/12/11 17:09
 * @description grabbers
 * @since V1.0.0
 */
public class Grabbers {

    private Grabbers(){}

    private static CustomsGrabber customsGrabber;
    private static ExchangeRateGrabber exchangeRateGrabber;
    private static Kuaidi100Grabber kuaidi100Grabber;
    private static SingleWindowGrabber singleWindowGrabber;
    private static BoxOfficeGrabber boxOfficeGrabber;

    static {
        customsGrabber = new CustomsGrabber();
        exchangeRateGrabber = new ExchangeRateGrabber(JDomHelper.getDefaultInstance());
        kuaidi100Grabber = new Kuaidi100Grabber();
        singleWindowGrabber = new SingleWindowGrabber();
        boxOfficeGrabber = new BoxOfficeGrabber();
    }

    public static CustomsGrabber getCustomsGrabber() {
        return customsGrabber;
    }

    public static ExchangeRateGrabber getExchangeRateGrabber() {
        return exchangeRateGrabber;
    }

    public static Kuaidi100Grabber getKuaidi100Grabber() {
        return kuaidi100Grabber;
    }

    public static SingleWindowGrabber getSingleWindowGrabber() {
        return singleWindowGrabber;
    }

    public static BoxOfficeGrabber getBoxOfficeGrabber() {
        return boxOfficeGrabber;
    }
}
