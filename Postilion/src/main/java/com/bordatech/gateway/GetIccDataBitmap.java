/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.gateway;

import com.bordatech.support.Tokenise;
import com.bordatech.support.TripleDES;

/**
 *
 *
 */
class IccBitmap {

    public byte bitmap1 = '\0';
    public byte bitmap2 = '\0';
    public byte bitmap3 = '\0';
    public byte bitmap4 = '\0';
    public byte bitmap5 = '\0';
    public byte bitmap6 = '\0';
    public byte bitmap7 = '\0';
    public byte bitmap8 = '\0';

    public String setBitmapStr(int length) {
        String bitmap_buffer = "";

        for (int i = 0; i < length; i++) {
            switch (i) {
                case 0: {
                    if (this.bitmap1 != '\0') {
                        bitmap_buffer += (char) this.bitmap1;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap2 != '\0') {
                        bitmap_buffer += (char) this.bitmap2;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap3 != '\0') {
                        bitmap_buffer += (char) this.bitmap3;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap4 != '\0') {
                        bitmap_buffer += (char) this.bitmap4;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap5 != '\0') {
                        bitmap_buffer += (char) this.bitmap5;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap6 != '\0') {
                        bitmap_buffer += (char) this.bitmap6;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap7 != '\0') {
                        bitmap_buffer += (char) this.bitmap7;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }

                    if (this.bitmap8 != '\0') {
                        bitmap_buffer += (char) this.bitmap8;
                    } else {
                        bitmap_buffer += (char) (0x8000);
                    }
                    break;
                }
            }//end of for statement
        }
        bitmap_buffer = TripleDES.toAscii(bitmap_buffer);
        return bitmap_buffer;
    }

    public void setBitmap(int position) {
        int byte_position = position % 8;
        byte[] fixer = {1, -128, 64, 32, 16, 8, 4, 2};
        position = position / 8;
        if (byte_position == 0) {
            position--;
        }
        switch (position) {
            case 0: {
                this.bitmap1 |= fixer[byte_position];
                break;
            }
            case 1: {
                this.bitmap2 |= fixer[byte_position];
                break;
            }
            case 2: {
                this.bitmap3 |= fixer[byte_position];
                break;
            }
            case 3: {
                this.bitmap4 |= fixer[byte_position];
                break;
            }
            case 4: {
                this.bitmap5 |= fixer[byte_position];
                break;
            }
            case 5: {
                this.bitmap6 |= fixer[byte_position];
                break;
            }
            case 6: {
                this.bitmap7 |= fixer[byte_position];
                break;
            }
            case 7: {
                this.bitmap8 |= fixer[byte_position];
                break;
            }
        }
    }
}

class GetIccDataBitmap extends IccBitmap {

    private String iccdata;
    private String amountAuthorization = "";
    private String amountOther = "";
    private String applicationIdentication = "";
    private String applicationInterchangeProfile = "";
    private String applicationTransactionCounter = "";
    private String applicationUsageControl = "";
    private String authorisationResponseCode = "";
    private String cardAuthenticatorReliabilityIndicator = "";
    private String cardAuthenticatorResultCode = "";
    private String chipConditionCode = "";
    private String cryptogram = "";
    private String cryptogramInformationData = "";
    private String cvmResult = "";
    //private String cvmList = "";
    //private String defaults = "";
    // private String dedicatedFileName = "";
    private String interfaceDeviceSerialNumber = "";
    private String issuerActionCode = "";
    private String issuerApplicationData = "";
    private String issuerAuthenticatedData = "";
    private String issuerScriptResult = "";
    private String issuerScriptTemplate1 = "";
    private String issuerScriptTemplate2 = "";
    //private String online = "";
    private String terminalCountryCode = "";
    private String terminalIdentificationVersionNumber = "";
    private String terminalType = "";
    private String terminalVerificationResult = "";
    private String terminalCapabilities = "";
    private String transactionCategory = "";
    private String transactionCurrencyCode = "";
    private String transactionDate = "";
    private String transactionSequenceNumber = "";
    private String transactionType = "";
    private String unpredictableNumber = "";
    private String bitmap = "";

    public GetIccDataBitmap(String iccdata) {
        this.iccdata = iccdata;
    }

    public void setBitmap() {
        this.bitmap = setBitmapStr(1);
    }

