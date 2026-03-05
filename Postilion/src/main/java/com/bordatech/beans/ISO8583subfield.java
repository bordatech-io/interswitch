/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.beans;

import java.io.Serializable;

/**
 *
 *
 */
public class ISO8583subfield extends Bitmap implements Serializable {

    private String secbitmap = "";
    private String switchKey = "";				//127.2
    private String routingInformation = "";                     //127.3
    private String posData = "";			        //127.4
    private String serviceStationData = "";		        //127.5
    private String authorizationProfile = "";		        //127.6
    private String checkData = "";				//127.7
    private String retentionData = "";				//127.8
    private String additionalNodeData = "";		        //127.9
    private String cvv2 = "";					//127.10
    private String originalKey = "";				//127.11
    private String terminalOwner = "";				//127.12
    private String posGeographicData = "";		        //127.13  fixed
    private String sponsorBank = "";				//127.14
    private String addressVerificationData1 = "";               //127.15
    private String addressVerificationResult = "";	        //127.16
    private String cardHolderInformation = "";		        //127.17
    private String validationData = "";			        //127.18
    private String bankDetails = "";				//127.19
    private String originatorDateSettlement = "";               //127.20
    private String recordIdentification = "";		        //127.21
    private String structureData = "";				//127.22
    private String payeeNameAddress = "";			//127.23
    private String payerAccount = "";				//127.24
    private String iccData = "";			        //127.25
    private String originalNode = "";			        //127.26
    private String cardVerificationResult = "";                 //127.27
    private String americanExpressCardId = "";		        //127.28 				
    private String secureData = "";                             //127.29
    private String secureResult = "";			   	//127.30
    private String issuerNetworkId = "";			//127.31		
    private String ucafData = "";			        //127.32
    private String extendedTransactionType = "";	        //127.33
    private String accountTypeQualifier = "";		        //127.34
    private String acquirerNetworkId = "";                      //127.35
    
    ISOLogger lg2 = new ISOLogger("127.");

    public ISO8583subfield() {
    }

    public String getBitmapsb() {
        return secbitmap;
    }

    public void setBitmapsb(boolean bitmap) {
        this.secbitmap = super.setBitmapsbStr(1);
    }

    public String getAccountTypeQualifier() {
        return accountTypeQualifier;
    }

    public void setAccountTypeQualifier(String accountTypeQualifier) {
        lg2.generateLog(2, 34, 1, accountTypeQualifier.length(), accountTypeQualifier, 'n');
        super.setBitmapsb(34);
        this.accountTypeQualifier = accountTypeQualifier;
    }

    public String getAcquirerNetworkId() {
        return acquirerNetworkId;
    }

    public void setAcquirerNetworkId(String acquirerNetworkId) {
        lg2.generateLog(11, 35, 0, acquirerNetworkId.length(), acquirerNetworkId, 's');
        super.setBitmapsb(35);
        this.acquirerNetworkId = acquirerNetworkId;
    }

    public String getAdditionalNodeData() {
        return additionalNodeData;
    }

    public void setAdditionalNodeData(String additionalNodeData) {
        lg2.generateLog(999, 9, 0, additionalNodeData.length(), additionalNodeData, 's');
        super.setBitmapsb(9);
        this.additionalNodeData = additionalNodeData;
    }

    public String getAddressVerificationData1() {
        return addressVerificationData1;
    }

    public void setAddressVerificationData1(String addressVerificationData1) {
        lg2.generateLog(29, 15, 0, addressVerificationData1.length(), addressVerificationData1, 's');
        super.setBitmapsb(15);
        this.addressVerificationData1 = addressVerificationData1;
    }

    public String getAddressVerificationResult() {
        return addressVerificationResult;
    }

    public void setAddressVerificationResult(String addressVerificationResult) {
        lg2.generateLog(1, 16, 1, addressVerificationResult.length(), addressVerificationResult, 's');
        super.setBitmapsb(16);
        this.addressVerificationResult = addressVerificationResult;
    }

