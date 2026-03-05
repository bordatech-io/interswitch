/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.postilion;

import com.bordatech.beans.ISO8583;
import com.bordatech.gateway.GetSubFields;
import com.bordatech.gateway.IsoTransformer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kazeemakinbola
 */
public class Main {
     public static void main(String args[]){
        //read
        byte [] data = hexToByte("071830323130F23E46D5ABE081A00000000006000022313935303631333630323036353730363337363830353031303230303030303030313030303030303532363132353633303030303233383133353633303035323632383035303532363630313139303130303130303132443030303031303735433030303030303030313135303531333630323036353036313131313131333635303631333630323036353730363337363830443238303536303130303537343237373030313337303037333630313430303630313137303134383731303030303030303030303030343035546F79696E4F6C6F7775202020202020202020202020204F6C6F7775202020202020202020204E47353636303034313531303030333130373031303937313837343938313731303230333539303238323431303131333030393333393130313535313132303132313333343430303230303134373560105400800000003130393731383734393831374153505357547372632020204E455054554E45736E6B20203030353938333030303233384E544D47726F757020202020303953575446424E736E6B34355154315452515453533B3530363133362A2A373638307C323830353B3632373738377C3030323930373533323532303235303532363031333332323138506F7374696C696F6E3A4D65746144617461333132383231314D65646961546F74616C73313131323132506F7374696C696F6E3A544D3131313231324D6564696142617463684E72313131323236506F7374696C696F6E3A416374697665416374697665446174613131313231374164646974696F6E616C456D76546167733131313231344164646974696F6E616C496E666F3131313231314D65646961546F74616C73333231313C4D65646961546F74616C733E3C546F74616C733E3C416D6F756E743E34393030303030303C2F416D6F756E743E3C43757272656E63793E3536363C2F43757272656E63793E3C4D65646961436C6173733E436173683C2F4D65646961436C6173733E3C2F546F74616C733E3C546F74616C733E3C416D6F756E743E303C2F416D6F756E743E3C43757272656E63793E3030303C2F43757272656E63793E3C4D65646961436C6173733E43617264733C2F4D65646961436C6173733E3C2F546F74616C733E3C2F4D65646961546F74616C733E323132506F7374696C696F6E3A544D32313832313253706C697453696E6B496E643131463231324D6564696142617463684E7231383238343739383133323236506F7374696C696F6E3A41637469766541637469766544617461323332323233506F7374436172643A50696E42617365645365637572653134747275653231374164646974696F6E616C456D7654616773333334343C4164646974696F6E616C456D76546167733E3C456D765461673E3C54616749643E35303C2F54616749643E3C54616756616C75653E353636353732373636353C2F54616756616C75653E3C2F456D765461673E3C456D765461673E3C54616749643E38313C2F54616749643E3C54616756616C75653E30303031383641303C2F54616756616C75653E3C2F456D765461673E3C456D765461673E3C54616749643E354633363C2F54616749643E3C54616756616C75653E30323C2F54616756616C75653E3C2F456D765461673E3C456D765461673E3C54616749643E354633343C2F54616749643E3C54616756616C75653E30313C2F54616756616C75653E3C2F456D765461673E3C456D765461673E3C54616749643E39423C2F54616749643E3C54616756616C75653E363830303C2F54616756616C75653E3C2F456D765461673E3C2F4164646974696F6E616C456D76546167733E3231344164646974696F6E616C496E666F333433363C4164646974696F6E616C496E666F3E3C5472616E73616374696F6E3E3C4F70436F64653E41434248414242413C2F4F70436F64653E3C427566666572433E303032393037353332353C2F427566666572433E3C436667457874656E646564547278547970653E383530323C2F436667457874656E646564547278547970653E3C436667526563656976696E67496E737469747574696F6E4944436F64653E3632373738373C2F436667526563656976696E67496E737469747574696F6E4944436F64653E3C2F5472616E73616374696F6E3E3C446F776E6C6F61643E3C41544D436F6E66696749443E353030363C2F41544D436F6E66696749443E3C41746D417070436F6E66696749443E353030363C2F41746D417070436F6E66696749443E3C4C6F616473657447726F75703E464550204E435220454D562B436172646C6573732B586C733C2F4C6F616473657447726F75703E3C446F776E6C6F61644170703E51544949495F50415F4C53203230323420454D5620464550205374616E64617264204E44432B333C2F446F776E6C6F61644170703E3C2F446F776E6C6F61643E3C2F4164646974696F6E616C496E666F3E38353032");
        Main.readPostilionMessage(data);
//         Main.readFromHex();
//         Main.writeIso();
        //write
        //ISO8583 postBridge = new ISO8583("0800");
        //postBridge = new ISO8583("0200");
        //Testing.financialRequest(postBridge, map);
       
    }

