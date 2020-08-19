package pers.yurwisher.wechat.mp.template;


import java.util.ArrayList;
import java.util.List;

/**
 * @author yq
 * @date 2018/07/03 09:44
 * @description 模版消息构造
 * @since V1.0.0
 */
public class TemplateDataBuilder {

    private List<TemplateData> list ;

    public TemplateDataBuilder(int size) {
        this.list = new ArrayList<>(size);
    }

    public TemplateDataBuilder() {
        this(6);
    }

    public TemplateDataBuilder push(String key,String value){
        list.add(new TemplateData(key,value));
        return this;
    }

    public List<TemplateData> build(){
        return list;
    }
}