    public String getAmericanExpressCardId() {
        return americanExpressCardId;
    }

    public void setAmericanExpressCardId(String americanExpressCardId) {
        lg2.generateLog(4, 28, 1, americanExpressCardId.length(), americanExpressCardId, 'n');
        super.setBitmapsb(28);
        this.americanExpressCardId = americanExpressCardId;
    }

    public String getAuthorizationProfile() {
        return authorizationProfile;
    }

    public void setAuthorizationProfile(String authorizationProfile) {
        lg2.generateLog(2, 6, 1, authorizationProfile.length(), authorizationProfile, 'p');
        super.setBitmapsb(6);
        this.authorizationProfile = authorizationProfile;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        lg2.generateLog(31, 19, 1, bankDetails.length(), bankDetails, 's');
        super.setBitmapsb(19);
        this.bankDetails = bankDetails;
    }

    public String getCardHolderInformation() {
        return cardHolderInformation;
    }

    public void setCardHolderInformation(String cardHolderInformation) {
        lg2.generateLog(50, 17, 0, cardHolderInformation.length(), cardHolderInformation, 's');
        super.setBitmapsb(17);
        this.cardHolderInformation = cardHolderInformation;
    }

    public String getCardVerificationResult() {
        return cardVerificationResult;
    }

    public void setCardVerificationResult(String cardVerificationResult) {
        lg2.generateLog(1, 27, 1, cardVerificationResult.length(), cardVerificationResult, 'a');
        super.setBitmapsb(27);
        this.cardVerificationResult = cardVerificationResult;
    }

    public String getCheckData() {
        return checkData;
    }

    public void setCheckData(String checkData) {
        lg2.generateLog(70, 7, 0, checkData.length(), checkData, 's');
        super.setBitmapsb(7);
        this.checkData = checkData;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        lg2.generateLog(3, 10, 1, cvv2.length(), cvv2, 'n');
        super.setBitmapsb(10);
        this.cvv2 = cvv2;
    }

    public String getExtendedTransactionType() {
        return extendedTransactionType;
    }

    public void setExtendedTransactionType(String extendedTransactionType) {
        lg2.generateLog(4, 33, 1, extendedTransactionType.length(), extendedTransactionType, 'n');
        super.setBitmapsb(33);
        this.extendedTransactionType = extendedTransactionType;
    }

    public String getIccData() {
        return iccData;
    }

    public void setIccData(String iccData) {
        lg2.generateLog(8000, 25, 0, iccData.length(), iccData, 's');
        super.setBitmapsb(25);
        this.iccData = iccData;
    }

    public String getIssuerNetworkId() {
        return issuerNetworkId;
    }

    public void setIssuerNetworkId(String issuerNetworkId) {
        lg2.generateLog(11, 31, 0, issuerNetworkId.length(), issuerNetworkId, 's');
        super.setBitmapsb(31);
        this.issuerNetworkId = issuerNetworkId;
    }

    public String getOriginalKey() {
        return originalKey;
    }

    public void setOriginalKey(String originalKey) {
        lg2.generateLog(32, 11, 0, originalKey.length(), originalKey, 's');
        super.setBitmapsb(11);
        this.originalKey = originalKey;
    }

    public String getOriginalNode() {
        return originalNode;
    }

    public void setOriginalNode(String originalNode) {
        lg2.generateLog(20, 26, 0, originalNode.length(), originalNode, 's');
        super.setBitmapsb(26);
        this.originalNode = originalNode;
    }

    public String getOriginatorDateSettlement() {
        return originatorDateSettlement;
    }

    public void setOriginatorDateSettlement(String originatorDateSettlement) {
        lg2.generateLog(8, 20, 1, originatorDateSettlement.length(), originatorDateSettlement, 'n');
        super.setBitmapsb(20);
        this.originatorDateSettlement = originatorDateSettlement;
    }

    public String getPayeeNameAddress() {
        return payeeNameAddress;
    }