    public static void writeIso(){

        // Create a dummy map with ISO field values
        Map<String, String> isoFields = new HashMap<>();
        isoFields.put("2", "5399230000000008"); // PAN
        isoFields.put("3", "000001"); // Processing Code
        isoFields.put("4", "000000010000"); // Amount Transaction
        isoFields.put("7", "0506123456"); // Transmission Date & Time
        isoFields.put("11", "123456"); // STAN
        isoFields.put("12", "123456"); // Time Local Transaction
        isoFields.put("13", "0506"); // Date Local Transaction
        isoFields.put("14", "2505"); // Expiry
        isoFields.put("18", "6011"); // Merchant Type
        isoFields.put("22", "051"); // POS Entry Mode
        isoFields.put("23", "001"); // Card Sequence Number
        isoFields.put("25", "00"); // POS Condition Code
        isoFields.put("26", "12"); // POS PIN Capture Code
        isoFields.put("28", "C00000000"); // Amount Transaction Fee
        isoFields.put("32", "12345678901"); // Acquiring Institution ID Code
        isoFields.put("35", "5399230000000008=25052210000000000000"); // Track2
        isoFields.put("37", "123456789012"); // Retrieval Reference Number
        isoFields.put("40", "000"); // Service Restriction Code
        isoFields.put("41", "TERMID01"); // Terminal ID
        isoFields.put("42", "ACCEPTORID001"); // Card Acceptor ID
        isoFields.put("43", "9260490214             Netflix      LANG"); // Location
        isoFields.put("49", "566"); // Currency Code
        isoFields.put("52", "5F2A59A1D8998BAA"); // PIN Block (dummy)
        isoFields.put("56", "1111"); // Message Reason Code
        isoFields.put("59", "ECHO123"); // Echo Data
        isoFields.put("98", "Payee001"); // Payee
        isoFields.put("103", "1234567890123456"); // Account ID 2
        isoFields.put("123", "91010151134C101"); // POS Data Code
        isoFields.put("127.02", "SWITCHKEY001"); // Subfield 127.2
        isoFields.put("127.03", "ROUTING001"); // Subfield 127.3
        isoFields.put("127.13", "LAGOS"); // Subfield 127.13
        isoFields.put("127.20", "20250506"); // Subfield 127.20
        isoFields.put("127.25", "ICC_DATA_HERE"); // Subfield 127.25
        isoFields.put("127.33", "EXTXTYPE01"); // Subfield 127.33

        // Create an instance of ISO8583 object (this would be your custom class)
        ISO8583 postBridge_ = new ISO8583("0200");


        // Call your method
        ISO8583  postBridge = isoRequest(postBridge_, isoFields);

        // Print the result (ISO 8583 formatted message)
        System.out.println("Generated ISO 8583 Message:");
        System.out.println(postBridge.getISO8583subfield());

        String message = postBridge.toString();

        //System.out.println("Response:"+fromPostBridge.toString());

        byte[] bcdmeslen = postBridge.toBCD(String.format("%04x", message.length()));
        String bcdmeslenChar = "";
        for (int i = 0; i < bcdmeslen.length; i++) {
            bcdmeslenChar += (char) bcdmeslen[i];
        }

        message = (bcdmeslenChar).concat(message);

        byte[] ti = new byte[message.length()];

        for (int k = 0; k < message.length(); k++) {
            ti[k] = (byte) message.charAt(k);
        }

        System.out.println("Response:"+new String(ti));

        byte[] response = ti;

        System.out.println("Response sent: " + ToHexString(response));

    }

