package com.bordatech.gateway;

//import com.byteworks.connection.SecureKeyProvider;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 */
public class GetSubFields {

    private static final Logger log = LoggerFactory.getLogger(GetSubFields.class);
    private String map_key;
    private Map<String, String> hashmap;
    GregorianCalendar time = new GregorianCalendar();

    public GetSubFields(String map_key) {
        this.map_key = map_key;
    }

    public GetSubFields(Map<String, String> hashmap) {
        this.hashmap = hashmap;
    }

    public String getAccountTypeQualifier() {
        String field = "11";

        return field;
    }

    public String getAcqirerNetworkId() {
        String field = "11111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil.concat(field);

    }

    public String getAdditonalNodeData() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$03d", len);
        return fil.concat(field);

    }

    public String getAddressVerificationData1() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil.concat(field);

    }

    public String getAddressVerificationResult() {
        String field = "1";

        return field;
    }

    public String getAmericanExpressCardId() {
        String field = "1111";

        return field;
    }

    public String getAuthorizationProfile() {
        String field = "11";
        return field;
    }

    public String getBankDetails() {
        String field = "1111111111111111111111111111111";
        if (hashmap.get("BankDetails") != null) {
            field = hashmap.get("BankDetails");
        }
        field = String.format("%-31s", field);
        return field;
    }

    public String getBitmap() {
        return "";
    }

    public String getCardHolderInformation() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil.concat(field);
    }

    public String getCardVerificationResult() {
        String field = "U";

        return field;
    }

    public String getCheckData() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil + field;
    }

    public String getCvv2() {
        String field = "111";

        return field;
    }

    public String getExtendedTransactionType() {
        String field = "6008";

        return field;
    }

    public String getIccData() {
        String field = "111111111111111";
        String value = hashmap.get("ICCData");
        if (value != null && !value.isEmpty()) {
            
            //String parsevalue = parseTLV(value);
            //GetIccDataBitmap icc = new GetIccDataBitmap(parsevalue);
            //GetIccDataBitmap icc = new GetIccDataBitmap(value);
            //icc.processData();
            
            String parsevalue = parseTLV(value);
            //System.out.println("ICCDATA="+parsevalue);
            GetIccDataXML icc = new GetIccDataXML(parsevalue);
            icc.processData();
            String data = icc.toString();
            int len = data.length();
            String fil = String.format("%1$04d", len);
            return fil.concat(data);
        }
        return null;
    }

    public String getIssuerNetworkId() {
        String field = "11111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil + field;
    }

    public String getMapkey() {
        return "";
    }

    public String getOriginalKey() {
        String field = "111111111111111";
        String value = hashmap.get("RefNo");
        if (value != null) {
            field = value;
        }
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil.concat(field);
    }

    public String getOriginalNode() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil.concat(field);
    }

    public String getOriginatorDateSettlment() {

        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DATE);

        String field = String.format("%1$04d%2$02d%3$02d", year, ++month, day);

        return field;
    }

    public String getPayeeNameAddress() {
        String field = "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";

        return field;
    }

    public String getPayerAccount() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil.concat(field);
    }

    public String getPosData() {
        String field = "1111111111111111111111";

        return field;
    }

    public String getPosGeographicData() {
        //String field = "11111111111111111";
        String field = "10566   100211566";

        return field;
    }

    public String getRecordIdentification() {
        String field = "11111111111";
        int len = field.length();
        String fil = String.format("%1$02d", len);
        return fil.concat(field);
    }

    public String getRetentionData() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$03d", len);
        return fil.concat(field);
    }

    public String getRoutingInfomation() {
        String field = "111111111111111111111111111111111111111111111111";
        String value = hashmap.get("RefNo");
        if(value != null){
            field = "                        "+value.substring(6)+value.substring(6)+"            ";
        }
        return field;
    }

    public String getSecureData() {
        String field = "1111111111111111111111111111111111111111";

        return field;
    }

    public String getSecureResult() {
        String field = "1";

        return field;
    }

    public String getServiceStationData() {
        String field = "1111111111111111111111111111111111111111111111111111111111111111111111111";

        return field;
    }

    public String getSponsorBank() {
        String field = "11111111";

        return field;
    }

    public String getStructureData() {
        String field = "1111111111";
        field = String.format("17REFCODE2%1$02d%2$s", field.length(), field);
        String value = hashmap.get("RefCode");
        if (value != null && !value.equals("")) {
            value = String.format("17REFCODE2%1$02d%2$s", value.length(), value);
            field = value;
        }
        String fil = String.format("%1$05d", field.length());
        return fil.concat(field);
    }

    public String getSwitchKey() {
        String field = "00000013";//00000000";
        //SecureKeyProvider sp = new SecureKeyProvider();
        //field  = sp.get(15);
 
        String value = hashmap.get("RefNo");
        if (value != null) {
            field = value;
        }
        
        int len = field.length();
        String fil = String.format("%1$02d", len);
        return fil.concat(field);
    }

    public String getTerminalOwner() {

        String value = (String) hashmap.get("TermOwner");
        String field = "111111111111111";
        value = "057";
        if (value != null) {
            field = value;
        }
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil.concat(field);
    }

    public String getUcafData() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil + field;
    }

    public String getValidationData() {
        String field = "111111111111111";
        int len = field.length();

        String fil = String.format("%1$02d", len);
        return fil.concat(field);

    }
    
    public static String parseTLV(String tlv) {
        if (tlv == null || tlv.length() % 2 != 0) {
            throw new RuntimeException("Invalid tlv, null or odd length");
        }
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < tlv.length();) {
            try {
                String key = tlv.substring(i, i = i + 2);

                if ((Integer.parseInt(key, 16) & 0x1F) == 0x1F) {
                    // extra byte for TAG field
                    key += tlv.substring(i, i = i + 2);
                }
                String len = tlv.substring(i, i = i + 2);
                int length = Integer.parseInt(len, 16);

                if (length > 127) {
                    // more than 1 byte for lenth
                    int bytesLength = length - 128;
                    len = tlv.substring(i, i = i + (bytesLength * 2));
                    length = Integer.parseInt(len, 16);
                }
                length *= 2;

                String value = tlv.substring(i, i = i + length);
                //System.out.println(key + " = " + value);
                hashMap.put(key, value);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Error parsing number", e);
            } catch (IndexOutOfBoundsException e) {
                throw new RuntimeException("Error processing field", e);
            }
        }

        String tag9F26 = (hashMap.get("9F26") == null) ? "" : (String)hashMap.get("9F26");
        String tag9F27 = (hashMap.get("9F27") == null) ? "" : (String)hashMap.get("9F27");
        String tag9F10 = (hashMap.get("9F10") == null) ? "" : (String)hashMap.get("9F10");
        String tag9F37 = (hashMap.get("9F37") == null) ? "" : (String)hashMap.get("9F37");
        String tag9F36 = (hashMap.get("9F36") == null) ? "" : (String)hashMap.get("9F36");
        String tag95 = (hashMap.get("95") == null) ? "" : (String)hashMap.get("95");
        String tag9A = (hashMap.get("9A") == null) ? "" : (String)hashMap.get("9A");
        String tag9C = (hashMap.get("9C") == null) ? "" : (String)hashMap.get("9C");
        String tag9F02 = (hashMap.get("9F02") == null) ? "" : (String)hashMap.get("9F02");
        String tag5F2A = (hashMap.get("5F2A") == null) ? "" : (String)hashMap.get("5F2A");
        String tag5F34 = (hashMap.get("5F34") == null) ? "" : (String)hashMap.get("5F34");
        String tag82 = (hashMap.get("82") == null) ? "" : (String)hashMap.get("82");
        String tag9F1A = (hashMap.get("9F1A") == null) ? "" : (String)hashMap.get("9F1A");
        String tag9F03 = (hashMap.get("9F03") == null) ? "" : (String)hashMap.get("9F03");
        String tag9F33 = (hashMap.get("9F33") == null) ? "" : (String)hashMap.get("9F33");
        String tag84 = (hashMap.get("84") == null) ? "" : (String)hashMap.get("84");
        String tag9F08 = (hashMap.get("9F08") == null) ? "" : (String)hashMap.get("9F08");
        String tag9F34 = (hashMap.get("9F34") == null) ? "" : (String)hashMap.get("9F34");
        String tag9F35 = (hashMap.get("9F35") == null) ? "" : (String)hashMap.get("9F35");
        String tag9F1E = (hashMap.get("9F1E") == null) ? "" : (String)hashMap.get("9F1E");
        String tag9F53 = (hashMap.get("9F53") == null) ? "" : (String)hashMap.get("9F53");
        String tag84x = (hashMap.get("84") == null) ? "" : (String)hashMap.get("84");
        String tag9F09 = (hashMap.get("9F09") == null) ? "" : (String)hashMap.get("9F09");
        String tag9F41 = (hashMap.get("9F41") == null) ? "" : (String)hashMap.get("9F41");
        String tag9F34x = (hashMap.get("9F34") == null) ? "" : (String)hashMap.get("9F34");

        String iccdata = tag9F26+"|"+tag9F27+"|"+tag9F10+"|"+tag9F37+"|"+tag9F36+"|"+tag95+"|"+tag9A+"|"+tag9C+"|"+tag9F02+"|"+tag5F2A+"|"+tag5F34+"|"+tag82+"|"+tag9F1A+"|"+tag9F03+"|"+tag9F33+"|"+tag84+"|"+tag9F08+"|"+tag9F34+"|"+tag9F35+"|"+tag9F1E+"|"+tag9F53+"|"+tag84x+"|"+tag9F09+"|"+tag9F41+"|"+tag9F34x+"|";


        return iccdata;//hashMap;
    }
}



