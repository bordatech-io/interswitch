/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.gateway;

//import com.byteworks.connection.HSM;
//import com.byteworks.connection.PostBridgeTcpCon;
import com.bordatech.support.HSM;
import com.bordatech.support.ISO8583Exception;
import com.bordatech.support.ResponseCode;
import com.bordatech.support.TripleDES;
import java.text.SimpleDateFormat;
import java.util.*;
//import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 */
public class GetFields implements ResponseCode {

    private String map_key;
    Calendar time;
    Map<String, String> hashmap;
    Date timer;
    private static long tracer = 0;
    /**
     * Log.
     */
    private static final Logger log = LoggerFactory.getLogger(GetFields.class);

    public GetFields(Map<String, String> hashmap) {
        this.hashmap = hashmap;
        //String timeset=(String) hashmap.get("RevDateTime");

        time = new GregorianCalendar();

        String tim = hashmap.get("DateTime");
        if (tim != null) {
            long longtime = Long.parseLong(tim) * 1000;

            timer = new Date(longtime);

            time.setTime(timer);

            // time.setTimeInMillis(longtime);
            //time.setTimeZone(TimeZone.getTimeZone(tim));
        }
    }

    public GetFields(String key_map) {
        this.map_key = key_map;
        time = new GregorianCalendar();
        hashmap = new HashMap<String, String>();
        timer = new Date();
        time.setTime(timer);
    }

    public String getPan() {
        //not yet implemnted
        //return string Db.getpan(this.reference);
        String pan = "";
        String track2 = hashmap.get("track2");
        /*if (hashmap.get("AquireTrack2") != null && !hashmap.get("AquireTrack2").isEmpty() && !hashmap.get("TranType").trim().equalsIgnoreCase("7")) {
            track2 = hashmap.get("AquireTrack2");
        }

        //if(tranType==TRAN_TYPE_AGENTREVERSAL)
        if (hashmap.get("TranType").trim().equalsIgnoreCase("36")) {
            track2 = hashmap.get("AquireTrack2");
        }

        if (hashmap.get("TranType").trim().equalsIgnoreCase("37")) {
            track2 = hashmap.get("AquireTrack2");
        }

        if (hashmap.get("TranType").trim().equalsIgnoreCase("7")) {
            track2 = hashmap.get("track2");
        }

        if (hashmap.get("TranType").trim().equalsIgnoreCase("30")) {
            track2 = hashmap.get("track2");
        }*/

        if (track2 != null && !track2.isEmpty()) {
            StringTokenizer st = null;
            if (track2.indexOf('=') > 0) {
                st = new StringTokenizer(track2, "=");
            } else {
                st = new StringTokenizer(track2, "D");
            }

            pan = st.nextToken();

            int len = pan.length();
            pan = String.format("%1$02d", len) + pan;
        }
        return pan;
    }

    public String getPINData() {
        //String value=(String) hashmap.get("pinblock");
        String pan = "";
        String PINData = null;

        /*String value = hashmap.get("TranType");
        int trantype = 0;
        if (value != null) {
            trantype = Integer.parseInt(value);
        }*/

        String pinblock = hashmap.get("pinblock");
        /*String track2 = hashmap.get("track2");
        
        if (track2 != null && !track2.isEmpty()) {
            StringTokenizer st = null;
            if (track2.indexOf('=') > 0) {
                st = new StringTokenizer(track2, "=");
            } else {
                st = new StringTokenizer(track2, "D");
            }

            pan = st.nextToken();
        }
        
        Map<String, String> mappin = new HashMap<String, String>();
        mappin.put("SourcePIN", pinblock);
        mappin.put("SourceData", pan);
        
        //transate pinblock
        Map<String, String> map = new HashMap<String, String>(); 
        HSM hsm = new HSM();
        map = hsm.use3DES(mappin);
        if(map.get("errorCode") != null && map.get("errorCode").equals("00")){
            pinblock = map.get("pin");
        }*/

        /*if (TRAN_TYPE_AGENT_LINKDEPOSIT == trantype || TRAN_TYPE_TAMSACCT_TRANSFER == trantype) {
            //pinblock = hashmap.get("AquirePINBlock");
        }
        if (hashmap.get("firstTran") != null && hashmap.get("firstTran").trim().equalsIgnoreCase("38")) {
            //pinblock = hashmap.get("pinblock");
        }*/
        /*if (TRAN_TYPE_PINCHANGE == trantype) {
            int index = pinblock.indexOf('|');
            if (index != -1) {
                String[] pinblocks = pinblock.split("\\|");
                pinblock = pinblocks[0];
                
                mappin.put("SourcePIN", pinblock);

                //transate pinblock
                Map<String, String> map2 = new HashMap<String, String>();
                HSM hsm2 = new HSM();
                map2 = hsm2.use3DES(mappin);
                if (map2.get("errorCode") != null && map2.get("errorCode").equals("00")) {
                    pinblock = map2.get("pin");
                }
            }
        }*/

        byte[] pins = TripleDES.hexToByte(pinblock);
        if (pinblock.length() == 16) {
            PINData = "";
            for (int i = 0; i < pins.length; i++) {
                PINData += (char) pins[i];
            }

        }
        return PINData;
    }

    public String getSettlementCode() {
        //return Settlement;
        String field = "1";
        return field;
    }

    public String getAccountIdentification1() {
        //return acct_Id_No1;
        String field = "";
        String value = null;         // hashmap.get("AquireAcct")

        value = hashmap.get("FromAcctNo");
        
        /*if (hashmap.get("firstTran") != null && hashmap.get("firstTran").trim().equalsIgnoreCase("38")) {
            value = hashmap.get("FromAcctNo");
        }

        if (hashmap.get("firstTran") != null && hashmap.get("firstTran").trim().equalsIgnoreCase("1")) {
            value = hashmap.get("FromAcctNo");
        }*/
        
        /*if(hashmap.get("TranType").trim().equalsIgnoreCase("37") ){
           value = hashmap.get("FromAcctNo");
        }*/
        if (value != null) {
            field = value.trim();
        }

        int len = field.length();
        
        if (len == 0 || hashmap.get("TranType").trim().equalsIgnoreCase("7")) {
            return null;
        }
        if (hashmap.get("TranType").trim().equalsIgnoreCase("4") /*&& !hashmap.get("RevTxnType").trim().equalsIgnoreCase("11")*/) {
            return null;
        }

        String fil = String.format("%1$02d", len);

        return fil.concat(field);
    }

