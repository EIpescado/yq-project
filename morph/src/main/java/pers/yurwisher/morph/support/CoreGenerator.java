package pers.yurwisher.morph.support;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.common.Utils;
import pers.yurwisher.morph.model.ControllerModel;
import pers.yurwisher.morph.model.CoreConfig;
import pers.yurwisher.morph.model.CoreModel;
import pers.yurwisher.morph.model.MapperModel;
import pers.yurwisher.morph.model.ObjectModel;
import pers.yurwisher.morph.model.ServiceModel;
import pers.yurwisher.morph.model.builder.CoreConfigBuilder;
import pers.yurwisher.morph.model.builder.CoreModelBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author yq
 * @date 2019/06/15 12:14
 * @description 核心生成器
 * @since V1.0.0
 */
public class CoreGenerator implements Generator {

    private static final Logger logger = LoggerFactory.getLogger(CoreGenerator.class);

    @Override
    public void generate(CoreModel coreModel, CoreConfig config) {
        //生成pojo
        generatePojo(config, coreModel);
        //生成mapper
        generateMapper(config,coreModel);
        //生成service
        generateService(config, coreModel);
        //生成controller
        generateController(config,coreModel);
    }

    public void generate(CoreModelBuilder builder, CoreConfigBuilder configBuilder) {
        generate(builder.build(),configBuilder.build());
    }

    private void generatePojo(CoreConfig config, CoreModel coreModel) {
        logger.info("开始自动创建Pojo =====================");
        if (config.getVo()) {
            logger.info("vo start=====================");
            generatePojo(config, Constant.VO, coreModel);
        }
        if (config.getQo()) {
            logger.info("qo start=====================");
            generatePojo(config, Constant.QO, coreModel);
        }
        if (config.getFo()) {
            logger.info("fo start=====================");
            generatePojo(config, Constant.FO, coreModel);
        }
        if (config.getSo()) {
            logger.info("so start=====================");
            generatePojo(config, Constant.SO, coreModel);
        }
        if (config.getTo()) {
            logger.info("to start=====================");
            generatePojo(config, Constant.TO, coreModel);
        }
    }

    private void generatePojo(CoreConfig coreConfig, String flag, CoreModel coreModel) {
        ObjectModel objectModel = new ObjectModel(getSuperClassByFlag(flag, coreConfig), coreConfig.getUseLombok(), flag, coreModel);
        generate(objectModel, getFolderPathByFlag(coreModel, flag), getFileName(coreModel, flag), "/template/pojo.java.ftl");
    }

    private void  generateMapper(CoreConfig coreConfig, CoreModel coreModel){
        MapperModel mapperModel = new MapperModel(coreModel,coreConfig.getMapperSuperClass(),coreConfig);
        //mapper
        generate(mapperModel, mapperFolderPath(coreModel), mapperFileName(coreModel,Constant.DOT_JAVA), "/template/mapper.java.ftl");
        //mapper xml
        generate(mapperModel, mapperXmlFolderPath(coreModel,coreConfig), mapperFileName(coreModel,Constant.DOT_XML), "/template/mapper.xml.ftl");
    }

    private void generateService(CoreConfig coreConfig, CoreModel coreModel) {
        ServiceModel serviceModel = new ServiceModel(coreModel, coreConfig.getServiceSuperClass(),coreConfig.getServiceImplSuperClass(),coreConfig);
        //service接口
        generate(serviceModel, serviceFolderPath(coreModel, false), serviceFileName(coreModel, false), "/template/service.java.ftl");
        //service实现类
        generate(serviceModel, serviceFolderPath(coreModel, true), serviceFileName(coreModel, true), "/template/serviceImpl.java.ftl");
    }

    private String mapperFolderPath(CoreModel coreModel){
        StringBuilder sb = new StringBuilder(coreModel.getBasePackage());
        sb.append(Constant.DOT).append("mapper");
        return generateOutPutFolderPath(coreModel.getModule(),false,sb.toString());
    }


    private String mapperXmlFolderPath(CoreModel coreModel,CoreConfig coreConfig){
        String mapperLocation = coreConfig.getMapperLocation();
        if(Utils.isEmpty(mapperLocation)){
            mapperLocation = "mapper";
        }
        return generateOutPutFolderPath(coreModel.getModule(),true,mapperLocation);
    }

