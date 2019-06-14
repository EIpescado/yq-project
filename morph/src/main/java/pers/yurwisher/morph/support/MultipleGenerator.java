package pers.yurwisher.morph.support;

import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.config.PojoGenerateConfig;
import pers.yurwisher.morph.config.ServiceGenerateConfig;

/**
 * @author yq
 * @date 2019/06/11 12:02
 * @description Vo, To.So.Qo.Fo 生产者
 * @since V1.0.0
 */
public class MultipleGenerator implements Generator{

    private ServiceGenerator serviceGenerator;

    private PojoGenerator pojoGenerator;

    public MultipleGenerator() {
        this.pojoGenerator = new PojoGenerator();
        this.serviceGenerator = new ServiceGenerator();
    }

    public void generate(PojoGenerateConfig config, ServiceGenerateConfig serviceGenerateConfig) {
        logger.info("开始自动创建Pojo =====================");
        if (config.getVo()) {
            logger.info("vo start=====================");
            serviceGenerateConfig.setVoClass(pojoGenerator.generate(config, Constant.VO));
        }
        if (config.getQo()) {
            logger.info("qo start=====================");
            serviceGenerateConfig.setQoClass(pojoGenerator.generate(config, Constant.QO));
        }
        if (config.getFo()) {
            logger.info("fo start=====================");
            serviceGenerateConfig.setFoClass(pojoGenerator.generate(config, Constant.FO));
        }
        if (config.getSo()) {
            logger.info("so start=====================");
            serviceGenerateConfig.setSoClass(pojoGenerator.generate(config, Constant.SO));
        }
        if (config.getTo()) {
            logger.info("to start=====================");
            serviceGenerateConfig.setToClass(pojoGenerator.generate(config, Constant.TO));
        }
        logger.info("创建Pojo结束 =====================");

        logger.info("创建service开始 =====================");
        serviceGenerator.generate(serviceGenerateConfig);
        logger.info("创建service结束 =====================");
    }
}