    public String getAccountIdentification2() {
        //return acct_Id_No2;
        String field = "";
        String value = hashmap.get("ToAcctNo");
        
        /*if (hashmap.get("firstTran") != null && hashmap.get("firstTran").trim().equalsIgnoreCase("38")) {
            value = hashmap.get("AquireAcct");
        }
        if (hashmap.get("firstTran") != null && hashmap.get("firstTran").trim().equalsIgnoreCase("1")) {
            value = hashmap.get("AquireAcct");
        }*/
        
        /*if(hashmap.get("TranType").trim().equalsIgnoreCase("37") ){
            value = hashmap.get("AquireAcct");
        }*/
        
        if (value != null) {
            field = value.trim();
        }

        int len = field.length();
        if (len == 0 || hashmap.get("TranType").trim().equalsIgnoreCase("7")) {
            return null;
        }
        if (hashmap.get("TranType").trim().equalsIgnoreCase("4") /*&& !hashmap.get("RevTxnType").trim().equalsIgnoreCase("11")*/) {
            return null;
        }
        
        String fil = String.format("%1$02d", len);
        return fil.concat(field);
    }

    public String getAcquirerInstitutionIdCode() {

        //return acq_Inst_Id_Code;
        //PostBridgeTcpCon con = new PostBridgeTcpCon();
        //Map<String, String> map = con.get();
        //String value = (String) hashmap.get("AquireBank");
        String field = "000000";
        /*if (value != null) {
            field = value;
        }

        if (field.regionMatches(0, "000000", 0, 5)) {
            field = this.getPan().substring(2, 8);
        }*/

        int length = field.length();
        String fil = String.format("%1$02d", length);
        fil = fil.concat(field);
        return fil;

    }

    public String getAdditionAmount() {
        //return additional_Amt;
        String field = "11111111111111111111";
        int len = field.length();

        String fil = String.format("%1$03d", len);
        return fil.concat(field);
    }

    public String getAdditonalData() {
        //return additonal_Data;
        String field = "11111111111111111111";
        field = hashmap.get("RefData");
        System.out.println("Filed 38 data " + field);
        int len = field.length();
        String fil = String.format("%1$03d", len);

        return fil.concat(field);
    }

    public String getAddtionalAmount() {
        //return addtional_Amount;
        String field = "11111111111111111111";
        int len = field.length();
        String fil = String.format("%1$03d", len);
        return fil.concat(field);
    }

    public String getAdditionalResponseData() {
        String field = "11111111111111111111";
        int len = field.length();
        String fil = String.format("%1$02d", len);
        return fil.concat(field);

    }

    public String getAmountSettlement() {
        // get amt transaction form mesg
        String field = "11914";
        String zeroes = "";
        int lenght = 12 - field.length();
        if (lenght < 0) {
            throw new ISO8583Exception();
        } else {
            for (int i = 0; i < lenght; i++) {
                zeroes += "0";
            }
        }
        field = zeroes.concat(field);

        return field;

    }

    public String getAmountTransaction() {
        // get amt transaction form mesg
        long fieldvalue = 0;
        String field = "000000000000";
        String amount = (String) hashmap.get("amount");
        String tip = "0";//(String) hashmap.get("tip");
        String cashback = (String) hashmap.get("cashback");
        if ((amount != null)) {
            fieldvalue += Long.parseLong(amount);
        }
        if (tip != null) {
            fieldvalue += Long.parseLong(tip);
        }
        if (cashback != null) {
            fieldvalue += Long.parseLong(cashback);
        }
        if (fieldvalue != 0) {
            field = "" + fieldvalue;
        }

        if (hashmap.get("secondtime") != null) {
            field = "000000000000";
        }

        String zeroes = "";
        int lenght = 12 - field.length();
        if (lenght < 0) {
            throw new ISO8583Exception();
        } else {
            //field=String.format("%012s",field);
            for (int i = 0; i < lenght; i++) {
                zeroes += "0";
            }
        }
        field = zeroes.concat(field);

        return field;
    }

    public String getAmountNetSettlement() {
        //return amt_Net_Settlmt;
        String fil = "D";
        String field = "000000000000000";
        if (true) {
            fil = "C";
        }
        field = fil + field;

        return field;
    }

    public String getAmountSettlementFee() {
        //return amt_Settlmt_Fee;
        String fil = "D";
        String field = "00000000";
        /*if (true) {
            fil = "C";
        }*/
        field = fil + field;

        return field;
    }

    public String getAmountSettlementProcessingFee() {
        //return amt_Settlmt_Proc_Fee;
        String fil = "D";
        String field = "11111111";
        if (true) {
            fil = "C";
        }
        field = fil + field;

        return field;
    }

    public String getAmountTransactionProcessingFee() {
        //return amt_Tranc_Proc_Fee;
        String value = "";
        String field = "";
        value = (String) hashmap.get("ProcessorFee");
        if (value != null) {
            if ((value.charAt(0) == 'D') || (value.charAt(0) == 'C')) {
                field = value;
            }
        }
        return field;
    }

    public String getAmountTransactionFee() {
        //return amt_Transact_Fee;

        String value = "";
        String field = "";
        value = "D00000000";//(String) hashmap.get("TransactionFee");
        if (value != null) {
            if ((value.charAt(0) == 'D') || (value.charAt(0) == 'C')) {
                field = value;
            }
        }
        return field;
    }

    public String getAuthorizationAgentInstitution() {
        //return author_Id_Response_Len;
        String field = "11111111111";
        int len = field.length();
        String fil = Integer.toString(len);
        if (fil.length() == 1) {
            fil = "0" + fil;
        }
        return fil + field;
    }

