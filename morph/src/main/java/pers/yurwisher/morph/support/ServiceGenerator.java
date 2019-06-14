package pers.yurwisher.morph.support;

import pers.yurwisher.morph.bean.ServiceImplInfo;
import pers.yurwisher.morph.bean.ServiceInfo;
import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.config.ServiceGenerateConfig;

import java.util.Date;

/**
 * @author yq
 * @date 2019/06/11 12:02
 * @description Service 生产者
 * @since V1.0.0
 */
public class ServiceGenerator implements Generator{

    public void generate(ServiceGenerateConfig config){
        //生成模版报文对象
        ServiceInfo info = generateInfo(config);
        //文件输出路径
        String outPutFolderPath = generateOutPutFolderPath(config.getGenerateConfig().getBaseOutPutPath(),config.getServicePackageName());
        //文件名称
        String fileName = info.getServiceName() + Constant.DOT_JAVA;
        //生成文件
        generate(info,outPutFolderPath,fileName,"/template/service.java.ftl");

        //生成impl
        ServiceImplInfo implInfo =  generateImplInfo(info);
        //文件输出路径
        outPutFolderPath = generateOutPutFolderPath(config.getGenerateConfig().getBaseOutPutPath(),implInfo.getServiceImplPackageName());
        //文件名称
        fileName = implInfo.getServiceName() + Constant.DOT_JAVA;
        //生成文件
        generate(implInfo,outPutFolderPath,fileName,"/template/serviceImpl.java.ftl");
    }

    private ServiceInfo generateInfo(ServiceGenerateConfig config) {
        ServiceInfo info = new ServiceInfo();
        info.setIdType(config.getGenerateConfig().getIdType());
        info.setAuthor(config.getGenerateConfig().getAuthor());
        //描述
        info.setDescription(config.getGenerateConfig().getDescription() + "service");
        info.setDate(new Date());
        String entityClass = config.getGenerateConfig().getEntityClass();
        info.setEntityClass(entityClass);
        info.setEntityName(getClassName(entityClass));
        info.setServiceName(generateClassName(info.getEntityName(),"I",false) + "Service");
        info.setServicePackageName(config.getServicePackageName());
        info.setSuperServiceClass(config.getSuperServiceClass());
        info.setSuperServiceName(getClassName(config.getSuperServiceClass()));
        info.setVoClass(config.getVoClass());
        info.setFoClass(config.getFoClass());
        info.setQoClass(config.getQoClass());
        info.setSoClass(config.getSoClass());
        info.setToClass(config.getToClass());
        return info;
    }

    private ServiceImplInfo generateImplInfo(ServiceInfo serviceInfo){
        ServiceImplInfo info = new ServiceImplInfo();
        info.setIdType(serviceInfo.getIdType());
        info.setAuthor(serviceInfo.getAuthor());
        //描述
        info.setDescription(serviceInfo.getDescription() + "serviceImpl");
        info.setDate(new Date());
        info.setEntityClass(serviceInfo.getEntityClass());
        info.setEntityName(serviceInfo.getEntityName());

        info.setServiceName(generateClassName(serviceInfo.getEntityName(),"ServiceImpl",true));
        info.setSuperServiceClass(serviceInfo.getServicePackageName() + Constant.DOT + serviceInfo.getServiceName());
        info.setSuperServiceName(serviceInfo.getServiceName());
        //包名
        info.setServiceImplPackageName(serviceInfo.getServicePackageName() + Constant.DOT + "impl");
        //父实现类
        info.setBaseImplServiceName(serviceInfo.getSuperServiceName() + "Impl");
        //mapper
        info.setMapperPackageName(serviceInfo.getServicePackageName().substring(0,serviceInfo.getServicePackageName().lastIndexOf(Constant.DOT) + 1) + "mapper");
        info.setVoClass(serviceInfo.getVoClass());
        info.setFoClass(serviceInfo.getFoClass());
        info.setQoClass(serviceInfo.getQoClass());
        info.setSoClass(serviceInfo.getSoClass());
        info.setToClass(serviceInfo.getToClass());
        return info;
    }

}