    public static void readPostilionMessage(byte[] data){
        System.out.println("===============about to read===============");
        IsoTransformer transformer = new IsoTransformer();
//        ISOLogger log = new ISOLogger();
        //String receive = "";
        ISO8583 fromPostBridge = new ISO8583("");
        //ISOReader isoReceived = new ISOReader();

        
        String receive = "";
        for (int i = 2; i < data.length; i++) {
            receive += (char) data[i];
        }

        System.out.println("receive="+receive);
        
        transformer.getMti(fromPostBridge, receive.substring(0, 4));
        transformer.getBitmap(fromPostBridge, receive.substring(0, receive.length()));
        
        //isoReceived.setResponse(fromPostBridge);
        
        System.out.println("PAN:"+fromPostBridge.getMti());
        System.out.println("PAN:"+fromPostBridge.getPan());
        System.out.println("123:"+ fromPostBridge.getPosDataCode());
        System.out.println("originatorDateSettlement "+ fromPostBridge.getOriginatorDateSettlement());
        
        
//        fromPostBridge.setMti("0210");
//        fromPostBridge.setPan("195061360206564676447");
//        fromPostBridge.setResponseCode("00");
        
//        fromPostBridge.setBitmapsb(true);
//        String subfields = fromPostBridge.toStringsb();
//        String length = String.format("%06d", subfields.length());
//        fromPostBridge.setISO8583subfield(length + subfields);
//
//        fromPostBridge.setSecondaryBitmap();
//        fromPostBridge.setBitmap(true);

        String message = fromPostBridge.toString();
        
        //System.out.println("Response:"+fromPostBridge.toString());
        
        byte[] bcdmeslen = fromPostBridge.toBCD(String.format("%04x", message.length()));
        String bcdmeslenChar = "";
        for (int i = 0; i < bcdmeslen.length; i++) {
            bcdmeslenChar += (char) bcdmeslen[i];
        }

        message = (bcdmeslenChar).concat(message);

        byte[] ti = new byte[message.length()];

        for (int k = 0; k < message.length(); k++) {
            ti[k] = (byte) message.charAt(k);
        }
        
        System.out.println("Response:"+new String(ti));
    }
    
    public static void writePostilionMessage(){
        
    }

