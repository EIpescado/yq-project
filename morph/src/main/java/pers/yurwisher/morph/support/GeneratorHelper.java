package pers.yurwisher.morph.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.morph.model.FieldInfo;
import pers.yurwisher.morph.common.Constant;
import pers.yurwisher.morph.common.MorphException;
import pers.yurwisher.morph.common.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yq
 * @date 2019/06/13 09:54
 * @description
 * @since V1.0.0
 */
public class GeneratorHelper {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorHelper.class);

    public static class ImportTypeAndFieldInfo{
        private Set<String> importType;
        private List<FieldInfo> fieldInfoList;

        public ImportTypeAndFieldInfo(int size) {
            this.importType = new HashSet<>(size);
            this.fieldInfoList = new ArrayList<>(size);
        }

        public Set<String> getImportType() {
            return importType;
        }

        public List<FieldInfo> getFieldInfoList() {
            return fieldInfoList;
        }
    }

    /**
     * 获取类中的属性名称和类型
     * @param entityClass 原始类
     * @param superClass 生成的类的父类
     */
    public static ImportTypeAndFieldInfo getImportTypeAndFieldInfo(String entityClass, String superClass){
        Class clazz;
        //生成的类的父类
        Class superClazz ;
        //生成的类的所有属性
        List<Field> fieldList ;
        try {
            clazz = Class.forName(entityClass);
            //原始类的所有属性
            fieldList = getClassALLField(clazz);
            logger.info("原始类包含属性:{}条",Utils.size(fieldList));
            if (!Utils.isEmpty(superClass)) {
                superClazz = Class.forName(superClass);
                //生成的类的父类的所有属性
                List<Field> superFieldList = getClassALLField(superClazz);
                logger.info("生成类父类包含属性:{}条",Utils.size(superFieldList));
                if(!Utils.isEmpty(superFieldList)){
                    //移除掉父类中有的
                    fieldList.removeAll(superFieldList);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new MorphException("class not found");
        }
        logger.info("生成类最终属性:{}条",Utils.size(fieldList));
        ImportTypeAndFieldInfo wrapper = new ImportTypeAndFieldInfo(fieldList.size());
        if(!Utils.isEmpty(fieldList)){
            for (Field field : fieldList) {
                int modifier = field.getModifiers();
                //静态或者final属性就不导入
                if (Modifier.isStatic(modifier) || Modifier.isFinal(modifier)) {
                    continue;
                }
                String type = field.getType().getName();
                StringBuilder simpleNameBuilder = new StringBuilder(field.getType().getSimpleName());
                //泛型
                String fxType = getFx(field);
                if (!Utils.isEmpty(fxType)) {
                    if (needImport(fxType)) {
                        wrapper.getImportType().add(fxType);
                    }
                    String simpleFxType = fxType.substring(fxType.lastIndexOf(Constant.DOT) + 1);
                    //组装泛型List<BigDecimal>
                    simpleNameBuilder.append(Constant.LEFT_BRACKET).append(simpleFxType).append(Constant.RIGHT_BRACKET);
                }
                if (needImport(type)) {
                    wrapper.getImportType().add(type);
                }
                wrapper.getFieldInfoList().add(new FieldInfo(simpleNameBuilder.toString(), field.getName()));
            }
        }
        return wrapper;
    }

    private static List<Field> getClassALLField(Class clazz){
        Field [] fields = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<>(fields.length);
        Collections.addAll(list,fields);
        if(clazz.getSuperclass() != null){
            list.addAll(getClassALLField(clazz.getSuperclass()));
        }
        return list;
    }

    /**
     * 非java.lang包和基础类型才需要导包
     */
    private static boolean needImport(String type) {
        return !type.startsWith("java.lang") && !Utils.isBaseJavaType(type);
    }

    /**
     * 泛型
     */
    private static String getFx(Field field) {
        //如java.util.List<java.math.BigDecimal>
        String name = field.getGenericType().getTypeName();
        //包含左括号则代表存在泛型
        if (name.contains(Constant.LEFT_BRACKET)) {
            //泛型的全路径名 java.math.BigDecimal
            return name.substring(name.lastIndexOf(Constant.LEFT_BRACKET) + 1, name.lastIndexOf(Constant.RIGHT_BRACKET));
        }
        return null;
    }

}
