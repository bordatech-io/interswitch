/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.gateway;

import com.bordatech.support.Tokenise;

import java.util.StringTokenizer;

/**
 *
 * @author SoloFoundation
 */
class GetIccDataXML {

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
    private String cvmList = "";
    private String defaults = "";
    private String dedicatedFileName = "";
    private String interfaceDeviceSerialNumber = "";
    private String issuerActionCode = "";
    private String issuerApplicationData = "";
    private String issuerAuthenticatedData = "";
    private String issuerScriptResult = "";
    private String issuerScriptTemplate1 = "";
    private String issuerScriptTemplate2 = "";
    private String online = "";
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

    public GetIccDataXML(String iccdata) {

        this.iccdata = iccdata;
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
                    //this.setTransactionSequenceNumber(data);
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
                    //this.setApplicationIdentication(data);
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
                    this.setCvmResult(data);
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
                    // this.setTransactionCategory(data);
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
                    this.setTransactionSequenceNumber(data);
                }
            }
            i++;
        }

    }

    public String getAmountAuthorization() {
        StringTokenizer tokeniser = new StringTokenizer(amountAuthorization, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setAmountAuthorization(String amountAuthorization) {
        this.amountAuthorization = "<AmountAuthorized>" + amountAuthorization + "</AmountAuthorized>";
    }

    public String getAmountOther() {
        StringTokenizer tokeniser = new StringTokenizer(amountOther, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setAmountOther(String amountOther) {
        this.amountOther = "<AmountOther>" + amountOther + "</AmountOther>";
    }

    public String getApplicationIdentication() {
        StringTokenizer tokeniser = new StringTokenizer(applicationIdentication, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setApplicationIdentication(String applicationIdentication) {
        this.applicationIdentication = "<ApplicationIdentifier>" + applicationIdentication + "</ApplicationIdentifier>";
    }

    public String getApplicationInterchangeProfile() {
        StringTokenizer tokeniser = new StringTokenizer(applicationInterchangeProfile, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setApplicationInterchangeProfile(String applicationInterchangeProfile) {
        this.applicationInterchangeProfile = "<ApplicationInterchangeProfile>" + applicationInterchangeProfile + "</ApplicationInterchangeProfile>";
    }

    public String getApplicationTransactionCounter() {
        StringTokenizer tokeniser = new StringTokenizer(applicationTransactionCounter, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setApplicationTransactionCounter(String applicationTransactionCounter) {
        this.applicationTransactionCounter = "<ApplicationTransactionCounter>" + applicationTransactionCounter + "</ApplicationTransactionCounter>";
    }

    public String getApplicationUsageControl() {
        StringTokenizer tokeniser = new StringTokenizer(applicationUsageControl, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setApplicationUsageControl(String applicationUsageControl) {
        this.applicationUsageControl = "<ApplicationUsageControl>" + applicationUsageControl + "</ApplicationUsageControl>";
    }

    public String getAuthorisationResponseCode() {
        StringTokenizer tokeniser = new StringTokenizer(authorisationResponseCode, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setAuthorisationResponseCode(String authorisationResponseCode) {
        this.authorisationResponseCode = "<AuthorizationResponseCode>" + authorisationResponseCode + "</AuthorizationResponseCode>";
    }

    public String getCardAuthenticatorReliabilityIndicator() {
        StringTokenizer tokeniser = new StringTokenizer(cardAuthenticatorReliabilityIndicator, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setCardAuthenticatorReliabilityIndicator(String cardAuthenticatorReliabilityIndicator) {
        this.cardAuthenticatorReliabilityIndicator = "<CardAuthenticationReliabilityIndicator>" + cardAuthenticatorReliabilityIndicator + "</CardAuthenticationReliabilityIndicator>";
    }

    public String getCardAuthenticatorResultCode() {
        StringTokenizer tokeniser = new StringTokenizer(cardAuthenticatorResultCode, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setCardAuthenticatorResultCode(String cardAuthenticatorResultCode) {
        this.cardAuthenticatorResultCode = "<CardAuthenticationResultsCode>" + cardAuthenticatorResultCode + "</CardAuthenticationResultsCode>";
    }

    public String getChipConditionCode() {
        StringTokenizer tokeniser = new StringTokenizer(chipConditionCode, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setChipConditionCode(String chipConditionCode) {
        this.chipConditionCode = "<ChipConditionCode>" + chipConditionCode + "</ChipConditionCode>";
    }

    public String getCryptogram() {
        StringTokenizer tokeniser = new StringTokenizer(cryptogram, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setCryptogram(String cryptogram) {
        this.cryptogram = "<Cryptogram>" + cryptogram + "</Cryptogram>";
    }

    public String getCryptogramAuthenticatedData() {
        StringTokenizer tokeniser = new StringTokenizer(cryptogramInformationData, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setCryptogramInformationData(String cryptogramInformationData) {
        this.cryptogramInformationData = "<CryptogramInformationData>" + cryptogramInformationData + "</CryptogramInformationData>";
    }

    public String getCvmResult() {
        StringTokenizer tokeniser = new StringTokenizer(cvmResult, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setCvmResult(String cvmResult) {
        this.cvmResult = "<CvmResults>" + cvmResult + "</CvmResults>";
    }

    public String getCvmList() {
        StringTokenizer tokeniser = new StringTokenizer(cvmList, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setCvmList(String cvmList) {
        this.cvmList = "<CvnList>" + cvmList + "</CvmList>";
    }

    public String getDefaults() {
        StringTokenizer tokeniser = new StringTokenizer(defaults, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setDefaults(String defaults) {
        this.defaults = "<Defaults>" + defaults + "</Defaults>";
    }

    public String getDedicatedFileName() {
        StringTokenizer tokeniser = new StringTokenizer(dedicatedFileName, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setDedicatedFileName(String dedicatedFileName) {
        this.dedicatedFileName = "<DedicatedFileName>" + dedicatedFileName + "</DedicatedFileName>";
    }

    public String getInterfaceDeviceSerialNumber() {
        StringTokenizer tokeniser = new StringTokenizer(interfaceDeviceSerialNumber, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setInterfaceDeviceSerialNumber(String interfaceDeviceSerialNumber) {
        this.interfaceDeviceSerialNumber = "<InterfaceDeviceSerialNumber>" + interfaceDeviceSerialNumber + "</InterfaceDeviceSerialNumber>";
    }

    public String getIssuerActionCode() {
        StringTokenizer tokeniser = new StringTokenizer(issuerActionCode, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setIssuerActionCode(String issuerActionCode) {
        this.issuerActionCode = "<IssuerActionCode>" + issuerActionCode + "</IssuerActionCode>";
    }

    public String getIssuerApplicationData() {
        StringTokenizer tokeniser = new StringTokenizer(issuerApplicationData, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setIssuerApplicationData(String issuerApplicationData) {
        this.issuerApplicationData = "<IssuerApplicationData>" + issuerApplicationData + "</IssuerApplicationData>";
    }

    public String getIssuerAuthenticatedData() {
        StringTokenizer tokeniser = new StringTokenizer(issuerAuthenticatedData, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setIssuerAuthenticatedData(String issuerAuthenticatedData) {
        this.issuerAuthenticatedData = "<IssuerAuthenticationData>" + issuerAuthenticatedData + "</IssuerAuthenticationData>";
    }

    public String getIssuerScriptResult() {
        StringTokenizer tokeniser = new StringTokenizer(issuerScriptResult, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setIssuerScriptResult(String issuerScriptResult) {
        this.issuerScriptResult = "<IssuerScriptResults>" + issuerScriptResult + "</IssuerScriptResults>";
    }

    public String getIssuerScriptTemplate1() {
        StringTokenizer tokeniser = new StringTokenizer(issuerScriptTemplate1, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setIssuerScriptTemplate1(String issuerScriptTemplate1) {
        this.issuerScriptTemplate1 = issuerScriptTemplate1;
    }

    public String getIssuerScriptTemplate2() {
        StringTokenizer tokeniser = new StringTokenizer(issuerScriptTemplate2, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setIssuerScriptTemplate2(String issuerScriptTemplate2) {
        this.issuerScriptTemplate2 = issuerScriptTemplate2;
    }

    public String getOnline() {
        StringTokenizer tokeniser = new StringTokenizer(online, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setOnline(String online) {
        this.online = "<Online>" + online + "</Online>";
    }

    public String getTerminalCapabilities() {
        StringTokenizer tokeniser = new StringTokenizer(terminalCapabilities, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTerminalCapabilities(String terminalCapabilities) {
        this.terminalCapabilities = "<TerminalCapabilities>" + terminalCapabilities + "</TerminalCapabilities>";
    }

    public String getTerminalCountryCode() {
        StringTokenizer tokeniser = new StringTokenizer(terminalCountryCode, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTerminalCountryCode(String terminalCountryCode) {
        this.terminalCountryCode = "<TerminalCountryCode>" + terminalCountryCode.substring(1) + "</TerminalCountryCode>";
    }

    public String getTerminalIdentificationVersionNumber() {
        StringTokenizer tokeniser = new StringTokenizer(terminalIdentificationVersionNumber, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTerminalIdentificationVersionNumber(String terminalIdentificationVersionNumber) {
        this.terminalIdentificationVersionNumber = terminalIdentificationVersionNumber;
    }

    public String getTerminalType() {
        StringTokenizer tokeniser = new StringTokenizer(terminalType, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = "<TerminalType>"+ terminalType +"</TerminalType>";
    }

    public String getTerminalVerificationResult() {
        StringTokenizer tokeniser = new StringTokenizer(terminalVerificationResult, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTerminalVerificationResult(String terminalVerificationResult) {
        this.terminalVerificationResult = "<TerminalVerificationResult>" + terminalVerificationResult + "</TerminalVerificationResult>";
    }

    public String getTransactionCategory() {
        StringTokenizer tokeniser = new StringTokenizer(transactionCategory, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = "<TransactionCategoryCode>" + transactionCategory + "</TransactionCategoryCode>";
    }

    public String getTransactionCurrencyCode() {
        StringTokenizer tokeniser = new StringTokenizer(transactionCurrencyCode, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTransactionCurrencyCode(String transactionCurrencyCode) {
        this.transactionCurrencyCode = "<TransactionCurrencyCode>" + transactionCurrencyCode.substring(1) + "</TransactionCurrencyCode>";
    }

    public String getTransactionDate() {
        StringTokenizer tokeniser = new StringTokenizer(transactionDate, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = "<TransactionDate>" + transactionDate + "</TransactionDate>";
    }

    public String getTransactionSequenceNumber() {
        StringTokenizer tokeniser = new StringTokenizer(transactionSequenceNumber, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTransactionSequenceNumber(String transactionSequenceNumber) {
        this.transactionSequenceNumber = "<TransactionSequenceCounter>" + transactionSequenceNumber + "</TransactionSequenceCounter>";
    }

    public String getTransactionType() {
        StringTokenizer tokeniser = new StringTokenizer(transactionType, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = "<TransactionType>" + transactionType + "</TransactionType>";
    }

    public String getUnpredictableNumber() {
        StringTokenizer tokeniser = new StringTokenizer(unpredictableNumber, "><");
        if (tokeniser.countTokens() > 1) {
            String value = tokeniser.nextToken();
            return tokeniser.nextToken();
        }
        return "";
    }

    public void setUnpredictableNumber(String unpredictableNumber) {
        this.unpredictableNumber = "<UnpredictableNumber>" + unpredictableNumber + "</UnpredictableNumber>";
    }

    @Override
    public String toString() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?><IccData><IccRequest>" + amountAuthorization + amountOther + applicationIdentication
                + applicationInterchangeProfile + applicationTransactionCounter + applicationUsageControl + authorisationResponseCode
                + cardAuthenticatorReliabilityIndicator + cardAuthenticatorResultCode + chipConditionCode + cryptogram + cryptogramInformationData
                + cvmResult + cvmList + defaults + interfaceDeviceSerialNumber + issuerActionCode + issuerApplicationData + issuerAuthenticatedData
                + issuerScriptResult + issuerScriptTemplate1 + issuerScriptTemplate2 + online + terminalCountryCode + terminalIdentificationVersionNumber
                + terminalType + terminalVerificationResult + terminalCapabilities + transactionCategory + transactionCurrencyCode + transactionDate
                + transactionSequenceNumber + transactionType + unpredictableNumber + "</IccRequest></IccData>";
    }
}