    public static String pan2Account(String Pan) {
        String acc = "";
        if (Pan.equals("5061360206564676447")) {
            acc = "2031235682";
        } else {
            acc = "2031657119";
        }
        return acc;
    }
    public static ISO8583 readFromHex(){
        byte [] data = hexToByte("072930323030F23E46D5A9E08120000000004600002231393530363133363032303635373036333736383035303130323030303030303031303030303030353236313235363330303030323338313335363330303532363238303530353236363031313930313030313030313244303030303130373543303030303030303031313530353133363032303635303631313131313133363530363133363032303635373036333736383044323830353630313030353734323737303031333730303733363031343630313137303134383731303030303030303030303030343035546F79696E4F6C6F7775202020202020202020202020204F6C6F7775202020202020202020204E473536363030343135313030313039373138373439383137303032393037353332357C31307C202020202020202020202031303230333539303238323431303131333030393333393130313535313132303132313333343430303230303134373560105400800000003130393731383734393831374153505357547372632020204E455054554E45736E6B20203030353938333030303233384E544D47726F757020202020303953575446424E736E6B34355154315452515453533B3530363133362A2A373638307C323830353B3632373738377C3030323930373533323532303235303532363031333332323138506F7374696C696F6E3A4D65746144617461333132383231314D65646961546F74616C73313131323132506F7374696C696F6E3A544D3131313231324D6564696142617463684E72313131323236506F7374696C696F6E3A416374697665416374697665446174613131313231374164646974696F6E616C456D76546167733131313231344164646974696F6E616C496E666F3131313231314D65646961546F74616C73333231313C4D65646961546F74616C733E3C546F74616C733E3C416D6F756E743E34393030303030303C2F416D6F756E743E3C43757272656E63793E3536363C2F43757272656E63793E3C4D65646961436C6173733E436173683C2F4D65646961436C6173733E3C2F546F74616C733E3C546F74616C733E3C416D6F756E743E303C2F416D6F756E743E3C43757272656E63793E3030303C2F43757272656E63793E3C4D65646961436C6173733E43617264733C2F4D65646961436C6173733E3C2F546F74616C733E3C2F4D65646961546F74616C733E323132506F7374696C696F6E3A544D32313832313253706C697453696E6B496E643131463231324D6564696142617463684E7231383238343739383133323236506F7374696C696F6E3A41637469766541637469766544617461323332323233506F7374436172643A50696E42617365645365637572653134747275653231374164646974696F6E616C456D7654616773333334343C4164646974696F6E616C456D76546167733E3C456D765461673E3C54616749643E35303C2F54616749643E3C54616756616C75653E353636353732373636353C2F54616756616C75653E3C2F456D765461673E3C456D765461673E3C54616749643E38313C2F54616749643E3C54616756616C75653E30303031383641303C2F54616756616C75653E3C2F456D765461673E3C456D765461673E3C54616749643E354633363C2F54616749643E3C54616756616C75653E30323C2F54616756616C75653E3C2F456D765461673E3C456D765461673E3C54616749643E354633343C2F54616749643E3C54616756616C75653E30313C2F54616756616C75653E3C2F456D765461673E3C456D765461673E3C54616749643E39423C2F54616749643E3C54616756616C75653E363830303C2F54616756616C75653E3C2F456D765461673E3C2F4164646974696F6E616C456D76546167733E3231344164646974696F6E616C496E666F333433363C4164646974696F6E616C496E666F3E3C5472616E73616374696F6E3E3C4F70436F64653E41434248414242413C2F4F70436F64653E3C427566666572433E303032393037353332353C2F427566666572433E3C436667457874656E646564547278547970653E383530323C2F436667457874656E646564547278547970653E3C436667526563656976696E67496E737469747574696F6E4944436F64653E3632373738373C2F436667526563656976696E67496E737469747574696F6E4944436F64653E3C2F5472616E73616374696F6E3E3C446F776E6C6F61643E3C41544D436F6E66696749443E353030363C2F41544D436F6E66696749443E3C41746D417070436F6E66696749443E353030363C2F41746D417070436F6E66696749443E3C4C6F616473657447726F75703E464550204E435220454D562B436172646C6573732B586C733C2F4C6F616473657447726F75703E3C446F776E6C6F61644170703E51544949495F50415F4C53203230323420454D5620464550205374616E64617264204E44432B333C2F446F776E6C6F61644170703E3C2F446F776E6C6F61643E3C2F4164646974696F6E616C496E666F3E38353032");
        IsoTransformer transformer = new IsoTransformer();
        ISO8583 fromPostBridge = new ISO8583("");

        String receive = "";
        for (int i = 2; i < data.length; i++) {
            receive += (char) data[i];
        }

        System.out.println("receive1="+receive);

        transformer.getMti(fromPostBridge, receive.substring(0, 4));
        transformer.getBitmap(fromPostBridge, receive.substring(0, receive.length()));

        String transMode = "normal";
        ISO8583 isoResMsg_ = new ISO8583(
                "0210"
        );

//        isoResMsg_ = copyIsoObject.doCopy(fromPostBridge, isoResMsg_);

        System.out.println("=======================about to start a withdrawal===========" + transMode);

        // Copy only non-null values from fromPostBridge to isoResMsg_
        System.out.println("=========about to copy================");
//        copyNonNullProperties(fromPostBridge, isoResMsg_);





        // Extract transaction details
        String pan = fromPostBridge.getPan();
        double amount = Double.parseDouble(fromPostBridge.getAmountTransaction()) / 100;
        String nuban = pan2Account(pan);

        System.out.println("PAN: " + pan);
        System.out.println("NUBAN: " + nuban);
        System.out.println("AMOUNT: " + amount);
        System.out.println("performWithdrawal isoResMsg_ before response: " + isoResMsg_);

        isoResMsg_.setMti("0210"
        );

        // Check balance

        double effectiveBalance = 100000.0;

        System.out.println("EFFECTIVE BALANCE: " + effectiveBalance);
        String BIN ="50513602065";

        isoResMsg_.setAcquiringInstitutionIdCode(BIN.length() + BIN);
        System.out.println(BIN.length() +":bin:"+ BIN);
//        isoResMsg_.setAuthorizationProfile("11");

        isoResMsg_ = new CopyMessage().copy(fromPostBridge, isoResMsg_);

        isoResMsg_.setResponseCode("00"); // 39
        isoResMsg_.setAuthorizationLifeCycle("003107");


        byte[] response = isoResMsg_.getMti().equals("0810")
                ? WriteMessage.WriteToPostilionWIthSub(isoResMsg_)
                : WriteMessage.WriteToPostilion(isoResMsg_);

        System.out.println("Response sent: " + ToHexString(response));
        return fromPostBridge;
    }
    