    public String getAuthorizationIdentifierResponseLength() {
        //return author_Id_Response_Len;
        String field = "1";
        return field;
    }

    public String getAuthorizationIdentifierResponse() {
        //return author_Id_response;
        String field = "111111";
        return field;
    }

    public String getAuthorizationLifeCycle() {
        //return authorization_No;
        String field = "111";


//        String field = "POWERED BY BYTEWORKS.";
        int len = field.length();

        String fil = String.format("%1$03d", len);

        return fil.concat(field);
    }

    public String getAuthorizationNumber() {
        //return authorization_No;
        String field = "1111111111";
        return field;
    }

    public String getCardAcceptorIdCode() {
        //return card_Acceptor_Id_Code;
        String value = (String) hashmap.get("AquireAcct");//aquireacct
        String field = "000000000000000";
        // field="076000400631341";
        if (value != null) {
            field = value;
        }
        return field;
    }

    public String getCardAcceptorIdLocation() {
        //return card_Acceptor_Id_location;
        String value = "";
        String locinfo = (String) hashmap.get("LocInfo");
        if (locinfo != null) {
            value = String.format("%-23s", locinfo);
        } else {
            value = String.format("%-23s", "");
        }
        String loccity = (String) hashmap.get("LocCity");
        if (loccity != null) {
            value += String.format("%-13s", loccity);
        } else {
            value += String.format("%-13s", "");
        }
        String locstate = (String) hashmap.get("LocState");
        if (locstate != null && !locstate.isEmpty()) {
            value += String.format("%-2s", locstate);
        } else {
            value += String.format("%-2s", "");
        }
        String loccountry = (String) hashmap.get("LocCountry");
        if (loccountry != null) {

            value += String.format("%-2s", loccountry);
        } else {
            value += String.format("%-2s", "NG");
            //String field="1111111111111111111111111111111111111111";
            //field=value;
        }
        return value;

    }

    public String getCardAcceptorTerminalId() {
        //return card_Acceptor_Term_Id;
        String termid = (String) hashmap.get("termid");
        String field = "11111111";
        if (termid != null) {
            if (termid.length() >= 8) {
                field = termid.substring(0, 8);
            }
        }
        return field;
    }

    public String getCardSequenceNumber() {
        //return card_Sequence_No;
        String field = "001";
        String cardseqno = (String) hashmap.get("CardSeqNo");
        if(cardseqno != null){
            field = cardseqno;
        }
        return field;
    }

    public String getConversionRate() {
        //return conversion_Rate;
        String field = "4560001.2330004";

        int len = field.length();

        if ((field.charAt(0) == '0') && (field.charAt(1) == '.')) {

            String fil = "";
            if (field.length() > 11) {
                fil = field.substring(2, 11);
                len = 9;
            } else {
                fil = field.substring(2, len);
                len -= 2;
            }

            int j = 0;
            while ((fil.length() > 7) && (fil.charAt(0) == '0')) {

                fil = fil.substring(1, fil.length());
            }
            while (fil.length() < 7) {
                fil += "0";
                len++;
            }

            while (fil.length() > 7) {
                fil = fil.substring(0, fil.length() - 1);
                len--;
            }

            field = fil;
        } else {
            String fil = "";
            int point = field.indexOf(".");

            if ((point == -1) || (point > 7)) {
                throw new ISO8583Exception();
            }
            for (int i = 0; i < field.length(); i++) {
                if (field.charAt(i) == '.') {
                    continue;
                }
                if (i > 7) {
                    break;
                }
                fil += field.charAt(i);
            }
            len = 7 - point;
            while (field.length() < 7) {
                field += "0";
                len++;
            }
            field = fil;
        }
        field = Integer.toString(len) + field;

        return field;
    }

    public String getCreditAmount() {
        //return credit_Amt;
        String field = "1111111111111111";

        return field;
    }

    public String getCreditNumber() {
        //return credit_No;
        String field = "1111111111";

        return field;
    }

    public String getCreditProcessingFeeAmount() {
        //return credit_Proc_Fee_Amt;
        String field = "111111111111";

        return field;

    }

    public String getCreditReversalAmount() {
        //return credit_Rev_Amt;
        String field = "1111111111111111";

        return field;
    }

    public String getCreditReversalNumber() {
        //return credit_Rev_No;
        String field = "1111111111";

        return field;
    }

    public String getCreditTransactionFeeAmount() {
        //return credit_Transc_Fee_Amt;
        String field = "111111111111";

        return field;
    }

    public String getCurrencyCodeSettlement() {
        //return currency_Code_Settl;
        String field = "566";

        return field;
    }

    public String getCurrencyCodeTransaction() {
        //return currency_Code_Trans;
        String value = (String) hashmap.get("Countrycode");
        String field = "566";
        if (value != null && !value.isEmpty()) {
            field = value;
        }
        return field;
    }

    public String getDateAction() {
        //return date_Action;;

        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DATE);

        int year = time.get(Calendar.YEAR);
        String syear = Integer.toString(year);
        String field = String.format("%1$2s%2$02d%3$02d", syear.substring(2), month, day);

