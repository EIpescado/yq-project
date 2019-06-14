package pers.yurwisher.morph.support;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.common.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author yq
 * @date 2019/06/13 15:17
 * @description 生成器
 * @since V1.0.0
 */
public interface Generator {

    Logger logger = LoggerFactory.getLogger(Generator.class);

    /**
     * 生成文件输出路径
     * @param basePath    根路径
     * @param packageName 包名
     * @return 文件输出路径
     */
    default String generateOutPutFolderPath(String basePath, String packageName){
        String[] array = packageName.split("\\.");
        StringBuilder sb = new StringBuilder(basePath);
        if (!basePath.endsWith(File.separator)) {
            sb.append(File.separator);
        }
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(File.separator);
        }
        return sb.toString();
    }

    /**
     * 生成文件名称
     * @param entityClass 实体类名
     * @param extra 拼接的字符
     * @param suffix 后缀
     * @return 文件名称
     */
    default String generateClassName(String entityClass, String extra ,boolean suffix) {
        String[] array = entityClass.split("\\.");
        StringBuilder sb = new StringBuilder(array[array.length - 1]);
        if(suffix){
            //文件名 如XXVo
            sb.append(extra);
        }else {
            sb.insert(0,extra);
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
    default void generate(Object info, String outPutFolderPath, String fileName ,String templateName) {
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

    /**
     * 生成新的包名
     * @param originalPackageName 根包
     * @param moduleName 模块名称
     * @return 包名
     */
    default String newPackageName(String originalPackageName,String moduleName){
        return originalPackageName + Constant.DOT +  moduleName.toLowerCase();
    }

    /**
     * 生成新的包名
     * @param description 根包
     * @param moduleName 模块名称
     * @return 包名
     */
    default String newDescription(String description,String moduleName){
        return description  +  moduleName.toLowerCase();
    }

    /**
     * 获取类的简单名称
     * @param fullClassName 全路径类名
     * @return 类名
     */
    default String getClassName(String fullClassName){
        return !Utils.isEmpty(fullClassName) ? fullClassName.substring(fullClassName.lastIndexOf(Constant.DOT) + 1) : null;
    }
}
