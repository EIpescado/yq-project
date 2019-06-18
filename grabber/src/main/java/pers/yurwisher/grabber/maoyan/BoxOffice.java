package pers.yurwisher.grabber.maoyan;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author yq
 * @date 2019/06/18 13:49
 * @description 票房
 * @since V1.0.0
 */
public class BoxOffice {

    /**
     * 数据更新时间  北京时间14:23:25
     */
    private String updateInfo;
    /**
     * 综合票房 单位
     */
    private String totalBoxUnitInfo;
    /**
     * 分账票房
     */
    private BigDecimal splitTotalBox;
    /**
     * 服务器时间 时间戳
     */
    private Long serverTimestamp;
    /**
     * 貌似是收票
     */
    private Crystal crystal;
    /**
     * 综合票房
     */
    private BigDecimal totalBoxInfo;
    /**
     * 票房前三十位信息
     */
    private List<MovieInfo> list;
    /**
     * 综合票房 单位
     */
    private String totalBoxUnit;
    /**
     * 综合票房
     */
    private BigDecimal totalBox;
    /**
     * 分账票房 单位,如 万
     */
    private String splitTotalBoxUnit;
    /**
     * 查询日期 yyyy-MM-dd
     */
    private Date queryDate;
    /**
     * 服务器时间 yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime serverTime;
    /**
     * 分账票房 单位
     */
    private String splitTotalBoxUnitInfo;
    /**
     * 分账票房
     */
    private BigDecimal splitTotalBoxInfo;

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public String getTotalBoxUnitInfo() {
        return totalBoxUnitInfo;
    }

    public void setTotalBoxUnitInfo(String totalBoxUnitInfo) {
        this.totalBoxUnitInfo = totalBoxUnitInfo;
    }

    public BigDecimal getSplitTotalBox() {
        return splitTotalBox;
    }

    public void setSplitTotalBox(BigDecimal splitTotalBox) {
        this.splitTotalBox = splitTotalBox;
    }

    public Long getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(Long serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }

    public Crystal getCrystal() {
        return crystal;
    }

    public void setCrystal(Crystal crystal) {
        this.crystal = crystal;
    }

    public BigDecimal getTotalBoxInfo() {
        return totalBoxInfo;
    }

    public void setTotalBoxInfo(BigDecimal totalBoxInfo) {
        this.totalBoxInfo = totalBoxInfo;
    }

    public List<MovieInfo> getList() {
        return list;
    }

    public void setList(List<MovieInfo> list) {
        this.list = list;
    }

    public String getTotalBoxUnit() {
        return totalBoxUnit;
    }

    public void setTotalBoxUnit(String totalBoxUnit) {
        this.totalBoxUnit = totalBoxUnit;
    }

    public BigDecimal getTotalBox() {
        return totalBox;
    }

    public void setTotalBox(BigDecimal totalBox) {
        this.totalBox = totalBox;
    }

    public String getSplitTotalBoxUnit() {
        return splitTotalBoxUnit;
    }

    public void setSplitTotalBoxUnit(String splitTotalBoxUnit) {
        this.splitTotalBoxUnit = splitTotalBoxUnit;
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

    public LocalDateTime getServerTime() {
        return serverTime;
    }

    public void setServerTime(LocalDateTime serverTime) {
        this.serverTime = serverTime;
    }

    public String getSplitTotalBoxUnitInfo() {
        return splitTotalBoxUnitInfo;
    }

    public void setSplitTotalBoxUnitInfo(String splitTotalBoxUnitInfo) {
        this.splitTotalBoxUnitInfo = splitTotalBoxUnitInfo;
    }

    public BigDecimal getSplitTotalBoxInfo() {
        return splitTotalBoxInfo;
    }

    public void setSplitTotalBoxInfo(BigDecimal splitTotalBoxInfo) {
        this.splitTotalBoxInfo = splitTotalBoxInfo;
    }
}
