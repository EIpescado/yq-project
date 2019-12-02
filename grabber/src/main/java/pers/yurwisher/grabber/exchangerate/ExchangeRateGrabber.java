package pers.yurwisher.grabber.exchangerate;

import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.grabber.GrabException;
import pers.yurwisher.grabber.JDomHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static pers.yurwisher.grabber.Utils.*;

/**
 * @author yq
 * @date 2018/12/11 12:09
 * @description 汇率
 * @since V1.0.0
 */
public class ExchangeRateGrabber{

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateGrabber.class);

    private JDomHelper jDomHelper;

    /**
     * 中行汇率查询页面地址
     */
    //private static final String BANK_OF_CHINA_URL = "http://srh.bankofchina.com/search/whpj/search.jsp";
    /**
     * 2019年12月2日 10:32:12 地址变更
     */
    private static final String BANK_OF_CHINA_URL = "https://srh.bankofchina.com/search/whpj/search_cn.jsp";

    private static final String BANK_OF_CHINA_URL_FORMAT = "http://srh.bankofchina.com/search/whpj/search.jsp?erectDate=%1$s&nothing=%2$s&pjname=%3$s";

    /**数据数量正则(某字符到其后面第一个;的内容)*/
    private static final String RECORD_COUNT_REGEX = "var m_nRecordCount = ([^;]*)";

    /**获取页面页数*/
    private static final String PAGE_SIZE_REGEX = "var m_nPageSize = ([^;]*)";

    private static Pattern RECORD_COUNT_PATTERN = Pattern.compile(RECORD_COUNT_REGEX);

    private static Pattern PAGE_SIZE_PATTERN = Pattern.compile(PAGE_SIZE_REGEX);

    /**
     * 汇率数据时间格式
     */
    private static  final DateTimeFormatter RATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    /**
     * 汇率查询接口时间格式
     */
    private static  final DateTimeFormatter QUERY_RATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static  final DateTimeFormatter FILTER_RATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 线程编号
     */
    private static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);

    /**
     * 抓取汇率线程池
     */
    private static final ExecutorService GRAB_RATE_EXECUTOR = Executors.newCachedThreadPool((Runnable r)->{
        Thread thread = new Thread(r);
        thread.setName("grab_rate_thread_" +  THREAD_NUMBER.getAndIncrement());
        //守护线程
        thread.setDaemon(true);
        return thread;
    });


    public ExchangeRateGrabber(JDomHelper jDomHelper) {
        this.jDomHelper = jDomHelper;
    }

    /**
     * 获取今天的指定币制所有已经公布的汇率
     * @param pjName  币制编码
     * @return 已经公布的汇率
     */
    public List<ChinaBankExchangeRate> getAllTodayRate(String pjName){
        return getAllAppointedTimeRate(LocalDateTime.now(),pjName);
    }

    /**
     * 获取指定时间当天的指定币制所有已经公布的汇率
     * @param time 指定时间
     * @param pjName 指定币制编码
     * @return 已经公布的汇率
     */
    public List<ChinaBankExchangeRate> getAllAppointedTimeRate(LocalDateTime time,String pjName){
        if(time != null){
            String timeStr = time.format(QUERY_RATE_FORMATTER) ;
            return getAllAppointedTimeRate(timeStr,timeStr,pjName);
        }
        return null;
    }

    /**
     * 获取指定时间当天的指定币制所有已经公布的汇率
     * @param time 指定时间 yyyy-MM-dd
     * @param pjName 指定币制编码
     * @return 已经公布的汇率
     */
    public List<ChinaBankExchangeRate> getAllAppointedTimeRate(String time,String pjName){
        return getAllAppointedTimeRate(time,time,pjName);
    }

    /**
     * 获取指定时间点第一个汇率
     * @param time 时间 yyyy-MM-dd
     * @param pjName 币制
     * @return 第一个汇率
     */
    public ChinaBankExchangeRate getFirstRate(LocalDateTime time,String pjName){
        return getLatestAfterDate(time.format(QUERY_RATE_FORMATTER),pjName,"00:00:00");
    }

    /**
     * 从抓取汇率中筛选出离指定时间点最近的汇率
     * @param rateList 汇率
     * @param pointInTime 时间点 yyyy-MM-dd HH:mm:ss
     * @param useAfter 是否使用指定时间点之后的汇率
     * @return 汇率
     */
    private ChinaBankExchangeRate getLatestOneToDate(List<ChinaBankExchangeRate> rateList,String pointInTime,boolean useAfter){
        if(isNotEmpty(rateList)){
            //指定时间的时间戳
            long appointedTimestamp = toEpochMilli(pointInTime,FILTER_RATE_FORMATTER);

            //指定时间之后最近的一条
            ChinaBankExchangeRate nearestOneAfter = null ;
            //指定时间之前最近的一条
            ChinaBankExchangeRate nearestOneBefore = null ;
            long flagTimeAfter = 0L;
            long flagTimeBefore = 0L;
            //时间差值
            long difference;
            //筛选数据
            for(int i = 0 ; i < rateList.size();i ++ ){
                ChinaBankExchangeRate rate = rateList.get(i);
                //汇率发布时间的时间戳
                long ratePublishTimestamp = toEpochMilli(rate.getPublishTime());
                //汇率发布时间 - 指定时间的差值
                difference = ratePublishTimestamp - appointedTimestamp ;
                //离指定时间最近的时间 大于等于指定时间
                if(difference >= 0 ){
                    //第一次
                    if(flagTimeAfter == 0L){
                        flagTimeAfter = difference;
                    }
                    //离指定时间更近
                    if(difference <= flagTimeAfter){
                        flagTimeAfter = difference ;
                        nearestOneAfter = rate;
                    }
                }else {
                    //指定时间之前 负数
                    //第一次
                    if(flagTimeBefore == 0L){
                        flagTimeBefore = difference;
                    }
                    //离指定时间更近
                    if(difference >= flagTimeBefore){
                        flagTimeBefore = difference ;
                        nearestOneBefore = rate;
                    }
                }
            }
            //使用指定时间点之后汇率
            if(useAfter){
                return nearestOneAfter;
            }else {
                return nearestOneBefore ;
            }
        }
        return null;
    }

    /**
     * 取离某个时间点之后最近的一个汇率
     * @param time 汇率查询日期 yyyy-MM-dd
     * @param pjName 货币编码
     * @param hhmmss 时间点 如09:30:00
     * @return 汇率
     */
    public ChinaBankExchangeRate getLatestAfterDate(String time,String pjName,String hhmmss){
        List<ChinaBankExchangeRate> rateList =  getAllAppointedTimeRate(time,pjName);
        return getLatestOneToDate(rateList,appendTime(time,hhmmss),true);
    }

    /**
     * 当前时间 < 截止时间:
     *      1.取到指定时间点之后的汇率,直接返回
     *      2.未取到,返回NULL
     * 当前时间 > 截止时间 :
     *      1.取到指定时间点之后的汇率,发布时间 < 截止时间,直接返回
     * @param time 要获取的汇率时间 yyyy-MM-dd
     * @param pjName 货币中文名称
     * @param hhmmss 指定时间点 如09:30:00
     * @param hhmmssDeadline 是否取指定时间之前的汇率的截止时间 如10:00:00
     * @return  ChinaBankExchangeRate
     */
    public ChinaBankExchangeRate getLatestAfterDate(String time,String pjName,String hhmmss,String hhmmssDeadline){
        List<ChinaBankExchangeRate> rateList =  getAllAppointedTimeRate(time,pjName);
        //指定截止时间的时间戳
        long deadline = toEpochMilli(appendTime(time,hhmmssDeadline),FILTER_RATE_FORMATTER);
        long now = toEpochMilli(LocalDateTime.now());
        return getLatestOneToDate(rateList,appendTime(time,hhmmss),deadline < now);
    }

    /**
     * 获取单个货币在指定时间段内的所有汇率
     * @param startTime 开始时间 yyyy-MM-dd
     * @param endTime 结束时间 yyyy-MM-dd
     * @param pjName 货币在中行的编码
     */
    public List<ChinaBankExchangeRate> getAllAppointedTimeRate(String startTime, String endTime, String pjName){
        String url = String.format(BANK_OF_CHINA_URL_FORMAT,startTime,endTime,pjName) ;
        //获取页面doc
        Document doc = jDomHelper.getDocument(url);
        //获取数据总数 和 总页数
        RateCountAndPage countAndPage = getTotalPage(doc.html());
        if(countAndPage.getTotalNum() == 0){
            return null;
        }else {
            Integer totalPage = countAndPage.getTotalPage();
            if(totalPage > 0 ){
                //首次查询获取首页数据 || 只有一页数据
                List<ChinaBankExchangeRate> rateList = new CopyOnWriteArrayList<>(getOnePageRateData(doc)) ;
                Integer initialValue = 2 ;
                if(totalPage >= initialValue){
                    //任务完成才执行下一个,内部存在阻塞队列BlockingQueue ,没有任务完成，take()方法也会阻塞
                    CompletionService<List<ChinaBankExchangeRate>> completionService = new ExecutorCompletionService<>(GRAB_RATE_EXECUTOR);
                    //从第二页开始，因为第一页已经在首次查询时获取
                    for (int k = initialValue ; k <= totalPage ; k++){
                        //提交执行
                        completionService.submit(getTask(url,k));
                    }
                    Future<List<ChinaBankExchangeRate>> future ;
                    List<ChinaBankExchangeRate> rate ;
                    for(int k = initialValue; k <= totalPage; k ++ ){
                        //检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。
                        try {
                            future = completionService.take();
                            //超时时间获取
                            rate = future.get(jDomHelper.getTimeOut(), TimeUnit.MILLISECONDS);
                            if(isNotEmpty(rate)){
                                rateList.addAll(rate);
                            }
                        } catch (InterruptedException |TimeoutException | ExecutionException e) {
                           logger.error("grab {} page exchangeRate error",e);
                        }
                    }
                }
                return rateList;
            }
            return null ;
        }
    }

    /**
     * 取查询出币制汇率分页总页数
     */
    private RateCountAndPage getTotalPage(String sb){
        int totalPage = 0;
        int totalNum = 0;
        //默认为20
        int pageSizeNum = 20;
        try{
            Matcher matcher = RECORD_COUNT_PATTERN.matcher(sb);
            if (matcher.find()) {
                totalNum = Integer.parseInt(matcher.group(1)) ;
            }

            matcher = PAGE_SIZE_PATTERN.matcher(sb);
            if (matcher.find()) {
                pageSizeNum = Integer.parseInt(matcher.group(1)) ;
            }

            if(totalNum % pageSizeNum == 0){
                totalPage = totalNum / pageSizeNum;
            }else{
                totalPage = totalNum / pageSizeNum + 1;
            }
        }catch(Exception e){
            throw new GrabException("grab exchangeRate total page number error",e);
        }
        RateCountAndPage rateCountAndPage = new RateCountAndPage();
        rateCountAndPage.setTotalNum(totalNum);
        rateCountAndPage.setTotalPage(totalPage);
        return rateCountAndPage;
    }

    /**
     * 获取一页数据
     */
    private List<ChinaBankExchangeRate> getOnePageRateData(Document document){
        List<ChinaBankExchangeRate> list = new CopyOnWriteArrayList<>() ;
        //获取汇率class : BOC_main 的div 下的table 下的所有tr
        Elements trs = document.select("div.BOC_main").get(0).select("table").select("tr") ;
        ChinaBankExchangeRate rate ;
        for (Element tr : trs){
            //有数据行 且不为table头
            int size = tr.children().size();
            if(size == 7 && !("th".equals(tr.children().first().tagName()))){
                rate = new ChinaBankExchangeRate() ;
                rate.setCurrency(tr.child(0).html());
                //现汇买入价
                rate.setBuyingRate(divideOneHundred(string2BigDecimal(tr.child(1).html())));
                //现钞买入价
                rate.setCashBuyingRate(divideOneHundred(string2BigDecimal(tr.child(2).html())));
                //现汇卖出价
                rate.setSellingRate(divideOneHundred(string2BigDecimal(tr.child(3).html())));
                //现钞卖出价
                rate.setCashSellingRate(divideOneHundred(string2BigDecimal(tr.child(4).html())));
                //中行折算价
                rate.setBankConvertRate(divideOneHundred(string2BigDecimal(tr.child(5).html())));
                //发布时间
                rate.setPublishTime(parseRateDate(tr.child(6).html(),RATE_FORMATTER));
                list.add(rate);
            }
        }
        return  list ;
    }

    /**
     * 按页码获取任务
     */
    private Callable<List<ChinaBankExchangeRate>> getTask(final String url,int currentPage){
        return ()->getOnePageRateData(jDomHelper.getDocument(url + "&page=" + currentPage));
    }

    private String appendTime(String time,String hhmmss){
        return time + " " + hhmmss ;
    }

    /**
     * 获取中行货币对应编码及名称
     * @return Map <货币名称,编码>
     */
    public List<ChinaBankCurrency> getAllCurrencyCodeAndName(){
        Document doc =  jDomHelper.getDocument(BANK_OF_CHINA_URL);
        if(doc != null){
            //获取中行货币select
            Element pjname = doc.getElementById("pjname") ;
            //所有货币options
            Elements options =  pjname.children() ;
            if(isNotEmpty(options)){
                return options.stream()
                        .filter(option -> !option.html().equals("选择货币"))
                        .map(option -> {
                            ChinaBankCurrency currency = new ChinaBankCurrency();
                            currency.setCode(option.val());
                            currency.setName(option.html());
                            return currency;
                        }).collect(Collectors.toList());
            }
        }
        return null;
    }

    /**
     * 汇率总页数,和数量
     */
    @Data
    public static class RateCountAndPage{
        Integer totalPage;
        Integer totalNum;
    }

    public static void main(String[] args) {
        ExchangeRateGrabber rateGrabber = new ExchangeRateGrabber(JDomHelper.getDefaultInstance());
        ChinaBankExchangeRate rate = rateGrabber.getFirstRate(LocalDateTime.now(),"1316");
//        List<ChinaBankCurrency> rate = rateGrabber.getAllCurrencyCodeAndName();
        System.out.println(rate.getBuyingRate());
    }

}
