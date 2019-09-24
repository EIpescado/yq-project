package pers.yurwisher.clockwerk.structural.proxy;

/**
 * @author yq
 * @date 2019/09/20 18:59
 * @description 实际图片接口
 * @since V1.0.0
 */
public class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        this.loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("loading file " + fileName);
    }
}
