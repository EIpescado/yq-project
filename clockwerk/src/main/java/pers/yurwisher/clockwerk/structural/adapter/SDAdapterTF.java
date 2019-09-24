package pers.yurwisher.clockwerk.structural.adapter;

/**
 * @author yq
 * @date 2019/09/20 09:23
 * @description SD适配TF
 * @since V1.0.0
 */
public class SDAdapterTF implements SDCard {
    private TFCard tf ;

    public SDAdapterTF(TFCard tf) {
        this.tf = tf;
    }

    @Override
    public String read() {
        return tf.read();
    }

    @Override
    public void write(String msg) {
        tf.write(msg);
    }
}