    public void setPayeeNameAddress(String payeeNameAddress) {
        lg2.generateLog(253, 23, 1, payeeNameAddress.length(), payeeNameAddress, 'n');
        super.setBitmapsb(23);
        this.payeeNameAddress = payeeNameAddress;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        lg2.generateLog(28, 24, 0, payerAccount.length(), payerAccount, 's');
        super.setBitmapsb(24);
        this.payerAccount = payerAccount;
    }

    public String getPosData() {
        return posData;
    }

    public void setPosData(String posData) {
        lg2.generateLog(22, 4, 1, posData.length(), posData, 's');
        super.setBitmapsb(4);
        this.posData = posData;
    }

    public String getPosGeographicData() {
        return posGeographicData;
    }

    public void setPosGeographicData(String posGeographicData) {
        lg2.generateLog(17, 13, 1, posGeographicData.length(), posGeographicData, 's');
        super.setBitmapsb(13);
        this.posGeographicData = posGeographicData;
    }

    public String getStructureData() {
        return structureData;
    }

    public void setStructureData(String structureData1) {
        lg2.generateLog(99999, 22, 0, structureData1.length(), structureData1, 's');
        super.setBitmapsb(22);
        this.structureData = structureData1;
    }

    public String getRecordIdentification() {
        return recordIdentification;
    }

    public void setRecordIdentification(String recordIdentification) {
        lg2.generateLog(12, 21, 0, recordIdentification.length(), recordIdentification, 's');
        super.setBitmapsb(21);
        this.recordIdentification = recordIdentification;
    }

    public String getRoutingInformation() {
        return routingInformation;
    }

    public void setRoutingInformation(String routingInformation) {
        lg2.generateLog(48, 3, 1, routingInformation.length(), routingInformation, 's');
        super.setBitmapsb(3);
        this.routingInformation = routingInformation;
    }

    public String getRetentionData() {
        return retentionData;
    }

    public void setRetentionData(String retentionData) {
        lg2.generateLog(999, 8, 0, retentionData.length(), retentionData, 's');
        super.setBitmapsb(8);
        this.retentionData = retentionData;
    }

    public String getSecureData() {
        return secureData;
    }

    public void setSecureData(String secureData) {
        lg2.generateLog(40, 29, 1, secureData.length(), secureData, 'b');
        super.setBitmapsb(29);
        this.secureData = secureData;
    }

    public String getSecureResult() {
        return secureResult;
    }

    public void setSecureResult(String secureResult) {
        lg2.generateLog(1, 30, 1, secureResult.length(), secureResult, 's');
        super.setBitmapsb(30);
        this.secureResult = secureResult;
    }

    public String getServiceStationData() {
        return serviceStationData;
    }

    public void setServiceStationData(String serviceStationData) {
        lg2.generateLog(73, 5, 1, serviceStationData.length(), serviceStationData, 's');
        super.setBitmapsb(5);
        this.serviceStationData = serviceStationData;
    }

    public String getSponsorBank() {
        return sponsorBank;
    }

    public void setSponsorBank(String sponsorBank) {
        lg2.generateLog(8, 14, 1, sponsorBank.length(), sponsorBank, 's');
        super.setBitmapsb(14);
        this.sponsorBank = sponsorBank;
    }

    public String getSwitchKey() {
        return switchKey;
    }

    public void setSwitchKey(String switchKey) {
        lg2.generateLog(32, 2, 0, switchKey.length(), switchKey, 's');
        super.setBitmapsb(2);
        this.switchKey = switchKey;
    }

    public String getTerminalOwner() {
        return terminalOwner;
    }

    public void setTerminalOwner(String terminalOwner) {
        lg2.generateLog(25, 12, 0, terminalOwner.length(), terminalOwner, 's');
        super.setBitmapsb(12);
        this.terminalOwner = terminalOwner;
    }

    public String getUcafData() {
        return ucafData;
    }

    public void setUcafData(String ucafData) {
        lg2.generateLog(33, 32, 0, ucafData.length(), ucafData, 'b');
        super.setBitmapsb(32);
        this.ucafData = ucafData;
    }

