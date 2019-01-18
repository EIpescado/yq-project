package pers.yurwisher.parser.pdf;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yq
 * @date 2018/12/03 09:35
 * @description 汇丰银行清单
 * @since V1.0.0
 */
public class HSBCStatement implements Serializable {
    private static final long serialVersionUID = -5306926381456954265L;

    private String accountName;
    private String accountNumber;
    private String bankName;
    private String currency;
    private String location;
    private String bic;
    private String iban;
    private String accountStatus;
    private String accountType;

    private BigDecimal closingLedgerBalanceBroughtForward;
    private Date closingLedgerBalanceBroughtForwardFrom;

    private BigDecimal closingAvailableBalanceBroughtForward;
    private Date closingAvailableBalanceBroughtForwardFrom;

    private BigDecimal currentLedgerBalance;
    private Date currentLedgerBalanceAsAt;

    private BigDecimal currentAvailableBalance;
    private Date currentAvailableBalanceAsAt;

    /**
     * 总页数
     */
    private Integer pageNumber;

    private List<HSBCStatementDetail> members;

    /**
     * 汇丰银行清单明细
     */
    public static class  HSBCStatementDetail implements Serializable{
        private static final long serialVersionUID = -660796595884262928L;

        private Date balanceBroughtForwardDate;
        private BigDecimal balanceBroughtForward;

        private Date balanceAsAtCloseDate;
        private BigDecimal balanceAsAtClose;

        private List<HSBCStatementDetailMember> detailMembers;

        public Date getBalanceBroughtForwardDate() {
            return balanceBroughtForwardDate;
        }

        public void setBalanceBroughtForwardDate(Date balanceBroughtForwardDate) {
            this.balanceBroughtForwardDate = balanceBroughtForwardDate;
        }

        public BigDecimal getBalanceBroughtForward() {
            return balanceBroughtForward;
        }

        public void setBalanceBroughtForward(BigDecimal balanceBroughtForward) {
            this.balanceBroughtForward = balanceBroughtForward;
        }

        public Date getBalanceAsAtCloseDate() {
            return balanceAsAtCloseDate;
        }

        public void setBalanceAsAtCloseDate(Date balanceAsAtCloseDate) {
            this.balanceAsAtCloseDate = balanceAsAtCloseDate;
        }

        public BigDecimal getBalanceAsAtClose() {
            return balanceAsAtClose;
        }

        public void setBalanceAsAtClose(BigDecimal balanceAsAtClose) {
            this.balanceAsAtClose = balanceAsAtClose;
        }

        public List<HSBCStatementDetailMember> getDetailMembers() {
            return detailMembers;
        }

        public void setDetailMembers(List<HSBCStatementDetailMember> detailMembers) {
            this.detailMembers = detailMembers;
        }
    }

    public static class HSBCStatementDetailMember implements Serializable{

        private static final long serialVersionUID = 5120488364658930136L;

        private String narrative;
        private String customerReference;
        private Date valueDate;
        private BigDecimal creditAmount;
        private BigDecimal debitAmount;

        public String getNarrative() {
            return narrative;
        }

        public void setNarrative(String narrative) {
            this.narrative = narrative;
        }

        public String getCustomerReference() {
            return customerReference;
        }

        public void setCustomerReference(String customerReference) {
            this.customerReference = customerReference;
        }

        public Date getValueDate() {
            return valueDate;
        }

        public void setValueDate(Date valueDate) {
            this.valueDate = valueDate;
        }

        public BigDecimal getCreditAmount() {
            return creditAmount;
        }

        public void setCreditAmount(BigDecimal creditAmount) {
            this.creditAmount = creditAmount;
        }

        public BigDecimal getDebitAmount() {
            return debitAmount;
        }

        public void setDebitAmount(BigDecimal debitAmount) {
            this.debitAmount = debitAmount;
        }
    }

    private static final String DEFAULT_REGEX_SUFFIX = "!(.*)";

