package pers.yurwisher.grabber.maoyan;

import java.math.BigDecimal;

/**
 * @author yq
 * @date 2019/06/18 14:08
 * @description
 * @since V1.0.0
 */
public class Crystal {
    /**
     * 售出票数量
     */
    private BigDecimal viewInfo;
    private String maoyanViewInfo;
    /**
     * 万张
     */
    private String viewUnitInfo;
    private String status;

    public BigDecimal getViewInfo() {
        return viewInfo;
    }

    public void setViewInfo(BigDecimal viewInfo) {
        this.viewInfo = viewInfo;
    }

    public String getMaoyanViewInfo() {
        return maoyanViewInfo;
    }

    public void setMaoyanViewInfo(String maoyanViewInfo) {
        this.maoyanViewInfo = maoyanViewInfo;
    }

    public String getViewUnitInfo() {
        return viewUnitInfo;
    }

    public void setViewUnitInfo(String viewUnitInfo) {
        this.viewUnitInfo = viewUnitInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