    public String getValidationData() {
        return validationData;
    }

    public void setValidationData(String validationData) {
        lg2.generateLog(50, 18, 0, validationData.length(), validationData, 's');
        super.setBitmapsb(18);
        this.validationData = validationData;
    }

    @Override
    public String toString() {
        return secbitmap + switchKey + routingInformation + posData + serviceStationData + authorizationProfile + checkData + retentionData + additionalNodeData + cvv2 + originalKey + terminalOwner + posGeographicData + sponsorBank + addressVerificationData1 + addressVerificationResult + cardHolderInformation + validationData + bankDetails + originatorDateSettlement + recordIdentification + structureData + payeeNameAddress + payerAccount + iccData + originalNode + cardVerificationResult + americanExpressCardId + secureData + secureResult + issuerNetworkId + ucafData + extendedTransactionType + accountTypeQualifier + acquirerNetworkId;
    }
    
    public byte[] toBCD(String hexString) {
        byte bcd[] = new byte[hexString.length() / 2];
        String hex = new String("0123456789ABCDEF");
        for (int i = 0, j = 0; i < hexString.length(); i++, j++) {
            byte firstQuadByte = (byte) (hex.indexOf(hexString.toUpperCase().charAt(i)) << 4);
            byte secondQuadByte = (byte) (hex.indexOf(hexString.toUpperCase().charAt(++i)));
            bcd[j] = (byte) (firstQuadByte | secondQuadByte);
        }
        return bcd;
    }

    public String toAscii(String toAsciiData) {
        String hexString = "";
        char hexChars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        int i = 0;
        while (toAsciiData.length() > i) {

            int hbits = (toAsciiData.charAt(i) & 0x00f0) >>> 4;
            int lbits = (toAsciiData.charAt(i) & 0x000f);

            hexString += hexChars[hbits];

            hexString += hexChars[lbits];
            i++;
        }
        return hexString;
    }
    
    private static String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }

        return hex.toString();
    }
}

class StructureData //127.22
{

    private String mediaBatchNumber = "";
    private String mediaTotals = "";
    private String additonalInformation = "";

    public String getAdditonalInformation() {
        return additonalInformation;
    }

    public void setAdditonalInformation(String additonalInformation) {
        this.additonalInformation = additonalInformation;
    }

    public String getMediaBatchNumber() {
        return mediaBatchNumber;
    }

    public void setMediaBatchNumber(String mediaBatchNumber) {
        this.mediaBatchNumber = mediaBatchNumber;
    }

    public String getMediaTotals() {
        return mediaTotals;
    }

    public void setMediaTotals(String mediaTotals) {
        this.mediaTotals = mediaTotals;
    }

    @Override
    public String toString() {
        return mediaBatchNumber + mediaTotals + additonalInformation;
    }
}

/* To implement ICC data in xml format
 */
class IccData {

    public enum IccType {

        xmlTypeRequest, xmlTypeResponse, bitmapt
    };
    private String xmlType = "<?xml version=\"1.0\"?>";
    private String bitmap = "";
    private String amountAuthorization = "";
    private String amountOther = "";
    private String applicationIdentification = "";
    private String applicationInterchangeProfile = "";
    private String applicationTransactionCounter = "";
    private String applicationUsageControl = "";
    private String authorisationResponseCode = "";
    private String cardAuthorizationReliabilityIndicator = "";
    private String cardAuthorizationResultCode = "";
    private String chipConditionCode = "";
    private String cryptogram = "";
    private String cryptogramAuthorizationData = "";
    private String cvnList = "";
    private String cvmResult = "";
    private String interfaceDeviceSerialNumber = "";
    private String issuerActionCode = "";
    private String issuerApplicationData = "";
    private String defaults = "";
    private String denial = "";
    private String online = "";
    private String issuerScriptResult = "";
    private String terminalIdentificationVersionNumber = "";
    private String terminalCapabilities = "";
    private String terminalCountryCode = "";
    private String terminalType = "";
    private String terminalVerificationResult = "";
    private String transactionCategory = "";
    private String transactionCurrencyCode = "";
    private String transactionDate = "";
    private String transactionSequenceNumber = "";
    private String transactionType = "";
    private String unpredictableNumber = "";
    private String issuerAuthorizationData = "";
    private String issuerScriptTemplate1 = "";
    private String issuerScriptTemplate2 = "";
    IccType iccType;

