package pers.yurwisher.grabber.maoyan;

import java.math.BigDecimal;

/**
 * @author yq
 * @date 2019/06/18 14:10
 * @description
 * @since V1.0.0
 */
public class MovieInfo {
    /**
     * 排片场次
     */
    private Integer showInfo;
    /**
     * 票房占比  25%
     */
    private String boxRate;
    /**
     * 场均人次
     */
    private Integer avgShowView;
    private String seatRate;
    private String movieId;
    /**
     * 上座率 1.5%
     */
    private String avgSeatView;
    private String refundViewRate;
    private String releaseInfoColor;
    /**
     * 综合票房
     */
    private BigDecimal boxInfo;
    /**
     * 观看人次 带单位
     */
    private String viewInfoV2;
    private String splitAvgViewBox;
    /**
     * 电影名称
     */
    private String movieName;
    /**
     * 总分账票房 1.87亿
     */
    private String splitSumBoxInfo;
    private String refundViewInfo;
    /**
     * 上映天数  上映5天
     */
    private String releaseInfo;
    /**
     * 分账票房  票房占比 28.5%
     */
    private String splitBoxRate;
    /**
     * 总票房 2.5亿
     */
    private String sumBoxInfo;
    /**
     * 分账票房
     */
    private BigDecimal splitBoxInfo;
    /**
     * 排片占比 29.2%
     */
    private String showRate;
    /**
     * 观看人次
     */
    private String viewInfo;
    private Milestone milestone;
    /**
     * 场均票房
     */
    private String avgViewBox;
    private String myRefundRateInfo;
    private String myRefundNumInfo;
    private String onlineBoxRate;

    public Integer getShowInfo() {
        return showInfo;
    }

    public void setShowInfo(Integer showInfo) {
        this.showInfo = showInfo;
    }

    public String getBoxRate() {
        return boxRate;
    }

    public void setBoxRate(String boxRate) {
        this.boxRate = boxRate;
    }

    public Integer getAvgShowView() {
        return avgShowView;
    }

    public void setAvgShowView(Integer avgShowView) {
        this.avgShowView = avgShowView;
    }

    public String getSeatRate() {
        return seatRate;
    }

    public void setSeatRate(String seatRate) {
        this.seatRate = seatRate;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getAvgSeatView() {
        return avgSeatView;
    }

    public void setAvgSeatView(String avgSeatView) {
        this.avgSeatView = avgSeatView;
    }

    public String getRefundViewRate() {
        return refundViewRate;
    }

    public void setRefundViewRate(String refundViewRate) {
        this.refundViewRate = refundViewRate;
    }

    public String getReleaseInfoColor() {
        return releaseInfoColor;
    }

    public void setReleaseInfoColor(String releaseInfoColor) {
        this.releaseInfoColor = releaseInfoColor;
    }

    public BigDecimal getBoxInfo() {
        return boxInfo;
    }

    public void setBoxInfo(BigDecimal boxInfo) {
        this.boxInfo = boxInfo;
    }

    public String getViewInfoV2() {
        return viewInfoV2;
    }

    public void setViewInfoV2(String viewInfoV2) {
        this.viewInfoV2 = viewInfoV2;
    }

    public String getSplitAvgViewBox() {
        return splitAvgViewBox;
    }

    public void setSplitAvgViewBox(String splitAvgViewBox) {
        this.splitAvgViewBox = splitAvgViewBox;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getSplitSumBoxInfo() {
        return splitSumBoxInfo;
    }

    public void setSplitSumBoxInfo(String splitSumBoxInfo) {
        this.splitSumBoxInfo = splitSumBoxInfo;
    }

    public String getRefundViewInfo() {
        return refundViewInfo;
    }

    public void setRefundViewInfo(String refundViewInfo) {
        this.refundViewInfo = refundViewInfo;
    }

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(String releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    public String getSplitBoxRate() {
        return splitBoxRate;
    }

    public void setSplitBoxRate(String splitBoxRate) {
        this.splitBoxRate = splitBoxRate;
    }

    public String getSumBoxInfo() {
        return sumBoxInfo;
    }

    public void setSumBoxInfo(String sumBoxInfo) {
        this.sumBoxInfo = sumBoxInfo;
    }

    public BigDecimal getSplitBoxInfo() {
        return splitBoxInfo;
    }

    public void setSplitBoxInfo(BigDecimal splitBoxInfo) {
        this.splitBoxInfo = splitBoxInfo;
    }

    public String getShowRate() {
        return showRate;
    }

    public void setShowRate(String showRate) {
        this.showRate = showRate;
    }

    public String getViewInfo() {
        return viewInfo;
    }

    public void setViewInfo(String viewInfo) {
        this.viewInfo = viewInfo;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public String getAvgViewBox() {
        return avgViewBox;
    }

    public void setAvgViewBox(String avgViewBox) {
        this.avgViewBox = avgViewBox;
    }

    public String getMyRefundRateInfo() {
        return myRefundRateInfo;
    }

    public void setMyRefundRateInfo(String myRefundRateInfo) {
        this.myRefundRateInfo = myRefundRateInfo;
    }

    public String getMyRefundNumInfo() {
        return myRefundNumInfo;
    }

    public void setMyRefundNumInfo(String myRefundNumInfo) {
        this.myRefundNumInfo = myRefundNumInfo;
    }

    public String getOnlineBoxRate() {
        return onlineBoxRate;
    }

    public void setOnlineBoxRate(String onlineBoxRate) {
        this.onlineBoxRate = onlineBoxRate;
    }
}