    public static ISO8583  isoRequest(ISO8583 postBridge, Map<String, String> map) {
        IsoMessage setField = new IsoMessage(map);
        GetSubFields setSubField = new GetSubFields(map);
//        System.out.println("setField.getPan()"+setField.getPan());
        if(!setField.getPan().isEmpty()){
            postBridge.setPan(16+setField.getPan()); //2
        }

        postBridge.setProcessingCodes(setField.getProcessingCode());//3
        postBridge.setAmountTransaction(setField.getAmountTransaction());//4
//        postBridge.setAmountSettlement(setField.getAmountSettlement()); //5
        postBridge.setTransmissionDateTime(setField.getTransmissionDateTime());//7
        postBridge.setSystemTraceAudit(setField.getCardAcceptorId());//11
        postBridge.setTimeLocalTransaction(setField.getLocalTime());//12
        postBridge.setDateLocalTransaction(setField.getLocalDate());//13
        postBridge.setDateExpiration(setField.getExpiryDate());//14
//        postBridge.setDateSettlement(setField.getDateSettlement());//15
        postBridge.setMerchantType(setField.getMerchantType()); //18
        postBridge.setPosEntryMode(setField.getPosEntryMode());//22
        postBridge.setCardSequenceNumber(setField.getCardSequenceNumber()); //23
        postBridge.setPosConditionCode(setField.getPosConditionCode()); //25
//        postBridge.setPosPINCaptureCode(setField.getPosPINCaptureCode()); // 26
//        postBridge.setAmountTransactionFee(setField.getAmountTransactionFee());//28
//        postBridge.setAmountSettlementFee(setField.getAmountSettlementFee()); //29
//        postBridge.setAmountTransactionProcessingFee(setField.getAmountTransactionProcessingFee());//30
////        postBridge.setAcquiringInstitutionIdCode(setField.getAcquirerInstitutionIdCode());//32
//        //postBridge.setForwardingInstitutionIdCode(setField.getForwardingInstitutionIdCode());//33
////        postBridge.setTrack2Data(setField.getTrack2Data());   //field 35
////        postBridge.setRetrievalRefrenceNumber(setField.getRetrievalRefrenceNumber());//field 37
////        postBridge.setServiceRestrictionCode(setField.getServiceRestrictionCode());  //40
////        postBridge.setCardAcceptorTerminalId(setField.getCardAcceptorTerminalId()); //field 41
////        postBridge.setCardAcceptorIdCode(setField.getCardAcceptorIdCode()); //field 42
////        postBridge.setCardAcceptorIdLocation(setField.getCardAcceptorIdLocation()); //field 43
////        postBridge.setCurrencyCodeTransaction(setField.getCurrencyCodeTransaction()); //field 49
//        //postBridge.setCurrencyCodeSettlement(setField.getCurrencyCodeSettlement());//field 50
////        String pinData = setField.getPINData();
////        if ((pinData.length()>0)) {
////            postBridge.setPINData(pinData);//52
////        }
////        postBridge.setMessageReasonCode(setField.getMessageReasonCode());//56
////        postBridge.setEchoData(setField.getEchoData());                   //59
////        postBridge.setPayee(setField.getPayee());//98
//        //postBridge.setReceivingInstitutionIdCode(setField.getReceivingInstitutionIdCode());//100
//        //String account1 = setField.getAccountIdentification1();
//        //if ((account1.length()>0 && !account1.isEmpty())) {
//          //  postBridge.setAccountIdNumber1(account1);            //102
//        //}
//
////        String account2 = setField.getAccountIdentification2();
////        if ((account2.length()>0 && !account2.isEmpty())) {
////            postBridge.setAccountIdNumber2(account2);            //103
////        }
////        postBridge.setPosDataCode(setField.getPosDataCode());//123
//
//        postBridge.setBankDetails(setSubField.getBankDetails()); //127.19
//
        postBridge.setSwitchKey(setField.getSubField127_02().length()+setField.getSubField127_02()); //127.02
//        postBridge.setRoutingInformation(setSubField.getRoutingInfomation()); //127.03
//
//        postBridge.setPosGeographicData(setSubField.getPosGeographicData()); //127.13
//        postBridge.setOriginatorDateSettlement(setSubField.getOriginatorDateSettlment()); //127.20
//
//
//        //postBridge.setAuthorizationProfile(setSubField.getAuthorizationProfile()); //127.06
//        //postBridge.setTerminalOwner(setSubField.getTerminalOwner()); //127.12
//        // postBridge.setOriginatorDateSettlement(setSubField.getOriginatorDateSettlment()); //127.20
//        //  postBridge.setStructureData(setSubField.getStructureData()); //127.22
//        String iccData = setSubField.getIccData();
////        if (iccData.length()>0) {
////            postBridge.setIccData(iccData);     //127.25
////        }
//
//        postBridge.setExtendedTransactionType(setSubField.getExtendedTransactionType()); //127.33
      
        postBridge.setBitmapsb(true);
        String subfields = postBridge.toStringsb();
        String length = String.format("%06d", subfields.length());
        System.out.println(subfields +  "=//////==" +length);
        postBridge.setISO8583subfield(length + subfields);

        postBridge.setSecondaryBitmap();
        postBridge.setBitmap(true);

        return postBridge;

    }
    
    public static byte[] hexToByte(String hexString) {
        String str = new String("0123456789ABCDEF");
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0, j = 0; i < hexString.length(); i++) {
            byte firstQuad = (byte) ((str.indexOf(hexString.charAt(i))) << 4);
            byte secondQuad = (byte) str.indexOf(hexString.charAt(++i));
            bytes[j++] = (byte) (firstQuad | secondQuad);

        }
        return bytes;
    }
    
    public static String ToHexString(byte[] toAsciiData) {
        String hexString = "";

        for (byte b : toAsciiData) {
            hexString += String.format("%02X", b);
        }
        return hexString;
    }
}