    public void setIccType(IccType iccType) {
        this.iccType = iccType;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public String getAmountAuthorizationorization() {
        return amountAuthorization;
    }

    public void setAmountAuthorization(String amountAuthorization) {
        this.amountAuthorization = amountAuthorization;
    }

    public String getAmountOther() {
        return amountOther;
    }

    public void setAmountOther(String amountOther) {
        this.amountOther = amountOther;
    }

    public String getApplicationIdentification() {
        return applicationIdentification;
    }

    public void setApplicationIdentification(String applicationIdentification) {
        this.applicationIdentification = applicationIdentification;
    }

    public String getApplicationInterchangeProfile() {
        return applicationInterchangeProfile;
    }

    public void setApplicationInterchangeProfile(String applicationInterchangeProfile) {
        this.applicationInterchangeProfile = applicationInterchangeProfile;
    }

    public String getApplicationTransactionCounter() {
        return applicationTransactionCounter;
    }

    public void setApplicationTransactionCounter(String applicationTransactionCounter) {
        this.applicationTransactionCounter = applicationTransactionCounter;
    }

    public String getApplicationUsageControl() {
        return applicationUsageControl;
    }

    public void setApplicationUsageControl(String applicationUsageControl) {
        this.applicationUsageControl = applicationUsageControl;
    }

    public String getAuthorisationResponseCode() {
        return authorisationResponseCode;
    }

    public void setAuthorisationResponseCode(String authorisationResponseCode) {
        this.authorisationResponseCode = authorisationResponseCode;
    }

    public String getCardAuthorizationReliabilityIndicator() {
        return cardAuthorizationReliabilityIndicator;
    }

    public void setCardAuthorizationReliabilityIndicator(String cardAuthorizationReliabilityIndicator) {
        this.cardAuthorizationReliabilityIndicator = cardAuthorizationReliabilityIndicator;
    }

    public String getCardAuthorizationResultCode() {
        return cardAuthorizationResultCode;
    }

    public void setCardAuthorizationResultCode(String cardAuthorizationResultCode) {
        this.cardAuthorizationResultCode = cardAuthorizationResultCode;
    }

    public String getChipConditionCode() {
        return chipConditionCode;
    }

    public void setChipConditionCode(String chipConditionCode) {
        this.chipConditionCode = chipConditionCode;
    }

    public String getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(String cryptogram) {
        this.cryptogram = cryptogram;
    }

    public String getCryptogramAuthorizationData() {
        return cryptogramAuthorizationData;
    }

    public void setCryptogramAuthorizationData(String cryptogramAuthorizationData) {
        this.cryptogramAuthorizationData = cryptogramAuthorizationData;
    }

    public String getCvmResult() {
        return cvmResult;
    }

    public void setCvmResult(String cvmResult) {
        this.cvmResult = cvmResult;
    }

    public String getCvnList() {
        return cvnList;
    }

    public void setCvnList(String cvnList) {
        this.cvnList = cvnList;
    }

    public String getInterfaceDeviceSerialNumber() {
        return interfaceDeviceSerialNumber;
    }

    public void setInterfaceDeviceSerialNumber(String interfaceDeviceSerialNumber) {
        this.interfaceDeviceSerialNumber = interfaceDeviceSerialNumber;
    }

    public String getIssuerActionCode() {
        return issuerActionCode;
    }

    public void setIssuerActionCode(String issuerActionCode) {
        this.issuerActionCode = issuerActionCode;
    }

    public String getIssuerApplicationData() {
        return issuerApplicationData;
    }

    public void setIssuerApplicationData(String issuerApplicationData) {
        this.issuerApplicationData = issuerApplicationData;
    }

    public void setIssuerAuthorizationData(String issuerAuthorizationData) {
        this.issuerAuthorizationData = issuerAuthorizationData;
    }

    public String getIssuerScriptResult() {
        return issuerScriptResult;
    }

    public void setIssuerScriptResult(String issuerScriptResult) {
        this.issuerScriptResult = issuerScriptResult;
    }

    public String getIssuerScriptTemplate1() {
        return issuerScriptTemplate1;
    }

    public void setIssuerScriptTemplate1(String issuerScriptTemplate1) {
        this.issuerScriptTemplate1 = issuerScriptTemplate1;
    }

    public String getIssuerScriptTemplate2() {
        return issuerScriptTemplate2;
    }

    public void setIssuerScriptTemplate2(String issuerScriptTemplate2) {
        this.issuerScriptTemplate2 = issuerScriptTemplate2;
    }

    public String getTerminalCountryCode() {
        return terminalCountryCode;
    }

    public void setTerminalCountryCode(String terminalCountryCode) {
        this.terminalCountryCode = terminalCountryCode;
    }

    public String getTerminalIdentificationVersionNumber() {
        return terminalIdentificationVersionNumber;
    }

    public void setTerminalIdentificationVersionNumber(String terminalIdentificationVersionNumber) {
        this.terminalIdentificationVersionNumber = terminalIdentificationVersionNumber;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getTerminalVerificationResult() {
        return terminalVerificationResult;
    }

    public void setTerminalVerificationResult(String terminalVerificationResult) {
        this.terminalVerificationResult = terminalVerificationResult;
    }

    public String getTerminalCapabilities() {
        return terminalCapabilities;
    }

    public void setTerminalCapabilities(String terminalCapabilities) {
        this.terminalCapabilities = terminalCapabilities;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public String getTransactionCurrencyCode() {
        return transactionCurrencyCode;
    }

    public void setTransactionCurrencyCode(String transactionCurrencyCode) {
        this.transactionCurrencyCode = transactionCurrencyCode;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionSequenceNumber() {
        return transactionSequenceNumber;
    }

    public void setTransactionSequenceNumber(String transactionSequenceNumber) {
        this.transactionSequenceNumber = transactionSequenceNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getUnpredictableNumber() {
        return unpredictableNumber;
    }

    public void setUnpredictableNumber(String unpredictableNumber) {
        this.unpredictableNumber = unpredictableNumber;
    }

    public String getDefaults() {
        return defaults;
    }

    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

    public String getDenial() {
        return denial;
    }

    public void setDenial(String denial) {
        this.denial = denial;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    @Override
    public String toString() {
        String iccHead = "", iccTail = "";

        switch (this.iccType) {
            case xmlTypeRequest: {

                iccHead = "<?xml version=\"1.0\"?><IccData><IccRequest>";
                iccTail = "</IccRequest></IccData>";
                break;
            }
            case xmlTypeResponse: {

                iccHead = "<?xml version=\"1.0\"?><IccData><IccResponse>";
                iccTail = "</IccResponse></IccData>";
                break;
            }
            case bitmapt: {
                iccHead = this.bitmap;
                iccTail = "";
                break;
            }
        }//End of switch statement.

        return iccHead + amountAuthorization + amountOther + applicationIdentification + applicationInterchangeProfile + applicationTransactionCounter + applicationUsageControl + authorisationResponseCode + cardAuthorizationReliabilityIndicator + cardAuthorizationResultCode + chipConditionCode + cryptogram + cryptogramAuthorizationData + cvnList + cvmResult + interfaceDeviceSerialNumber + issuerActionCode + issuerApplicationData + issuerScriptResult + terminalIdentificationVersionNumber + terminalCapabilities + terminalCountryCode + terminalType + terminalVerificationResult + transactionCategory + transactionCurrencyCode + transactionDate + transactionSequenceNumber + transactionType + unpredictableNumber + issuerAuthorizationData + issuerScriptTemplate1 + issuerScriptTemplate2 + iccTail;
    }
}