        return field;
    }

    public String getDateConversion() {
        //return date_Conversion;
        String field = "1208";

        return field;
    }

    public String getDateExpiration() {
        //return date_Expiration;
        // this may be part of the data that bank would send 4 authrisation advice
        String field = "1212";
        field = hashmap.get("track2");

        if (field != null && field.contains("D")) {
            String[] part = field.split("D", -1);
            field = part[1].substring(0, 4);
        } else if (field != null && field.contains("=")) {
            String[] part = field.split("=", -1);
            field = part[1].substring(0, 4);
        }

        return field;
    }

    public String getDateLocalTransaction() {
        //return date_Loc_Transc;
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);

        String field = String.format("%1$02d%2$02d", ++month, day);

        return field;
    }

    public String getDateSettlement() {
        //return date_Settlement;
        String field = "1208";
        Date date = new Date();  
        SimpleDateFormat dateformatter = new SimpleDateFormat("MMdd");  
        field = dateformatter.format(date);

        return field;
    }

    public String getDebitAmount() {
        //return debit_Amt;
        String field = "1111111111111111";

        return field;
    }

    public String getDebitNumber() {
        //return debit_No;
        String field = "1111111111";
        return field;
    }

    public String getDebitProcessingFeeAmount() {
        //return debit_Proc_Fee_Amt;
        String field = "111111111111";

        return field;
    }

    public String getDebitReversalAmount() {
        //return debit_Rev_Amt;
        String field = "1111111111111111";

        return field;
    }

    public String getDebitReversalNumber() {
        //return debit_Rev_No;
        String field = "1111111111";

        return field;
    }

    public String getDebitTransactionFeeAmount() {
        //return debit_Transc_Fee_Amt;
        String field = "111111111111";

        return field;
    }

    public String getEchoData() {
        String field = "POWERED BY BYTEWORKS.";
        int len = field.length();

        String fil = String.format("%1$03d", len);

        return fil.concat(field);

    }
    
    public String getExtTranAttrData() {
        String field = (String) hashmap.get("phonenumber");
        field = "00698WD0101333"+field;
        int len = field.length();

        String fil = String.format("%1$03d", len);

        return fil.concat(field);

    }

    public String getExtendedPaymentCode() {
        //return extended_Paym_Code;
        String value = (String) hashmap.get("budget");
        String field = "11";
        if (value != null) {
            field = value;
        }
        return field;
    }

    public String getFileName() {
        //return fileName;
        String field = "1111111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);

        return fil.concat(field);
    }

    public String getFileUpdateCode() {
        //return file_Update_Code;
        String field = "1";

        return field;
    }

    public String getForwardingInstitutionIdCode() {
        //return forward_Inst_Id_Code;
        String field = "200013";
        int len = field.length();

        String fil = String.format("%1$02d", len);

        return fil.concat(field);
    }

    public String getInquiriesNumber() {
        //return inquiries_No;
        String field = "1111111111";

        return field;
    }

    public String getMacExtended() {
        //return mac_Extended;
        return "";
    }

    public String getMerchantType() {
        //return merchant_Type;
        //retrieve merhant type from database using source of data
        String field = "1111";
        String value = hashmap.get("MCCCode");
        if(value != null){
            field = value;
        }
        return field;
    }

    public String getMessageReasonCode() {
        //return message_Reason_Code;
        String field = "1510";
        /*if (hashmap.get("TranType").trim().equals("36")) {
            field = "4021";
        }
        String value = hashmap.get("MessageReasonCode");
        if (value != null) {
            field = value;
        }*/
        field = String.format("%03d", field.length()) + field;
        return field;
    }

    public String getNetworkInformationManagementCode() {
        //return network_Info_Manag_Code;
        String field = "111";

        return field;
    }

    public String getOriginalDataElement() {
        //return original_Data_Elt;
        String revSeq = "";
        int iRevCmd = 200;

        //PostBridgeTcpCon con = new PostBridgeTcpCon();
        String acquireBank = this.getAcquirerInstitutionIdCode();

        //if(acquireBank==null)
        //  acquireBank=value;
        String revTnxType = hashmap.get("RevTxnType");
        String revDateTime = hashmap.get("RevDateTime");
        revSeq = (String) hashmap.get("RevRefNo");
        Date revDateTimes = new Date(Long.parseLong(revDateTime) * 1000);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(revDateTimes);
        String revTime = String.format("%02d%02d%02d%02d%02d", calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        switch (Integer.parseInt(revTnxType)) {
            case TRAN_TYPE_VALUEADD_RECHARGE:
            case TRAN_TYPE_VALUEADD_VOUCHER:
            case TRAN_TYPE_PURCHASE: // purchase

                iRevCmd = 200;
                break;
            /*case TRAN_TYPE_PURCASH: // purchase & cashback

                iRevCmd = 200;
                break;*/

            case TRAN_TYPE_CASHBACK: // cashback

                iRevCmd = 200;
                break;

            case TRAN_TYPE_REVERSAL: // reversal

                iRevCmd = 420;

                break;

            case TRAN_TYPE_REFUND: // refund

                iRevCmd = 200;
                break;

            case TRAN_TYPE_AUTH: // auth only

                iRevCmd = 100;
                break;
            case TRAN_TYPE_AGENTDEPOSIT:
            case TRAN_TYPE_AGENT_LINKDEPOSIT:
            case TRAN_TYPE_DEPOSIT:
                iRevCmd = 200;
                break;

            case TRAN_TYPE_TRANSFER:
                iRevCmd = 200;
                break;

        }//switch

        String field = "111111111111111111111111111111111111111111";
        //field = String.format("%1$04d%2$06d%3$10s%4$011d%5$011d", iRevCmd, Integer.parseInt(revSeq), revTime, 627629, 0);
        field = String.format("%1$04d%2$06d%3$10s%4$011d%5$011d", iRevCmd, Long.parseLong(revSeq), revTime, Long.parseLong(acquireBank), 0);
        return field;
    }

    public String getPayee() {
        String termid = (String) hashmap.get("termid");
        String field = "1111111111111111111111111";
        if (termid != null) {
             field = termid + "                 ";//"3GPAY LIMITED            ";
        }
        //return payee;
        //String field = "1111111111111111111111111";
        //String field = "MONIEKONNECT             ";
        //String field = "Trusted Choice Limited   ";
       

        return field;
    }

    public String getPaymentNumber() {
        //return payment_Number;
        String field = "1111111111";

        return field;
    }

    public String getPaymentReversalNumber() {
        //return payment_Revalsal_Number;
        String field = "1111111111";

        return field;
    }

    public String getPosConditionCode() {
        //return pos_Cond_Code;
        String field = "00";

        return field;
    }

    public String getPosDataCode() {
        //return pos_Data_Code;
        String value = "";
        //String v3dSecure = "";
        boolean bPinBased = true, bICC = true;
        boolean b3DSecure = false;
        /*v3dSecure = (String) hashmap.get("3DSecure");
        if (v3dSecure != null) {
            if (v3dSecure.equalsIgnoreCase("Y")) {
                b3DSecure = true;
            }
        }*/
        //String pinblock = hashmap.get("pinblock");
        //bPinBased = (pinblock != null && !pinblock.isEmpty());

        // Card Data Input Capability
        //		0 Unknown
        //		1 Manual, no terminal
        //		2 Magnetic stripe read
        //		3 Bar code
        //		4 OCR
        //		5 Integrated circuit card (ICC)
        //		6 Key entered
        if (b3DSecure) {
            value += "0";
        }//if
        else if (bICC) {
            value += "5";

        }//else
        else {
            value += "2";
        }//else

        //Cardholder Authentication Capability
        /*
        0 No electronic authentication
        1 PIN
        2 Electronic signature analysis
        3 Biometrics
        4 Biographic
        5 Electronic authentication inoperative
        6 Other
         */
        if (bPinBased) {
            value += "1";
        }//if
        else if (b3DSecure) {
            value += "0";
        }//else if
        else {
            value += "2";
        }//else

        // Card Capture Capability
        //		0 None
        //		1 Capture
        value += "0";

        //Operating Environment
        //	0 No terminal used
        //	1 On premises of card acceptor, attended
        //	2 On premises of card acceptor, unattended
        //	3 Off premises of card acceptor, attended
        //	4 Off premises of card acceptor, unattended
        //	5 On premises of cardholder, unattended
        if (b3DSecure) {
            value += "0";
        }//if
        else {
            value += "1";
        }//else

        //Cardholder Presence
        //
        //	0 Cardholder present
        //	1 Cardholder not present, unspecified
        //	2 Cardholder not present, mail order
        //	3 Cardholder not present, telephone
        //	4 Cardholder not present, standing authorization
        if (b3DSecure) {
            value += "1";
        }//if
        else {
            value += "0";
        }//else

        //Card Presence
        //
        //	0 Card not present
        //	1 Card present
        if (b3DSecure) {
            value += "0";
        }//if
        else {
            value += "1";
        }//else

        //Card Data Input Mode
        //	0 Unknown
        //	1 Manual, no terminal
        //	2 Magnetic stripe read
        //	3 Bar code
        //	4 OCR
        //	5 Integrated circuit card (ICC)
        //	6 Key entered
        if (b3DSecure) {
            value += "1";
        }//if
        else if (bICC) {
            value += "5";
        }//else
        else {
            value += "2";
        }//else

        //Cardholder Authentication Method
        //	0 No electronic authentication
        //	1 PIN
        //	2 Electronic signature analysis
        //	3 Biometrics
        //	4 Biographic
        //	5 Manual
        //	6 Other
        if (b3DSecure) {
            value += "6";
        }//if
        else if (bICC || bPinBased) {
            value += "1";
        }//else
        else {
            value += "0";
        }//else

        //Cardholder Authentication Entity
        //
        //	0 Not authenticated
        //	1 Integrated circuit card (ICC)
        //	2 Terminal
        //	3 Authorizing agent
        //	4 Merchant
        //	5 Other
        if (b3DSecure) {
            value += "5";
        }//if
        else if (bICC) {
            value += "3";//1
        }//else
        else {
            value += "2";
        }//else

        //Card Data Output Capability
        //
        //	0 Unknown
        //	1 None
        //	2 Magnetic stripe write
        //	3 Integrated circuit card (ICC)
        if (bICC) {
            value += "3";
        }//else
        else {
            value += "1";
        }//else

        //Terminal Output Capability
        //
        //	0 Unknown
        //	1 None
        //	2 Printing
        //	3 Display
        //	4 Printing and display
        if (b3DSecure) {
            value += "1";
        }//if
        else {
            value += "4";
        }//else

        //PIN Capture Capability
        //
        //	0 No PIN capture capability
        //	1 Device PIN capture capability unknown
        //	4 Four characters
        //	5 Five characters
        //	6 Six characters
        //	7 Seven characters
        //	8 Eight characters
        //	9 Nine characters
        //	A Ten characters
        //	B Eleven characters
        //	C Twelve characters
        if (b3DSecure) {
            value += "0";
        }//if
        else {
            value += "4";
        }//else

        //Terminal Operator
        //
        //	0 Customer operated
        //	1 Card acceptor operated
        //	2 Administrative
        if (b3DSecure) {
            value += "0";
        }//if
        else {
            value += "1";
        }//else

        // Terminal Type
        //
        //	00 Administrative terminal
        // 	01 POS terminal
        // 	02 ATM
        // 	03 Home terminal
        //	04 Electronic Cash Register (ECR)
        // 	05 Dial terminal
        // 	06 Travellers check machine
        // 	07 Fuel machine
        // 	08 Scrip machine
        // 	09 Coupon machine
        // 	10 Ticket machine
        // 	11 Point-of-Banking terminal
        // 	12 Teller
        // 	13 Franchise teller
        // 	14 Personal banking
        // 	15 Public utility
        // 	16 Vending
        // 	17 Self-service
        // 	18 Authorization
        // 	19 Payment
        // 	20 VRU
        // 	21 Smart phone
        // 	22 Interactive television
        // 	23 Personal digital assistant
        // 	24 Screen phone
        // 	90 E-commerce - No encryption; no authentication
        // 	91 E-commerce - SET encryption; cardholder certificate not used (non-authenticated)
        // 	92 E-commerce - SET encryption; cardholder certificate used (authenticated)
        // 	93 E-commerce - SET encryption, chip cryptogram used; cardholder certificate not used
        // 	94 E-commerce - SET encryption, chip cryptogram used; cardholder certificate used
        // 	95 E-commerce - Channel encryption (SSL); cardholder certificate not used (non-authenticated)
        // 	96 E-commerce - Channel encryption (SSL); cardholder certificate not used (non-authenticated)
        if (b3DSecure) {
            value += "92";
        }//if
        else {
            value += "01";
        }//else

        String field = "111311111111111";
        field = value;
        field = String.format("%03d", field.length()) + field;
        return field;
    }

    public String getPosEntryMode() {
        //return pos_Entry_Mode;
        String field = "901";

        String value = (String) hashmap.get("ICCData");

        if (value != null && !value.isEmpty()) {
            field = "051";
        }
        else{
            field = "051";//"901";
        }

        return field;
    }

    public String getPosPINCaptureCode() {
        //return pos_PIN_Capture_Code;
        String field = "04";

        return field;
    }

    public String getProcessingCodes() {
        //return pr_codes;
        String processingCode = "000000";
        String value = hashmap.get("TranType");
        int trantype = 0;
        if (value != null) {
            trantype = Integer.parseInt(value);
        }

        switch (trantype) {
            case TRAN_TYPE_VALUEADD_RECHARGE:
            case TRAN_TYPE_VALUEADD_VOUCHER:
            case TRAN_TYPE_PURCHASE: {
                String proc = (String) hashmap.get("fromacct");

                if (proc != null) {
                    processingCode = String.format("01%d000", Integer.parseInt(proc));
                }
                break;
            }

            /*case TRAN_TYPE_PURCASH: {
                String proc = (String) hashmap.get("fromacct");
                if (proc != null) {
                    processingCode = String.format("09%d000", Integer.parseInt(proc));
                }
                break;
            }*/

            case TRAN_TYPE_BILL_PAYMENT: {
                String proc = (String) hashmap.get("fromacct");
                if (proc != null) {
                    processingCode = String.format("54%d000", Integer.parseInt(proc));
                }
                break;
            }

            case TRAN_TYPE_CASHBACK: {
                String proc = (String) hashmap.get("fromacct");
                String proc1 = (String) hashmap.get("toacct");
                String proc2 = hashmap.get("AquireAcctType");

                if (proc != null) {
                    processingCode = String.format("00%d000", Integer.parseInt(proc));
                }
                if (proc1 != null && proc2.isEmpty()) {
                    processingCode = String.format("00%d0%d0", Integer.parseInt(proc), Integer.parseInt(proc1));
                }
                break;
            }
            case TRAN_TYPE_BALANCE: {
                String proc = (String) hashmap.get("fromacct");
                if (proc != null) {
                    processingCode = String.format("31%d000", Integer.parseInt(proc));
                }
                break;
            }
            case TRAN_TYPE_MINISTATE: {
                String proc = (String) hashmap.get("fromacct");
                if (proc != null) {
                    processingCode = String.format("38%d000", Integer.parseInt(proc));
                }
                break;
            }
            case TRAN_TYPE_DEPOSIT: {
                String proc = (String) hashmap.get("fromacct");
                String proc1 = (String) hashmap.get("toacct");
                String proc2 = hashmap.get("AquireAcctType");
                if (proc != null) {
                    processingCode = String.format("21%d000", Integer.parseInt(proc));
                }
                /*
                if (proc1 != null) {
                    processingCode = String.format("21%d0%d0", Integer.parseInt(proc1), Integer.parseInt(proc));
                }
                
                if (proc2 != null ) {
                    processingCode = String.format("21%d0%d0", Integer.parseInt(proc2), Integer.parseInt(proc));

                }*/
                break;
            }
           case TRAN_TYPE_PAYMENT: {
                String proc = (String) hashmap.get("fromacct");

                String proc1 = (String) hashmap.get("toacct");
                if (proc != null) {
                    processingCode = String.format("50%d0%d0", Integer.parseInt(proc), Integer.parseInt(proc1));
                }
                break;
            }
            case TRAN_TYPE_TAMSACCT_TRANSFER:
            case TRAN_TYPE_AGENT_LINKDEPOSIT: {
                String proc = (String) hashmap.get("AquireAcctType");

                String proc1 = (String) hashmap.get("toacct");
                String proc2 = (String) hashmap.get("fromacct");
                if (trantype == TRAN_TYPE_TAMSACCT_TRANSFER) {
                    processingCode = String.format("50%d0%d0", Integer.parseInt(proc2), Integer.parseInt(proc));
                } else if (proc != null) {
                    processingCode = String.format("50%d0%d0", Integer.parseInt(proc), Integer.parseInt(proc2));
                }
                if (hashmap.get("firstTran") != null && hashmap.get("firstTran").trim().equalsIgnoreCase("38")) {
                    processingCode = String.format("50%d0%d0", Integer.parseInt(proc1), Integer.parseInt(proc));
                }
                break;
            }

            case TRAN_TYPE_PAYMENT1: //TRAN_TYPE_TAMSACCT_TRANSFER:
            case TRAN_TYPE_AGENTDEPOSIT: {
                String proc = (String) hashmap.get("fromacct");

                String proc1 = (String) hashmap.get("toacct");
                if (proc != null) {
                    processingCode = String.format("39%d000", Integer.parseInt(proc)/* ,Integer.parseInt(proc)*/);
                }

                if (trantype == TRAN_TYPE_PAYMENT1) {
                    processingCode = String.format("39%d000", Integer.parseInt(proc));
                }

                break;
            }
            case TRAN_TYPE_PINCHANGE: {
                String proc = (String) hashmap.get("fromacct");
                if (proc != null) {
                    processingCode = String.format("92%d000", Integer.parseInt(proc));
                } else {
                    processingCode = String.format("920000", Integer.parseInt(proc));
                }
                break;
            }
            case TRAN_TYPE_TRANSFER: {
                String proc = (String) hashmap.get("fromacct");
                String proc1 = (String) hashmap.get("toacct");
                if (proc != null) {
                    processingCode = String.format("40%d000", Integer.parseInt(proc));
                }
                if (proc != null && proc1 != null) {
                    processingCode = String.format("40%d0%d0", Integer.parseInt(proc), Integer.parseInt(proc1));
                }
                break;
            }
            case TRAN_TYPE_AGENTREVERSAL:
            case TRAN_TYPE_REVERSAL: {
                String proc = (String) hashmap.get("fromacct");
                String proc1 = (String) hashmap.get("toacct");
                String revTnxType = hashmap.get("RevTxnType");
                switch (Integer.parseInt(revTnxType)) {
                    case TRAN_TYPE_VALUEADD_RECHARGE:
                    case TRAN_TYPE_VALUEADD_VOUCHER:
                    case TRAN_TYPE_PURCHASE: // purchase

                        if (proc != null) {
                            processingCode = String.format("00%d000", Integer.parseInt(proc));
                        }
                        break;
                    /*case TRAN_TYPE_PURCASH: // purchase & cashback

                        if (proc != null) {
                            processingCode = String.format("09%d000", Integer.parseInt(proc));
                        }
                        break;*/

                    case TRAN_TYPE_CASHBACK: // cashback
                    {
                        proc = (String) hashmap.get("fromacct");
                        proc1 = (String) hashmap.get("toacct");
                        String proc2 = hashmap.get("AquireAcctType");
                        if (proc != null) {
                            processingCode = String.format("00%d000", Integer.parseInt(proc));
                        }
                        if (proc1 != null && proc2.isEmpty()) {
                            processingCode = String.format("00%d0%d0", Integer.parseInt(proc), Integer.parseInt(proc1));
                        }
                        break;

                    }
                    case TRAN_TYPE_REFUND: // refund

                        processingCode = "200000";

                        break;

                    case TRAN_TYPE_AUTH: // auth only

                        processingCode = "000000";

                        break;

                    case TRAN_TYPE_DEPOSIT: {
                        processingCode = "210000";
                        // String proc1=hashmap.get("toacct");
                        if (proc != null) {
                            proc = (String) hashmap.get("fromacct");
                            proc1 = (String) hashmap.get("toacct");
                            String proc2 = hashmap.get("AquireAcctType");
                            if (proc != null) {
                                processingCode = String.format("2100%d0", Integer.parseInt(proc));
                            }
                            if (proc1 != null) {
                                processingCode = String.format("21%d0%d0", Integer.parseInt(proc1), Integer.parseInt(proc));
                            }
                            if (proc2 != null) {
                                processingCode = String.format("21%d0%d0", Integer.parseInt(proc2), Integer.parseInt(proc));

                            }
                        }
                    }
                    break;

                    case TRAN_TYPE_TRANSFER:
                        processingCode = "400000";
                        if (proc != null && proc1 != null) {
                            processingCode = String.format("40%d0%d0", Integer.parseInt(proc), Integer.parseInt(proc1));
                        }
                        break;
                    case TRAN_TYPE_TAMSACCT_TRANSFER:
                    case TRAN_TYPE_AGENTDEPOSIT:
                    case TRAN_TYPE_AGENT_LINKDEPOSIT: {
                        processingCode = "500000";
                        if (proc != null && proc1 != null) {
                            proc = (String) hashmap.get("fromacct");
                            proc1 = (String) hashmap.get("AquireAcctType");
                            if (proc != null && proc1 != null) {
                                processingCode = String.format("50%d0%d0", Integer.parseInt(proc1), Integer.parseInt(proc));
                            }
                        }
                        break;
                    }

                    default:

                        break;
                }//switch

                break;
            }
        }

        String field = processingCode;
        return field;
    }

    public String getReceivingInstitutionIdCode() {
        //return receiving_Inst_Id_Code;

        String field = hashmap.get("track2");
        /*if (hashmap.get("AquireTrack2") != null && !hashmap.get("AquireTrack2").isEmpty()) {
            field = hashmap.get("AquireTrack2");
        }*/
        field = field.substring(0, 6);
        int len = field.length();
        String fil = String.format("%1$02d", len) + field;
        return fil;
    }

    public String getReplacementAmount() {
        //return replacement_Amt;
        String field = "000000000000000000000000C00000000C00000000";
        /*int fieldvalue = 0;
        String field = "000000000000";
        String amount = (String) hashmap.get("amount");
        String transactionfee  = (String)hashmap.get("TransactionFee");
        String processorfee  = (String)hashmap.get("ProcessorFee");
        String tip = "0";//(String) hashmap.get("tip");
        String cashback = (String) hashmap.get("cashback");
        if ((amount != null)) {
            fieldvalue += Integer.parseInt(amount);
        }
        if (tip != null) {
            fieldvalue += Integer.parseInt(tip);
        }
        if (cashback != null) {
            fieldvalue += Integer.parseInt(cashback);
        }
        if (fieldvalue != 0) {
            field = "" + fieldvalue;
        }
        String zeroes = "";
        int lenght = 12 - field.length();
        if (lenght < 0) {
            throw new ISO8583Exception();
        } else {
            //field=String.format("%012s",field);
            for (int i = 0; i < lenght; i++) {
                zeroes += "0";
            }
        }
        field = zeroes.concat(field);
        //field = field + field + "C00000000C00000000";
        if ((transactionfee != null && !transactionfee.isEmpty()) && (processorfee != null && !processorfee.isEmpty())) {
            field = field + field + transactionfee + processorfee;
        } else {
            field = field + field + "C00000000C00000000";
        }*/

        return field;
    }

    public String getResponseCode() {
        //return response_Code;
        String field = "11";

        return field;
    }

    public String getRetrievalRefrenceNumber() {
        //return retrieve_Refrence_No;
        String field = "111111111111";
        String value = hashmap.get("RefNo");
        
        if(value != null){
            field = value;
        }

        /*value = hashmap.get("SeqNo");

        if (hashmap.get("secondtime") != null) {
            value = value.substring(value.length() - 1);
        }

        if (value != null) {

            int val = value.length();
            if (hashmap.get("secondtime") != null) {
                val = (val * 3) / 2;
            }

            field = "";
            for (int i = 0; i < 12 - val; i++) {
                field += "0";
                //value=String.format("%12s", value);
            }
            field = field + value;
        }*/

        return field;
    }

    public String getSecurityRelatedControlCode() {
        //return security_Related_Control_Code;
        String pan = "";
        String field = "";
        String value = hashmap.get("TranType");
        int trantype = 0;
        if (value != null) {
            trantype = Integer.parseInt(value);
        }
        
        String pinblock = hashmap.get("pinblock");
        String track2 = hashmap.get("track2");
        
        if (track2 != null && !track2.isEmpty()) {
            StringTokenizer st = null;
            if (track2.indexOf('=') > 0) {
                st = new StringTokenizer(track2, "=");
            } else {
                st = new StringTokenizer(track2, "D");
            }

            pan = st.nextToken();
        }
        
        Map<String, String> mappin = new HashMap<String, String>();
        mappin.put("SourcePIN", pinblock);
        mappin.put("SourceData", pan);
        
        //transate pinblock
        Map<String, String> map = new HashMap<String, String>(); 
        HSM hsm = new HSM();
        map = hsm.use3DES(mappin);
        if(map.get("errorCode") != null && map.get("errorCode").equals("00")){
            pinblock = map.get("pin");
        }

        if (TRAN_TYPE_PINCHANGE == trantype) {
            int index = pinblock.indexOf('|');
            if (index != -1) {
                String[] pinblocks = pinblock.split("\\|");

                pinblock = pinblocks[1];
                
                mappin.put("SourcePIN", pinblock);

                //transate pinblock
                Map<String, String> map2 = new HashMap<String, String>();
                HSM hsm2 = new HSM();
                map2 = hsm2.use3DES(mappin);
                if (map2.get("errorCode") != null && map2.get("errorCode").equals("00")) {
                    pinblock = map2.get("pin");
                }
        
                pinblock = String.format("%02x", 01) + pinblock + String.format("%078x", 0);
            }

            String PINData;
            byte[] pins = TripleDES.hexToByte(pinblock);

            PINData = "";
            for (int i = 0; i < pins.length; i++) {
                PINData += (char) pins[i];
            }

            field = PINData;

        }
        //String value=(String) hashmap.get("keyserial");

        return field;
    }

    public String getServiceRestrictionCode() {
        //return service_Restrictn_Code;
        String field = "101";
        field = hashmap.get("track2");

        if (field != null && field.contains("D")) {
            String[] part = field.split("D", -1);
            field = part[1].substring(4, 7);
        } else if (field != null && field.contains("=")) {
            String[] part = field.split("=", -1);
            field = part[1].substring(4, 7);
        }

        return field;
    }

    public synchronized String getSystemTraceAudit() {
        //return system_Trace_Audit;
        //this can be an auto_increament field in a DB.
        String field = String.format("%1$06d", ++tracer);
        
        if (!hashmap.isEmpty()) {
            String value = (String) hashmap.get("stan");

            /*if (hashmap.get("secondtime") != null) {
                value = value.substring(value.length() - 1);
            }*/

            /*if (value != null) {
                int val = value.length();
                for (int i = 0; i < 6 - val; i++) {
                    value = "0" + value;
                    //value=String.format("%12s", value);
                }
                field = value.substring(value.length() - 6, value.length());
            }*/
            if (value != null) {
                field = value;
            }

            /*String revSeq = (String) hashmap.get("RevSeqNo");
            if (revSeq != null && (!revSeq.isEmpty())) {
            int val = revSeq.length();
            for (int i = 0; i < 6 - val; i++) {
              revSeq = "0" + revSeq;
              //value=String.format("%12s", value);
            }
            field = revSeq.substring(revSeq.length() - 6, revSeq.length());
            }  //revSeq
            */
        }
        return field;
    }

    public String getTimeLocationTransaction() {
        //return time_Loc_Transc;
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);

        String field = String.format("%1$02d%2$02d%3$02d", hour, minute, second);

        return field;
    }

    public String getTrack1Data() {
        //return track1_Data;
        String field = "11111111111111111111";

        int len = field.length();
        String fil = String.format("%1$02d", len);

        return fil.concat(field);

    }

    public String getTrack2Data() {
        //return track2_Data;
        String field = "";
        int tranType = Integer.parseInt(hashmap.get("TranType"));
        String value = hashmap.get("track2");
        if (value != null && !value.isEmpty()) {
            field = value;
            int len = field.length();
            String fil = String.format("%1$02d", len) + field;
            value = fil;
        }

        /*String value2 = hashmap.get("AquireTrack2");
        if (value2 != null && !value2.isEmpty() && tranType == TRAN_TYPE_AGENT_LINKDEPOSIT) {
            field = value2;
            int len = field.length();
            String fil = String.format("%1$02d", len) + field;
            value = fil;
        }
        if (value2 != null && !value2.isEmpty() && tranType == TRAN_TYPE_TAMSACCT_TRANSFER) {
            field = value2;
            int len = field.length();
            String fil = String.format("%1$02d", len) + field;
            value = fil;
        }
        if (hashmap.get("TranType").trim().equalsIgnoreCase("36")) {
            value2 = hashmap.get("AquireTrack2");
            field = value2;
            int len = field.length();
            String fil = String.format("%1$02d", len) + field;
            value = fil;
        }*/
        
        field = field.replace('D', '=');
        return value;

    }

    public String getTransferNumber() {
        //return transfer_No;
        String field = "1111111111";

        return field;
    }

    public String getTransferReversalNumber() {
        //return transfer_Rev_No;
        String field = "1111111111";

        return field;
    }

    public String getTrasmissionDateTime() {
        //return trasm_Date_Time;
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DATE);
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);

        String field = String.format("%1$02d%2$02d%3$02d%4$02d%5$02d", ++month, day, hour, minute, second);

        return field;
    }
}

class GetStructData {

    int type = 0;

    GetStructData(int type) {
        this.type = type;

    }

    public String getAdditonalInformation() {
        return "";
    }

    public String getMediaBatchNumber() {
        return "";
    }

    public String getMediaTotals() {
        return "";
    }
}