    private static final String EMPTY_REGEX_SUFFIX = " (.*)";
    /**
     * 汇丰清单表头
     */
    public enum HSBCHead{
        ACCOUNT_NAME("Account name",DEFAULT_REGEX_SUFFIX),
        ACCOUNT_NUMBER("Account number",DEFAULT_REGEX_SUFFIX),
        BANK_NAME("Bank name",DEFAULT_REGEX_SUFFIX),
        CURRENCY("Currency",DEFAULT_REGEX_SUFFIX),
        LOCATION("Location",DEFAULT_REGEX_SUFFIX),
        BIC("BIC",DEFAULT_REGEX_SUFFIX),
        IBAN("IBAN",DEFAULT_REGEX_SUFFIX),
        ACCOUNT_STATUS("Account status",DEFAULT_REGEX_SUFFIX),
        ACCOUNT_TYPE("Account type",DEFAULT_REGEX_SUFFIX),
        CLOSING_LEDGER_BALANCE_BROUGHT_FORWARD("Closing ledger balance brought forward",DEFAULT_REGEX_SUFFIX),
        FROM("From",EMPTY_REGEX_SUFFIX),
        CLOSING_AVAILABLE_BALANCE_BROUGHT_FORWARD("Closing available balance brought forward",DEFAULT_REGEX_SUFFIX),
        CURRENT_LEDGER_BALANCE("Current ledger balance",DEFAULT_REGEX_SUFFIX),
        AS_AT("As at",EMPTY_REGEX_SUFFIX),
        CURRENT_AVAILABLE_BALANCE("Current available balance",DEFAULT_REGEX_SUFFIX),
        BALANCE_BROUGHT_FORWARD("Balance brought forward",EMPTY_REGEX_SUFFIX),
        BALANCE_AS_AT_CLOSE("Balance as at close",EMPTY_REGEX_SUFFIX);

        private String description;

        private String regex ;

        HSBCHead(String description,String regex){
            this.description = description;
            this.regex = regex;
        }

        public String getDescription() {
            return description;
        }

        public String getRegex() {
            return regex;
        }
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getClosingLedgerBalanceBroughtForward() {
        return closingLedgerBalanceBroughtForward;
    }

    public void setClosingLedgerBalanceBroughtForward(BigDecimal closingLedgerBalanceBroughtForward) {
        this.closingLedgerBalanceBroughtForward = closingLedgerBalanceBroughtForward;
    }

    public Date getClosingLedgerBalanceBroughtForwardFrom() {
        return closingLedgerBalanceBroughtForwardFrom;
    }

    public void setClosingLedgerBalanceBroughtForwardFrom(Date closingLedgerBalanceBroughtForwardFrom) {
        this.closingLedgerBalanceBroughtForwardFrom = closingLedgerBalanceBroughtForwardFrom;
    }

    public BigDecimal getClosingAvailableBalanceBroughtForward() {
        return closingAvailableBalanceBroughtForward;
    }

    public void setClosingAvailableBalanceBroughtForward(BigDecimal closingAvailableBalanceBroughtForward) {
        this.closingAvailableBalanceBroughtForward = closingAvailableBalanceBroughtForward;
    }

    public Date getClosingAvailableBalanceBroughtForwardFrom() {
        return closingAvailableBalanceBroughtForwardFrom;
    }

    public void setClosingAvailableBalanceBroughtForwardFrom(Date closingAvailableBalanceBroughtForwardFrom) {
        this.closingAvailableBalanceBroughtForwardFrom = closingAvailableBalanceBroughtForwardFrom;
    }

    public BigDecimal getCurrentLedgerBalance() {
        return currentLedgerBalance;
    }

    public void setCurrentLedgerBalance(BigDecimal currentLedgerBalance) {
        this.currentLedgerBalance = currentLedgerBalance;
    }

    public Date getCurrentLedgerBalanceAsAt() {
        return currentLedgerBalanceAsAt;
    }

    public void setCurrentLedgerBalanceAsAt(Date currentLedgerBalanceAsAt) {
        this.currentLedgerBalanceAsAt = currentLedgerBalanceAsAt;
    }

    public BigDecimal getCurrentAvailableBalance() {
        return currentAvailableBalance;
    }

    public void setCurrentAvailableBalance(BigDecimal currentAvailableBalance) {
        this.currentAvailableBalance = currentAvailableBalance;
    }

    public Date getCurrentAvailableBalanceAsAt() {
        return currentAvailableBalanceAsAt;
    }

    public void setCurrentAvailableBalanceAsAt(Date currentAvailableBalanceAsAt) {
        this.currentAvailableBalanceAsAt = currentAvailableBalanceAsAt;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<HSBCStatementDetail> getMembers() {
        return members;
    }

    public void setMembers(List<HSBCStatementDetail> members) {
        this.members = members;
    }
}