    private String serviceFolderPath(CoreModel coreModel, boolean impl) {
        StringBuilder sb = new StringBuilder(coreModel.getBasePackage());
        sb.append(Constant.DOT).append("service");
        if (impl) {
            sb.append(Constant.DOT).append("impl");
        }
        return generateOutPutFolderPath(coreModel.getModule(),false,sb.toString());
    }

    private String mapperFileName(CoreModel coreModel,String suffix){
        StringBuilder sb = new StringBuilder(coreModel.getEntityName());
        sb.append("Mapper");
        return sb.append(suffix).toString();
    }

    private String serviceFileName(CoreModel coreModel, boolean impl) {
        StringBuilder sb = new StringBuilder(coreModel.getEntityName());
        sb.append("Service");
        if (impl) {
            sb.append("Impl");
        } else {
            sb.insert(0, "I");
        }
        return sb.append(Constant.DOT_JAVA).toString();
    }

    private void generateController(CoreConfig coreConfig, CoreModel coreModel) {
        ControllerModel controllerModel = new ControllerModel(coreModel, coreConfig.getControllerSuperClass(),coreConfig);
        generate(controllerModel, controllerFolderPath(coreModel), controllerFileName(coreModel), "/template/controller.java.ftl");
    }

    private String controllerFolderPath(CoreModel coreModel){
        StringBuilder sb = new StringBuilder(coreModel.getBasePackage());
        sb.append(Constant.DOT).append("controller");
        return generateOutPutFolderPath(coreModel.getModule(),false,sb.toString());
    }

    private String controllerFileName(CoreModel coreModel){
        StringBuilder sb = new StringBuilder(coreModel.getEntityName());
        sb.append("Controller").append(Constant.DOT_JAVA);
        return sb.toString();
    }

    /**
     * 生成各类对象输出文件夹
     */
    private String getFolderPathByFlag(CoreModel coreModel, String flag) {
        StringBuilder sb = new StringBuilder(coreModel.getBasePackage());
        sb.append(Constant.DOT).append("pojo").append(Constant.DOT).append(flag.toLowerCase());
        return generateOutPutFolderPath(coreModel.getModule(),false,sb.toString());
    }

    /**
     * 生成各类对象输出文件夹
     */
    private String getFileName(CoreModel coreModel, String flag) {
        StringBuilder sb = new StringBuilder(coreModel.getEntityName());
        sb.append(flag).append(Constant.DOT_JAVA);
        return sb.toString();
    }

    /**
     * 根据类型 获取对应的父类
     */
    private String getSuperClassByFlag(String flag, CoreConfig config) {
        String x;
        switch (flag) {
            case Constant.VO:
                x = config.getVoSuperClass();
                break;
            case Constant.QO:
                x = config.getQoSuperClass();
                break;
            case Constant.FO:
                x = config.getFoSuperClass();
                break;
            case Constant.SO:
                x = config.getSoSuperClass();
                break;
            case Constant.TO:
                x = config.getToSuperClass();
                break;
            default:
                x = null;
                break;
        }
        return x;
    }

    /**
     * 生成文件输出路径
     * @param module    模块名
     * @param resource 是否为resource
     * @return 文件输出路径
     */
    private String generateOutPutFolderPath(String module, boolean resource, String packageName){
        String basePath = resource ? "src/main/resources" : "src/main/java";
        String[] array = packageName.split("\\.");
        StringBuilder sb = new StringBuilder(basePath);
        sb.append(File.separator);
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(File.separator);
        }
        //有模块插入模块
        if(!Utils.isEmpty(module)){
            sb.insert(0,module + File.separator);
        }
        return sb.toString();
    }

    /**
     * 生成文件
     * @param info 模版消息
     * @param outPutFolderPath 文件夹
     * @param fileName 文件名称带后缀
     * @param templateName 模版路径
     */
    private void generate(Object info, String outPutFolderPath, String fileName ,String templateName) {
        Template template = ConfigurationHolder.getInstance().getTemplate(templateName);
        FileOutputStream fileOutputStream = null;
        try {
            File folder = new File(outPutFolderPath);
            //创建文件夹
            Utils.mkDir(folder);
            File javaFile = new File(folder, fileName);
            fileOutputStream = new FileOutputStream(javaFile);
            template.process(info, new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
        } catch (TemplateException | IOException e) {
            logger.error("template process error", e);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    logger.error("close fileOutputStream error", e);
                }
            }
        }
    }

}
