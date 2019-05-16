package pers.yurwisher.grabber.singlewindow;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yq
 * @date 2019/05/16 15:40
 * @description
 * @since V1.0.0
 */
public interface OutputStreamCreater {

    OutputStream create() throws IOException;
}
