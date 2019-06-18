package pers.yurwisher.grabber.maoyan;

import java.util.Date;

/**
 * @author yq
 * @date 2019/06/18 14:11
 * @description 里程牌
 * @since V1.0.0
 */
public class Milestone {

    private Date dateTime;
    /**
     * 里程碑  如 破2亿
     */
    private String boxCopy;
    private String imageUrl;
    private String box;
    private String movieId;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getBoxCopy() {
        return boxCopy;
    }

    public void setBoxCopy(String boxCopy) {
        this.boxCopy = boxCopy;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = box;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
