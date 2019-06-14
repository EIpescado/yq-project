package pers.yurwisher.morph.config;

import pers.yurwisher.morph.support.MultipleGenerator;

/**
 * @author yq
 * @date 2019/06/14 10:35
 * @description
 * @since V1.0.0
 */
public class Morph {

    private  GenerateConfigBuilder generateConfigBuilder = new GenerateConfigBuilder();
    private  PojoGenerateConfigBuilder pojoGenerateConfigBuilder = new PojoGenerateConfigBuilder();
    private  ServiceGenerateConfigBuilder serviceGenerateConfigBuilder = new ServiceGenerateConfigBuilder();
    private  MultipleGenerator generator = new MultipleGenerator();

    public GenerateConfigBuilder generateConfigBuilder() {
        return generateConfigBuilder;
    }

    public PojoGenerateConfigBuilder pojoGenerateConfigBuilder() {
        return pojoGenerateConfigBuilder;
    }

    public ServiceGenerateConfigBuilder serviceGenerateConfigBuilder() {
        return serviceGenerateConfigBuilder;
    }

    public void generate(){
        GenerateConfig generateConfig = generateConfigBuilder.build();
        PojoGenerateConfig pojoGenerateConfig = pojoGenerateConfigBuilder.setGenerateConfig(generateConfig).build();
        ServiceGenerateConfig serviceGenerateConfig = serviceGenerateConfigBuilder().setGenerateConfig(generateConfig).build();
        generator.generate(pojoGenerateConfig,serviceGenerateConfig);
    }

}
