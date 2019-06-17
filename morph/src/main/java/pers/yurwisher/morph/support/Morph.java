package pers.yurwisher.morph.support;

import pers.yurwisher.morph.model.builder.CoreConfigBuilder;
import pers.yurwisher.morph.model.builder.CoreModelBuilder;

/**
 * @author yq
 * @date 2019/06/17 15:19
 * @description 水人
 * @since V1.0.0
 */
public class Morph {

    private CoreConfigBuilder configBuilder;
    private CoreModelBuilder modelBuilder;

    public Morph() {
        this.configBuilder = new CoreConfigBuilder();
        this.modelBuilder = new CoreModelBuilder();
    }

    public CoreConfigBuilder configBuilder() {
        return configBuilder;
    }

    public CoreModelBuilder modelBuilder() {
        return modelBuilder;
    }

    public void wave(){
        CoreGenerator coreGenerator = new CoreGenerator();
        coreGenerator.generate(modelBuilder,configBuilder);
    }
}
