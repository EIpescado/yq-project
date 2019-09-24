package pers.yurwisher.clockwerk.structural.proxy;

/**
 * @author yq
 * @date 2019/09/20 19:01
 * @description
 * @since V1.0.0
 */
public class ImageProxy implements Image {
    private RealImage realImage;
    private String fileName;

    public ImageProxy( String fileName) {
        this.fileName = fileName;
        this.realImage = new RealImage(fileName);
    }

    @Override
    public void display() {
        if(realImage == null){
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
