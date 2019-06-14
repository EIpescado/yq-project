package pers.yurwisher.morph.support;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.morph.common.MorphException;

import java.io.IOException;

/**
 * @author yq
 * @date 2019/06/11 12:18
 * @description freemarker配置持有者
 * @since V1.0.0
 */
public class ConfigurationHolder {

    private static final Logger logger = LoggerFactory.getLogger(ConfigurationHolder.class);
    private static ConfigurationHolder ourInstance = new ConfigurationHolder();
    private Configuration configuration ;

    public static ConfigurationHolder getInstance() {
        return ourInstance;
    }

    private ConfigurationHolder() {
        configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        //模版跟路径
        this.configuration.setClassForTemplateLoading(ConfigurationHolder.class, "/");
    }

    public Template getTemplate(String name){
        try {
            return configuration.getTemplate(name);
        } catch (IOException e) {
            logger.error("get template {} error",name,e);
            throw new MorphException("get template error");
        }
    }
}