    public void processData() {
        Tokenise tokenise = new Tokenise(iccdata);
        int i = 0;
        while (tokenise.hasNext("|")) {
            String data = tokenise.next("|");
            if (i == 0) {
                if (!data.isEmpty()) {
                    this.setCryptogram(data);
                }
            } else if (i == 1) {

                if (!data.isEmpty()) {
                    this.setCryptogramInformationData(data);
                }
            } else if (i == 2) {

                if (!data.isEmpty()) {
                    this.setIssuerApplicationData(data);
                }
            } else if (i == 3) {

                if (!data.isEmpty()) {
                    this.setUnpredictableNumber(data);
                }
            } else if (i == 4) {

                if (!data.isEmpty()) {
                    this.setApplicationTransactionCounter(data);
                }
            } else if (i == 5) {

                if (!data.isEmpty()) {
                    this.setTerminalVerificationResult(data);
                }
            } else if (i == 6) {

                if (!data.isEmpty()) {
                    this.setTransactionDate(data);
                }
            } else if (i == 7) {

                if (!data.isEmpty()) {
                    this.setTransactionType(data);
                }
            } else if (i == 8) {

                if (!data.isEmpty()) {
                    this.setAmountAuthorization(data);
                }

            } else if (i == 9) {

                if (!data.isEmpty()) {
                    this.setTransactionCurrencyCode(data);
                }
            } else if (i == 10) {

                if (!data.isEmpty()) {
                    //this.setApplicationTransactionCounter(data);
                }

            } else if (i == 11) {

                if (!data.isEmpty()) {
                    this.setApplicationInterchangeProfile(data);
                }
            } else if (i == 12) {
                if (!data.isEmpty()) {
                    this.setTerminalCountryCode(data);
                }
            } else if (i == 13) {
                if (!data.isEmpty()) {
                    this.setAmountOther(data);
                }
            } else if (i == 14) {
                if (!data.isEmpty()) {
                    this.setTerminalCapabilities(data);
                }
            } //field+="9F33"+"03"+iccData.substring(150,156);
            else if (i == 15) {

                if (!data.isEmpty()) {
                    this.setApplicationIdentication(data);
                }
            } //field+="4F"+"10"+iccData.substring(156,188);
            else if (i == 16) {

                if (!data.isEmpty()) {
                    this.setTerminalIdentificationVersionNumber(data);
                }
            } //field+="9F08"+"02"+iccData.substring(188,192);
            else if (i == 17) {
                if (!data.isEmpty()) {
                    // this.setCardAuthenticatorResultCode(data);
                }
            } //field+="9F34"+"03"+iccData.substring(192,198);
            else if (i == 18) {

                if (!data.isEmpty()) {
                    this.setTerminalType(data);
                }
            } //field+="9F35"+"01"+iccData.substring(198,200);
            else if (i == 19) {
                int len = data.length() / 2;
                if (!data.isEmpty()) {
                    this.setInterfaceDeviceSerialNumber(data);
                }

            } //field+="9F1E"+"08"+iccData.substring(200,216);
            else if (i == 20) {

                if (!data.isEmpty()) {
                    //this.setTransactionCategory(data);
                }

            } //field+="9F53"+"01"+iccData.substring(216,218);
            else if (i == 21) {
                if (!data.isEmpty()) {
                    //this.setDedicatedFileName(data);
                }
            } //field+="84"+"10"+iccData.substring(218,250);
            else if (i == 22) {
                if (!data.isEmpty()) {
                    this.setTerminalIdentificationVersionNumber(data);
                }
            } //field+="9F09"+"02"+iccData.substring(250,254);
            else if (i == 23) {
                if (!data.isEmpty()) {
                    //this.setTransactionSequenceNumber(data);
                }
            }
            else if (i == 24) {
                if (!data.isEmpty()) {
                    this.setCvmResult(data);
                }
            }
            i++;
        }
        setBitmap();
    }

    public String getAmountAuthorization() {
        return amountAuthorization;
    }

    public void setAmountAuthorization(String amountAuthorization) {
        setBitmap(2);
        this.amountAuthorization = (amountAuthorization);
    }

    public String getAmountOther() {
        return amountOther;
    }

    public void setAmountOther(String amountOther) {
        setBitmap(3);
        this.amountOther = amountOther;
    }

    public String getApplicationIdentication() {
        return applicationIdentication.substring(2);
    }

    public void setApplicationIdentication(String applicationIdentication) {
        setBitmap(4);
        this.applicationIdentication = String.format("%02d", applicationIdentication.length()) + applicationIdentication;
    }

    public String getApplicationInterchangeProfile() {
        return applicationInterchangeProfile;
    }

    public void setApplicationInterchangeProfile(String applicationInterchangeProfile) {
        setBitmap(5);
        this.applicationInterchangeProfile = applicationInterchangeProfile;
    }

    public String getApplicationTransactionCounter() {
        return applicationTransactionCounter;
    }

    public void setApplicationTransactionCounter(String applicationTransactionCounter) {
        setBitmap(6);
        //this.applicationTransactionCounter = "00" + applicationTransactionCounter;
        this.applicationTransactionCounter = applicationTransactionCounter;
    }

    public String getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(String cryptogram) {
        setBitmap(12);
        this.cryptogram = cryptogram;
    }

    public void setCryptogramInformationData(String cryptogramInformationData) {
        setBitmap(13);
        this.cryptogramInformationData = cryptogramInformationData;
    }
    
    public String getCvmResult() {
        return cvmResult;
    }
    
