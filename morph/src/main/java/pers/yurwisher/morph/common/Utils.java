package pers.yurwisher.morph.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author yq
 * @date 2019/06/12 10:05
 * @description
 * @since V1.0.0
 */
public class Utils {

    private Utils() {

    }

    private static final List<String> JAVA_BASE_TYPE = new ArrayList<String>() {
        private static final long serialVersionUID = -7233595460223344345L;

        {
            add("byte");
            add("short");
            add("char");
            add("int");
            add("long");
            add("float");
            add("double");
            add("boolean");
        }
    };

    public static boolean isBaseJavaType(String type) {
        return JAVA_BASE_TYPE.contains(type);
    }

    public static boolean isEmpty(String type) {
        return type == null || type.length() == 0;
    }

    public static boolean isEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }

    public static int size(Collection collection){
        return isEmpty(collection) ? 0 : collection.size();
    }

    public static void mkDir(File file) {
        if (file.getParentFile().exists()) {
            file.mkdir();
        } else {
            mkDir(file.getParentFile());
            file.mkdir();
        }
    }

}
