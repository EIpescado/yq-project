package pers.yurwisher.grabber.singlewindow;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yq
 * @date 2019/05/06 17:55
 * @description 单一窗口报关单列表返回结果
 * @since V1.0.0
 */
@Data
public class SingleWindowsDeclarationResult implements Serializable {

    private static final long serialVersionUID = 9004203075129572480L;

    private Long total;

    private List<Declaration> rows;

    @Data
    public static class Declaration implements Serializable{
        private static final long serialVersionUID = 1372719371114388190L;
        private String tradeModeCode;
        private String customMasterName;
        private String agentCode;
        private String despPortCode;
        private String supvModeCdde;
        private String entDeclNo;
        private String ciqDecStatusName;
        /**海关编号*/
        private String entryId;
        private String declPersonName;
        private String ciqVoyageNo;
        private String loginName;
        private String iEPortName;
        /**提运单号*/
        private String billNo;
        private String chktstFlag;
        private String extendField;
        private String packNo;
        private String ciqDecStatus;
        private String cusDecStatus;
        private String transPreNo;
        private String tableFlag;
        private String inspPutrecNo;
        private String splitBillLadNo;
        private String ciqEntyPortCode;
        private String cusTradeNationCode;
        private String consignorCode;
        private String consigneeCode;
        private String queryPage;
        private String begin;
        private String grossWt;
        private String contTel;
        private String cusDecStatusName;
        private String updateTimeEnd;
        private String ownerScc;
        private String cusOrgCode;
        private String ownerCode;
        private String consignorEname;
        private String contractNo;
        /**统一编号*/
        private String cusCiqNo;
        private String declDateEnd;
        private String stName;
        /**进出口标志*/
        private String cusIEFlagName;
        private String entUuid;
        private String cusRemark;
        private String dclTrnRelFlag;
        /**和统一编号一样 - -!*/
        private String spDecSeqNo;
        private String inspPlaceCode;
        private String agentScc;
        private String clientId;
        private String consignorCname;
        private String etpsCategory;
        private String cusIEFlag;
        private String updateUser;
        /**最后操作时间*/
        private String updateTime;
        private String rcvgdTradeScc;
        private String ciqQueryFlag;
        private String queryType;
        private String declRegNo;
        private String statusList;
        private String cusmPutrecNo;
        private String createUser;
        private String dDateEnd;
        private String ciqTradeCountryCode;
        private String ciqTradeCountryCodeName;
        private String ciqMessageId;
        private String stOrder;
        private String iEPort;
        private String despPortCodeName;
        private String dataSrcModeCode;
        private String ciqEntyPortCodeName;
        private String operType;
        private String dDate;
        private String dataSrcUnitCode;
        private String tradeModeCodeName;
        private String trafName;
        private String cusVoyageNo;
        private String declRegName;
        private String cnsnTradeCode;
        private String orgCodeName;
        private String inputEtpsCode;
        private String supvModeCddeName;
        private String agentName;
        private String indbTime;
        private String dclrNo;
        private String consigneeCname;
        private String consigneeEname;
        private String certNo;
        private String inspOrgCode;
        private String declDate;
        private String indbUser;
        private String clBlNo;
        private String typistNo;
        private String etpsCategoryName;
        private String declPersnCertNo;
        private String copNo;
        private String preEntryId;
        private String cnsnTradeScc;
        /**进出口日期*/
        private String iEDate;
        private String ownerName;
        private String ownerCiqCode;
        private String contrNo;
        private String orgCode;
        private String dataSrcCode;
        private String indbTimeEnd;
        private String end;
        private String goodsNum;
        private String rcvgdTradeCode;
        private String ciqBillNo;
        private String noteS;
        private String customMaster;
        private String icCode;
        private String relativeId;
        private String billType;
        private String inputErName;
        private String priUseGoodsType;
        private String shipperConsignee;
        private String seqNoSignData;
        private String cusTradeNationCodeName;
    }

}
