package pers.yurwisher.grabber.express;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.grabber.HttpClientHelper;
import pers.yurwisher.grabber.Utils;

import java.util.Collections;

/**
 * @author yq
 * @date 2018/12/11 15:08
 * @description kuaidi100
 * @since V1.0.0
 */
public class Kuaidi100Grabber {

    private static final Logger logger = LoggerFactory.getLogger(Kuaidi100Grabber.class);

    private static final String URL = "http://www.kuaidi100.com/query?type=%1$s&postid=%2$s";

    /**根据单号查询快递类型地址*/
    private static final String QUERY_TYPE_URL = "http://www.kuaidi100.com/autonumber/autoComNum";


    /**
     * 构建请求地址
     * @param wayBillNo 快递单号
     * @param  expressCompanyNameCode 快递公司在快递100的编码
     * @return 请求地址
     */
    private String buildUrl(String wayBillNo,String expressCompanyNameCode){
        return String.format(URL,expressCompanyNameCode,wayBillNo);
    }

    /**
     * 根据单号自动识别快递公司获取编码
     * @param expressNo 快递单号
     * @return 快递公司获取编码
     */
    public String obtainExpressType(String expressNo){
        String returnStr = HttpClientHelper.getInstance().postForm(QUERY_TYPE_URL, Collections.singletonList(new BasicNameValuePair("text",expressNo)));
        JSONObject data = JSON.parseObject(returnStr);
        JSONArray array = data.getJSONArray("auto");
        String type = null ;
        if(Utils.isNotEmpty(array)){
            type = array.getJSONObject(0).getString("comCode");
            logger.debug("快递号 {} 快递类型: {}",expressNo,type);
        }
        if(!Utils.isNotEmpty(type)){
            logger.debug("未获取到快递号 {} 快递类型",expressNo);
        }
        return type;
    }

    /**
     * 查询快递信息
     * @param wayBillNo 快递单号
     * @param expressCompanyNameCode 快递公司在快递100的编码
     * @return 快递信息
     */
    public KuaiDi100Info queryExpressInfo(String wayBillNo, String expressCompanyNameCode){
        String result = HttpClientHelper.getInstance().sendGet(buildUrl(wayBillNo,expressCompanyNameCode));
        if(!Utils.isNotEmpty(result)){
            return null;
        }
        return JSON.parseObject(result,KuaiDi100Info.class) ;
    }

    /**
     * 查询快递信息,自动根据单号获取快递公司编码
     * @param wayBillNo 快递单号
     * @return 快递信息
     */
    public KuaiDi100Info queryExpressInfo(String wayBillNo){
        //获取快递公司编码
        String type = obtainExpressType(wayBillNo);
        if(!Utils.isNotEmpty(type)){
            return null;
        }
        return queryExpressInfo(wayBillNo,type);
    }

    public static void main(String[] args) {

    }

}