    public void setCvmResult(String cvmResult) {
        setBitmap(15);
        this.cvmResult = cvmResult;
    }

    public String getInterfaceDeviceSerialNumber() {
        return interfaceDeviceSerialNumber;
    }

    public void setInterfaceDeviceSerialNumber(String interfaceDeviceSerialNumber) {
        setBitmap(16);
        this.interfaceDeviceSerialNumber = TripleDES.toBCDStr(interfaceDeviceSerialNumber);
    }

    public String getIssuerApplicationData() {
        return issuerApplicationData;
    }

    public void setIssuerApplicationData(String issuerApplicationData) {
        setBitmap(18);
        this.issuerApplicationData = String.format("%02d", issuerApplicationData.length()) + issuerApplicationData;
    }

    public String getIssuerScriptResult() {
        return issuerScriptResult;
    }

    public void setIssuerScriptResult(String issuerScriptResult) {
        setBitmap(19);
        this.issuerScriptResult = String.format("%04d", issuerScriptResult.length()) + issuerScriptResult;
    }

    public String getIssuerScriptTemplate1() {
        return issuerScriptTemplate1;
    }

    public void setIssuerScriptTemplate1(String issuerScriptTemplate1) {
        setBitmap(32);
        this.issuerScriptTemplate1 = String.format("%04d", issuerScriptTemplate1.length()) + issuerScriptTemplate1;
    }

    public String getIssuerScriptTemplate2() {
        return issuerScriptTemplate2;
    }

    public void setIssuerScriptTemplate2(String issuerScriptTemplate2) {
        setBitmap(33);
        this.issuerScriptTemplate2 = String.format("%04d", issuerScriptTemplate2.length()) + issuerScriptTemplate2;
    }

    public String getTerminalCapabilities() {
        return terminalCapabilities;
    }

    public void setTerminalCapabilities(String terminalCapabilities) {

        setBitmap(21);
        this.terminalCapabilities = terminalCapabilities;
    }

    public String getTerminalCountryCode() {
        return terminalCountryCode;
    }

    public void setTerminalCountryCode(String terminalCountryCode) {
        setBitmap(22);
        this.terminalCountryCode = terminalCountryCode.substring(1);
    }

    public String getTerminalIdentificationVersionNumber() {
        return terminalIdentificationVersionNumber;
    }

    public void setTerminalIdentificationVersionNumber(String terminalIdentificationVersionNumber) {
        setBitmap(20);
        this.terminalIdentificationVersionNumber = terminalIdentificationVersionNumber.length() > 4 ? terminalIdentificationVersionNumber.substring(0, 4) : terminalIdentificationVersionNumber;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {

        setBitmap(23);
        this.terminalType = terminalType;
    }

    public String getTerminalVerificationResult() {
        return terminalVerificationResult;
    }

    public void setTerminalVerificationResult(String terminalVerificationResult) {

        setBitmap(24);
        this.terminalVerificationResult = terminalVerificationResult;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        setBitmap(25);
        this.transactionCategory = TripleDES.toBCDStr(transactionCategory);
    }

    public String getTransactionCurrencyCode() {
        return transactionCurrencyCode;
    }

    public void setTransactionCurrencyCode(String transactionCurrencyCode) {
        setBitmap(26);
        this.transactionCurrencyCode = transactionCurrencyCode.substring(1);
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        setBitmap(27);
        this.transactionDate = transactionDate;
    }

    public String getTransactionSequenceNumber() {
        return transactionSequenceNumber.substring(1);
    }

    public void setTransactionSequenceNumber(String transactionSequenceNumber) {
        setBitmap(28);
        //transactionSequenceNumber = String.format("%d", Long.parseLong(transactionSequenceNumber, 16));
        this.transactionSequenceNumber = transactionSequenceNumber;//String.format("%d", transactionSequenceNumber.length() % 10) + transactionSequenceNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        setBitmap(29);
        this.transactionType = transactionType;
    }

    public String getUnpredictableNumber() {
        return unpredictableNumber;
    }

    public void setUnpredictableNumber(String unpredictableNumber) {
        setBitmap(30);
        this.unpredictableNumber = unpredictableNumber;
    }

    @Override
    public String toString() {
        return bitmap + amountAuthorization + amountOther + applicationIdentication
                + applicationInterchangeProfile + applicationTransactionCounter + applicationUsageControl + authorisationResponseCode
                + cardAuthenticatorReliabilityIndicator + cardAuthenticatorResultCode + chipConditionCode + cryptogram + cryptogramInformationData + cvmResult
                + interfaceDeviceSerialNumber + issuerActionCode + issuerApplicationData
                + issuerScriptResult + terminalIdentificationVersionNumber + terminalCapabilities + terminalCountryCode
                + terminalType + terminalVerificationResult + transactionCategory + transactionCurrencyCode + transactionDate
                + transactionSequenceNumber + transactionType + unpredictableNumber + issuerAuthenticatedData + issuerScriptTemplate1 + issuerScriptTemplate2;
    }
}
