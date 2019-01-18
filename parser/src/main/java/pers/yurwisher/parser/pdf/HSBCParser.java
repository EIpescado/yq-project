package pers.yurwisher.parser.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yurwisher.parser.exception.PdfParseException;
import pers.yurwisher.parser.utils.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yq
 * @date 2018/12/03 13:53
 * @description 汇丰清单
 * @since V1.0.0
 */
public class HSBCParser {

    private static final Logger logger = LoggerFactory.getLogger(HSBCParser.class);

    /**
     * 汇丰清单 取值正则
     */
    private static final Map<HSBCStatement.HSBCHead, Pattern> HSBC_HEAD_PATTERN_MAP = new ConcurrentHashMap<>(HSBCStatement.HSBCHead.values().length);

    private static final ThreadLocal<SimpleDateFormat> FROM_DATE_TL = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
    );

    private static final ThreadLocal<SimpleDateFormat> AS_AT_DATE_TL = ThreadLocal.withInitial(() ->
            new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH)
    );

    static {
        for (HSBCStatement.HSBCHead head : HSBCStatement.HSBCHead.values()) {
            HSBC_HEAD_PATTERN_MAP.put(head, Pattern.compile(head.getDescription() + head.getRegex()));
        }
    }

    private static final String TEXT_CHUNK = "!";

    /**
     * 一行正确的明细的数据条数
     */
    private static final int ONE_ROW_DATA_NUMBER = 4;

    /**
     * 读取PDF返回转化后的对象
     *
     * @param fileName pdf文件名
     * @return 汇丰清单
     */
    public static HSBCStatement readHSBCPdfInfo(String fileName) {
        PdfReader reader = null;
        try {
            reader = new PdfReader(fileName);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            //总页数
            int pageNumber = reader.getNumberOfPages();
            logger.info("{},总页数:{}", fileName, pageNumber);
            if (pageNumber > 0) {
                HSBCStatement hsbcStatement = new HSBCStatement();
                hsbcStatement.setPageNumber(pageNumber);
                TextExtractionStrategy strategy;

                StringBuilder sb = new StringBuilder();
                //读取明细
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    strategy = parser.processContent(i, new YurwisherTextExtractionStrategy(TEXT_CHUNK));
                    String text = strategy.getResultantText();
                    if (i == 1) {
                        //读取第一页
                        transferHomePage(text, hsbcStatement);
                    }
                    //合并文本内容,方便读取明细
                    sb.append("\n").append(text);
                }
                //读取明细
                transferDetails(sb.toString(), hsbcStatement);
                return hsbcStatement;
            }
            return null;
        } catch (IOException e) {
            throw new PdfParseException("parse HSBC pdf error",e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * 解析首页
     */
    private static void transferHomePage(String fullText, HSBCStatement statement) {
        //全文本拆分成行
        String[] textArray = fullText.split("\\n");
        statement.setAccountName(getContentFromRow(textArray[0], HSBCStatement.HSBCHead.ACCOUNT_NAME));
        statement.setAccountNumber(getContentFromRow(textArray[1], HSBCStatement.HSBCHead.ACCOUNT_NUMBER));
        statement.setBankName(getContentFromRow(textArray[2], HSBCStatement.HSBCHead.BANK_NAME));
        statement.setCurrency(getContentFromRow(textArray[3], HSBCStatement.HSBCHead.CURRENCY));
        statement.setLocation(getContentFromRow(textArray[4], HSBCStatement.HSBCHead.LOCATION));
        statement.setBic(getContentFromRow(textArray[5], HSBCStatement.HSBCHead.BIC));
        statement.setIban(getContentFromRow(textArray[6], HSBCStatement.HSBCHead.IBAN));
        statement.setAccountStatus(getContentFromRow(textArray[7], HSBCStatement.HSBCHead.ACCOUNT_STATUS));
        statement.setAccountType(getContentFromRow(textArray[8], HSBCStatement.HSBCHead.ACCOUNT_TYPE));
        statement.setClosingLedgerBalanceBroughtForward(getContentFromRowNumber(textArray[9], HSBCStatement.HSBCHead.CLOSING_LEDGER_BALANCE_BROUGHT_FORWARD));
        statement.setClosingLedgerBalanceBroughtForwardFrom(parseFrom(textArray[10]));
        statement.setClosingAvailableBalanceBroughtForward(getContentFromRowNumber(textArray[11], HSBCStatement.HSBCHead.CLOSING_AVAILABLE_BALANCE_BROUGHT_FORWARD));
        statement.setClosingAvailableBalanceBroughtForwardFrom(parseFrom(textArray[12]));
        statement.setCurrentLedgerBalance(getContentFromRowNumber(textArray[13], HSBCStatement.HSBCHead.CURRENT_LEDGER_BALANCE));
        statement.setCurrentLedgerBalanceAsAt(parseAsAt(textArray[14]));
        statement.setCurrentAvailableBalance(getContentFromRowNumber(textArray[15], HSBCStatement.HSBCHead.CURRENT_AVAILABLE_BALANCE));
        statement.setCurrentAvailableBalanceAsAt(parseAsAt(textArray[16]));
    }

    /**
     * 解析明细
     */
    private static void transferDetails(String detailText, HSBCStatement statement) {
        //全文本拆分成行
        String[] textArray = detailText.split("\\n");
        List<HSBCStatement.HSBCStatementDetail> members = new ArrayList<>(30);
        HSBCStatement.HSBCStatementDetail member = null;
        boolean isOneOver = true;
        StringBuilder before = new StringBuilder() ;
        for (String row : textArray) {
            if(isOneOver){
                //Balance brought forward 起始标识新明细的起始
                String oneBegin = getContentFromRow(row, HSBCStatement.HSBCHead.BALANCE_BROUGHT_FORWARD);
                if (Utils.isNotEmpty(oneBegin)) {
                    member = new HSBCStatement.HSBCStatementDetail();
                    //解析出日期,金额
                    transferDetailBegin(oneBegin,member);
                    member.setDetailMembers(new ArrayList<>());
                    members.add(member);
                    isOneOver = false;
                }
            }else {
                String oneEnd = getContentFromRow(row, HSBCStatement.HSBCHead.BALANCE_AS_AT_CLOSE);
                //Balance as at close 标识明细的结束
                if (Utils.isNotEmpty(oneEnd)) {
                    //解析出日期,金额
                    transferDetailEnd(oneEnd,member);
                    isOneOver = true ;
                    appendAndCreateDetailMember(before,member);
                }else {
                    isOneOver = false;
                    //过滤|!Statement details
                    //29 Nov 2018 | Account number 642-003941-201!Page 1 of 7 这2行
                    if(row.contains("Statement details") || row.contains("Page") || row.contains("Narrative")){
                        continue;
                    }
                    String[] array = row.split(TEXT_CHUNK);
                    //表示此字段为换行后多出部分
                    if(array.length != ONE_ROW_DATA_NUMBER){
                        //narrative 可能存在换行,合并内容,并拼接上分隔符
                        if(array.length == 3){
                            before.append(TEXT_CHUNK);
                            before.append(row);
                            appendAndCreateDetailMember(before,member);
                        }else {
                            before.append(row);
                        }
                    }else {
                        appendAndCreateDetailMember(before,member);
                        //明细主体内容
                        member.getDetailMembers().add(transferDetailBody(array));
                    }
                }
            }
        }
        statement.setMembers(members);
    }


    /**
     * 解析明细的起始数据
     * @param text 文本,只包含后一部分
     * @param member
     */
    private static void transferDetailBegin(String text,HSBCStatement.HSBCStatementDetail member){
        int flag = text.lastIndexOf(" ");
        String  forwardDateStr = Utils.null2EmptyWithTrimNew(text.substring(0,flag));
        String  forwardStr= Utils.null2EmptyWithTrimNew(text.substring(flag));
        member.setBalanceBroughtForward(parseNumber(forwardStr));
        member.setBalanceBroughtForwardDate(parseFromDate(forwardDateStr));
    }

    /**
     * 解析明细的中间数据
     * @param array 文本,只包含后一部分
     */
    private static HSBCStatement.HSBCStatementDetailMember transferDetailBody(String [] array){
        HSBCStatement.HSBCStatementDetailMember detailMember = new HSBCStatement.HSBCStatementDetailMember();
        //解析出一条明细 Narrative,Customer reference,Value date,Credit amount,Debit amount
        detailMember.setNarrative(array[0]);
        detailMember.setCustomerReference(array[1]);
        detailMember.setValueDate(parseFromDate(array[2]));
        if(array.length == ONE_ROW_DATA_NUMBER){
            BigDecimal amount = parseNumber(array[3]);
            if(amount != null && amount.compareTo(BigDecimal.ZERO) >= 0){
                detailMember.setCreditAmount(amount);
            }else {
                detailMember.setDebitAmount(amount);
            }
        }
        return detailMember;
    }

    /**
     * 解析明细的结束数据
     * @param text 文本,只包含后一部分
     * @param member
     */
    private static void transferDetailEnd(String text,HSBCStatement.HSBCStatementDetail member){
        int flag = text.lastIndexOf(" ");
        String  forwardDateStr = Utils.null2EmptyWithTrimNew(text.substring(0,flag));
        String  forwardStr = Utils.null2EmptyWithTrimNew(text.substring(flag));
        member.setBalanceAsAtClose(parseNumber(forwardStr));
        member.setBalanceAsAtCloseDate(parseFromDate(forwardDateStr));
    }

    /**
     * 获取标题后文本
     *
     * @param row      一行
     * @param hsbcHead 标题名称
     * @return 标题内容
     */
    private static String getContentFromRow(String row, HSBCStatement.HSBCHead hsbcHead) {
        Matcher matcher = HSBC_HEAD_PATTERN_MAP.get(hsbcHead).matcher(row);
        if (matcher.find()) {
            return Utils.null2EmptyWithTrimNew(matcher.group(1));
        }
        return null;
    }

    /**
     * 获取标题后文本,转BigDecimal
     *
     * @param row      一行
     * @param hsbcHead 标题名称
     * @return 标题内容
     */
    private static BigDecimal getContentFromRowNumber(String row, HSBCStatement.HSBCHead hsbcHead) {
        String x = getContentFromRow(row, hsbcHead);
        return parseNumber(x);
    }

    /**
     * 获取标题后文本,转Date
     *
     * @param row 一行
     * @return 标题内容
     */
    private static Date parseFrom(String row) {
        String x = getContentFromRow(row, HSBCStatement.HSBCHead.FROM);
        if (Utils.isNotEmpty(x)) {
           return parseFromDate(Utils.null2EmptyWithTrimNew(x));
        }
        return null;
    }

    private static Date parseFromDate(String date){
        try {
            return FROM_DATE_TL.get().parse(date);
        } catch (ParseException e) {
            logger.error("{}日期转化错误", date, e);
            return null;
        }
    }

    /**
     * 获取标题后文本,转Date
     *
     * @param row 一行
     * @return 标题内容
     */
    private static Date parseAsAt(String row) {
        String x = getContentFromRow(row, HSBCStatement.HSBCHead.AS_AT);
        if (Utils.isNotEmpty(x)) {
            try {
                return AS_AT_DATE_TL.get().parse(Utils.null2EmptyWithTrimNew(x));
            } catch (ParseException e) {
                logger.error("{}日期转化错误", x, e);
                return null;
            }
        }
        return null;
    }

    /**
     * 转number
     * @param x 格式化字符串
     * @return BigDecimal
     */
    private static BigDecimal parseNumber(String x){
        if (Utils.isNotEmpty(x)) {
            x = x.replaceAll(",", "");
            return new BigDecimal(x);
        }
        return null;
    }

    private static void appendAndCreateDetailMember(StringBuilder before,HSBCStatement.HSBCStatementDetail member){
        if(before.length() > 0){
            String[] arrayBefore =  before.toString().split(TEXT_CHUNK);
            //换行的已经拼接完全 解析并存入members
            if(arrayBefore.length == ONE_ROW_DATA_NUMBER){
                //明细主体内容
                member.getDetailMembers().add(transferDetailBody(arrayBefore));
                //清空
                before.delete(0,before.length());
            }
        }
    }


    public static void main(String[] args) throws IOException, ParseException {
        HSBCStatement statement = readHSBCPdfInfo("d:/qr/HSBC USD 201.pdf");
        //校验个数
        List<HSBCStatement.HSBCStatementDetail> list = statement.getMembers();
        System.out.println("明细数量 " + list.size());
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,4);map.put(2,4);map.put(3,4);map.put(4,7);
        map.put(5,6);map.put(6,7);map.put(7,9);map.put(8,3);
        map.put(9,5);map.put(10,5);map.put(11,3);map.put(12,3);
        map.put(13,5);map.put(14,6);map.put(15,9);map.put(16,1);
        map.put(17,12);map.put(18,8);map.put(19,11);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getDetailMembers().size() != map.get(i + 1)){
                System.out.println("第" + i + "条明细数量不对");
            }
        }
    }
}
