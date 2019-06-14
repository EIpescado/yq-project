package pers.yurwisher.morph.support;

import pers.yurwisher.morph.bean.VoInfo;
import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.common.MorphException;
import pers.yurwisher.morph.common.Utils;
import pers.yurwisher.morph.config.PojoGenerateConfig;

import java.util.Date;

/**
 * @author yq
 * @date 2019/06/11 12:02
 * @description Vo, To.So.Qo.Fo 生产者
 * @since V1.0.0
 */
public class PojoGenerator implements Generator {

    /**
     * 生成类 并返回类全路径名称
     * @param config 配置
     * @param flag 标识Vo,to..
     * @return 类全路径名称
     */
    public String generate(PojoGenerateConfig config, String flag) {
        //生成模版报文对象
        VoInfo info = generateInfo(config, flag);
        //文件输出路径
        String outPutFolderPath = generateOutPutFolderPath(config.getGenerateConfig().getBaseOutPutPath(), info.getPackageName());
        //文件名称
        String fileName = info.getEntityName() + Constant.DOT_JAVA;
        //生成文件
        generate(info, outPutFolderPath, fileName, "/template/vo.java.ftl");
        return info.getPackageName() + Constant.DOT + info.getEntityName();
    }

    /**
     * 生成模版需要的数据对象
     *
     * @param config   配置
     * @param pojoFlag 标识 vo.fo
     */
    private VoInfo generateInfo(PojoGenerateConfig config, String pojoFlag) {
        VoInfo vo = new VoInfo();
        //包名 + 对象类别 组成完整包名 com.xxx.xx.Vo
        vo.setPackageName(newPackageName(config.getPackageName(), pojoFlag));
        vo.setUseLombok(config.isUseLombok());
        vo.setAuthor(config.getGenerateConfig().getAuthor());
        vo.setDescription(newDescription(config.getGenerateConfig().getDescription(), pojoFlag));
        //父类名称
        String superClass = getSuperClassByFlag(pojoFlag, config);
        vo.setSuperClass(superClass);
        vo.setSuperClassName(getClassName(superClass));
        //生成的类名称
        String entityClassName = config.getGenerateConfig().getEntityClass();
        if (Utils.isEmpty(entityClassName)) {
            throw new MorphException("entity class name is empty");
        }
        vo.setEntityName(generateClassName(entityClassName, pojoFlag, true));
        GeneratorHelper.ImportTypeAndFieldInfo info = GeneratorHelper.getImportTypeAndFieldInfo(entityClassName, superClass);
        vo.setFields(info.getFieldInfoList());
        vo.setNeedImportType(info.getImportType());
        //日期
        vo.setDate(new Date());
        return vo;
    }

    /**
     * 根据类型 获取对应的父类
     */
    private String getSuperClassByFlag(String flag, PojoGenerateConfig config) {
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

}
