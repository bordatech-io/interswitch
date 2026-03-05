/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.beans;

/**
 *
 *
 */
public class ISO8583 extends ISO8583subfield {

    private String mti = "";
    private String bitmap = "";
    private String pan = "";                            //2 var
    private String processingCodes = "";                //3 fixed
    private String amountTransaction = "";              //4fixed
    private String amountSettlement = "";               //5 fixed
    private String transmissionDateTime = "";           //10 M fixed
    private String conversionRate = "";	                //9 O fixed
    private String systemTraceAudit = "";               //11 M fixed 
    private String timeLocalTransaction = "";	        //12 M fixed
    private String dateLocalTransaction = "";	        //13 M fixed
    private String dateExpiration = "";                 //14 C fixed
    private String dateSettlement = "";                 //15 C fixed
    private String dateConversion = "";                 //16 O fixed
    private String merchantType = "";                   //18 O fixed
    private String posEntryMode = "";                   //22 M fixed
    private String cardSequenceNumber = "";             //23 C fixed
    private String posConditionCode = "";               //25 
    private String posPINCaptureCode = "";		//26
    private String authorizationIdResponseLength = "";  //27
    private String amountTransactionFee = "";		//28
    private String amountSettlementFee = "";		//29
    private String amountTransactionProcessingFee = "";	//30
    private String amountSettlementProcessingFee = "";  //31
    private String acquiringInstitutionIdCode = "";     //32  var
    private String forwardingInstitutionIdCode = "";    //33 var
    private String track2Data = "";		        //35
    private String track3Data = "";			//36
    private String retrievalRefrenceNumber = "";        //37
    private String authorizationIdResponse = "";	//38
    private String responseCode = "";			//39
    private String serviceRestrictionCode = "";	        //40
    private String cardAcceptorTerminalId = "";		//41
    private String cardAccepttorIdCode = "";            //42
    private String cardAcceptorIdLocation = "";         //43
    private String additionalResponseData = "";         //44
    private String track1Data = "";			//45
    private String currencyCodeTransaction = "";	//49
    private String additionalData = "";                 //48
    private String currencyCodeSettlement = "";		//50
    private String PINData = "";			//52
    private String securityRelatedControlCode = "";     //53
    private String addtionalAmount = "";		//54
    private String messageReasonCode = "";		//56
    private String authorizationLifeCycle = "";         //57
    private String authorizationAgentInstitution = "";	//58
    private String echoData = "";			//59
    private String adviceCode = "";			//60
    private String extTranAttr = "";                    //62
    private String SettlementCode = "";			//66
    private String extendedPaymentCode = "";		//67
    private String networkInformationManagementCode = "";	//70
    private String dateAction = "";				//73
    private String creditNumber = "";				//74
    private String creditReversalNumber = "";			//75
    private String debitNumber = "";				//76
    private String debitReversalNumber = "";			//77
    private String transferNumber = "";				//78
    private String transferReferenceNumber = "";		//79
    private String inquiriesNumber = "";			//80
    private String authorizationNumber = "";			//81
    private String creditProcessingFeeAmount = "";		//82
    private String creditTransactionFeeAmount = "";		//83
    private String debitProcessingFeeAmount = "";		//84
    private String debitTransactionFeeAmount = "";		//85
    private String creditAmount = "";				//86
    private String creditReversalAmount = "";			//87
    private String debitAmount = "";				//88
    private String debitReversalAmount = "";			//89
    private String originalDataElement = "";			//90
    private String fileUpdateCode = "";                         //91
    private String replacementAmount = "";
    private String amountNetSettlement = "";			//97
    private String payee = "";					//98
    private String receivingInstitutionIdCode = "";             //100
    private String fileName = "";				//101
    private String accountIdNumber1 = "";			//102
    private String accountIdNumber2 = "";			//103
    private String paymentNumber = "";			        //118
    private String paymentReversalNumber = "";			//119
    private String posDataCode = "";				//123
    private String networkManagementInformation = "";           //125
    private String ISO8583subfield = "";                        //127
    private String macExtended = "";                            //128

    ISOLogger lg = new ISOLogger("");

    public ISO8583(String mti) {
        super();
        this.mti = mti;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(boolean extended) {
        if (!extended) {
            this.bitmap = super.setBitmapStr(1);
        } else {
            this.bitmap = super.setBitmapStr(2);
        }

    }

    public String getMti() {
        return mti;
    }

    public void setMti(String mti) {

        this.mti = mti;
    }

    public void setSecondaryBitmap() {
        super.setBitmap(1);
    }

    public String getISO8583subfield() {

        return ISO8583subfield;
    }

    public void setISO8583subfield(String ISO8583subfield) {
       
        lg.generateLog(999999, 127, 1, ISO8583subfield.length(), ISO8583subfield, 's');
        super.setBitmap(127);
        this.ISO8583subfield = ISO8583subfield;
        
        /*lg.generateLog(999999, 127, 1, ISO8583subfield.substring(18).length(), ISO8583subfield.substring(18), 's');
        super.setBitmap(127);
        this.ISO8583subfield = ISO8583subfield.substring(18);*/
    }

    public String getPINData() {

        return PINData;
    }

    public void setPINData(String PINData) {

        lg.generateLog(8, 52, 1, PINData.length(), PINData, 'b');

        super.setBitmap(52);
        this.PINData = PINData;
    }

    public String getAccountIdNumber1() {
        return accountIdNumber1;
    }

    public void setAccountIdNumber1(String accountIdNumber1) {

        lg.generateLog(28, 102, 0, accountIdNumber1.length(), accountIdNumber1, 's');

        super.setBitmap(102);
        this.accountIdNumber1 = accountIdNumber1;
    }

    public String getAccountIdNumber2() {
        return accountIdNumber2;
    }

    public void setAccountIdNumber2(String accountIdNumber2) {

        lg.generateLog(28, 103, 0, accountIdNumber2.length(), accountIdNumber2, 's');

        super.setBitmap(103);
        this.accountIdNumber2 = accountIdNumber2;
    }

    public String getAcquiringInstitutionIdCode() {
        return acquiringInstitutionIdCode;
    }

    public void setAcquiringInstitutionIdCode(String acquiringInstitutionIdCode) {

        lg.generateLog(11, 32, 0, acquiringInstitutionIdCode.length(), acquiringInstitutionIdCode, 'n');

        this.acquiringInstitutionIdCode = acquiringInstitutionIdCode;
        super.setBitmap(32);
    }

    public String getAdditionalAmount() {
        return addtionalAmount;
    }

    public void setAdditionalAmount(String addtionalAmount) {

        lg.generateLog(120, 54, 0, addtionalAmount.length(), addtionalAmount, 'p');

        super.setBitmap(54);
        this.addtionalAmount = addtionalAmount;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        lg.generateLog(999, 48, 0, additionalData.length(), additionalData, 's');
        super.setBitmap(48);
        this.additionalData = additionalData;
    }

    public String getAdditionalResponseData() {
        return additionalResponseData;
    }

    public void setAdditionalResponseData(String additionalResponseData) {

        lg.generateLog(25, 44, 0, additionalResponseData.length(), additionalResponseData, 'n');

        super.setBitmap(44);
        this.additionalResponseData = additionalResponseData;
    }

    public String getAmountSettlement() {

        return amountSettlement;
    }

    public void setAmountSettlement(String amountSettlement) {

        lg.generateLog(12, 5, 1, amountSettlement.length(), amountSettlement, 'n');

        super.setBitmap(5);
        this.amountSettlement = amountSettlement;
    }

    public String getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(String amountTransaction) {

        lg.generateLog(12, 4, 1, amountTransaction.length(), amountTransaction, 'n');
        super.setBitmap(4);
        this.amountTransaction = amountTransaction;
    }

    public String getAmountNetSettlement() {

        return amountNetSettlement;
    }

    public void setAmountNetSettlement(String amountNetSettlement) {

        lg.generateLog(17, 97, 1, amountNetSettlement.length(), amountNetSettlement, 'x');
        super.setBitmap(97);
        this.amountNetSettlement = amountNetSettlement;
    }

    public String getAmountSettlementFee() {
        return amountSettlementFee;
    }

    public void setAmountSettlementFee(String amountSettlementFee) {

        lg.generateLog(9, 29, 1, amountSettlementFee.length(), amountSettlementFee, 'x');

        super.setBitmap(29);
        this.amountSettlementFee = amountSettlementFee;
    }

    public String getAmountSettlementProcessingFee() {
        return amountSettlementProcessingFee;
    }

    public void setAmountSettlementProcessingFee(String amountSettlementProcessingFee) {

        lg.generateLog(9, 31, 1, amountSettlementProcessingFee.length(), amountSettlementProcessingFee, 'x');

        super.setBitmap(31);
        this.amountSettlementProcessingFee = amountSettlementProcessingFee;
    }

    public String getAmountTransactionProcessingFee() {
        return amountTransactionProcessingFee;
    }

    public void setAmountTransactionProcessingFee(String amountTransactionactionProcessingFee) {

        lg.generateLog(9, 30, 1, amountTransactionactionProcessingFee.length(), amountTransactionactionProcessingFee, 'x');

        super.setBitmap(30);
        this.amountTransactionProcessingFee = amountTransactionactionProcessingFee;
    }

    public String getAmountTransactionFee() {
        return amountTransactionFee;
    }

    public void setAmountTransactionFee(String amountTransactionFee) {

        lg.generateLog(9, 28, 1, amountTransactionFee.length(), amountTransactionFee, 'x');
        super.setBitmap(28);
        this.amountTransactionFee = amountTransactionFee;
    }

    public String getAuthorizationAgentInstitution() {
        return authorizationAgentInstitution;
    }

    public void setAuthorizationAgentInstitution(String authorizationAgentInstitution) {

        lg.generateLog(11, 58, 0, authorizationAgentInstitution.length(), authorizationAgentInstitution, 'n');
        super.setBitmap(58);
        this.authorizationAgentInstitution = authorizationAgentInstitution;
    }

    public String getAuthorizationLifeCycle() {
        return authorizationLifeCycle;
    }

    public void setAuthorizationLifeCycle(String authorizationLifeCycle) {

        lg.generateLog(3, 57, 0, authorizationLifeCycle.length(), authorizationLifeCycle, 's');
        super.setBitmap(57);
        this.authorizationLifeCycle = authorizationLifeCycle;
    }

    public String getAuthorizationIdResponseLength() {
        return authorizationIdResponseLength;
    }

    public void setAuthorizationIdResponseLength(String authorizationIdResponseLength) {

        lg.generateLog(1, 27, 1, authorizationIdResponseLength.length(), authorizationIdResponseLength, 'n');

        super.setBitmap(27);
        this.authorizationIdResponseLength = authorizationIdResponseLength;
    }

    public String getAuthorizationIdResponse() {
        return authorizationIdResponse;
    }

    public void setAuthorizationIdResponse(String authorizationIdResponse) {

        lg.generateLog(6, 38, 1, authorizationIdResponse.length(), authorizationIdResponse, 'p');
        super.setBitmap(38);
        this.authorizationIdResponse = authorizationIdResponse;
    }

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public void setAuthorizationNumber(String authorizationNumber) {

        lg.generateLog(10, 81, 1, authorizationNumber.length(), authorizationNumber, 'n');
        super.setBitmap(81);
        this.authorizationNumber = authorizationNumber;
    }

    public String getCardAcceptorIdCode() {
        return cardAccepttorIdCode;
    }

    public void setCardAcceptorIdCode(String cardAccepttorIdCode) {

        lg.generateLog(15, 42, 1, cardAccepttorIdCode.length(), cardAccepttorIdCode, 's');
        super.setBitmap(42);
        this.cardAccepttorIdCode = cardAccepttorIdCode;
    }

    public String getCardAcceptorIdLocation() {
        return cardAcceptorIdLocation;
    }

    public void setCardAcceptorIdLocation(String cardAcceptorIdLocation) {

        lg.generateLog(40, 43, 1, cardAcceptorIdLocation.length(), cardAcceptorIdLocation, 's');
        super.setBitmap(43);
        this.cardAcceptorIdLocation = cardAcceptorIdLocation;
    }

    public String getCardAcceptorTerminalId() {
        return cardAcceptorTerminalId;
    }

    public void setCardAcceptorTerminalId(String cardAcceptorTerminalId) {

        lg.generateLog(8, 41, 1, cardAcceptorTerminalId.length(), cardAcceptorTerminalId, 's');
        super.setBitmap(41);
        this.cardAcceptorTerminalId = cardAcceptorTerminalId;
    }

    public String getCardSequenceNumber() {
        return cardSequenceNumber;
    }

    public void setCardSequenceNumber(String cardSequenceNumber) {

        lg.generateLog(3, 23, 1, cardSequenceNumber.length(), cardSequenceNumber, 'n');
        super.setBitmap(23);
        this.cardSequenceNumber = cardSequenceNumber;
    }

    public String getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(String conversionRate) {

        lg.generateLog(8, 9, 1, conversionRate.length(), conversionRate, 'n');
        super.setBitmap(9);
        this.conversionRate = conversionRate;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {

        lg.generateLog(16, 86, 1, creditAmount.length(), creditAmount, 'n');
        super.setBitmap(86);
        this.creditAmount = creditAmount;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {

        lg.generateLog(10, 74, 1, creditNumber.length(), creditNumber, 'n');
        super.setBitmap(74);
        this.creditNumber = creditNumber;
    }

    public String getCreditProcessingFeeAmount() {
        return creditProcessingFeeAmount;
    }

    public void setCreditProcessingFeeAmount(String creditProcessingFeeAmount) {

        lg.generateLog(12, 82, 1, creditProcessingFeeAmount.length(), creditProcessingFeeAmount, 'n');
        super.setBitmap(82);
        this.creditProcessingFeeAmount = creditProcessingFeeAmount;
    }

    public String getCreditReversalAmount() {
        return creditReversalAmount;
    }

    public void setCreditReversalAmount(String creditReversalAmount) {

        lg.generateLog(16, 87, 1, creditReversalAmount.length(), creditReversalAmount, 'n');
        super.setBitmap(87);
        this.creditReversalAmount = creditReversalAmount;
    }

    public String getCreditReversalNumber() {
        return creditReversalNumber;
    }

    public void setCreditReversalNumber(String creditReversalNumber) {

        lg.generateLog(10, 75, 1, creditReversalNumber.length(), creditReversalNumber, 'n');
        super.setBitmap(75);
        this.creditReversalNumber = creditReversalNumber;
    }

    public String getCreditTransactionFeeAmount() {
        return creditTransactionFeeAmount;
    }

    public void setCreditTransactionFeeAmount(String creditTransactionFeeAmount) {

        lg.generateLog(12, 83, 1, creditTransactionFeeAmount.length(), creditTransactionFeeAmount, 'n');
        super.setBitmap(83);
        this.creditTransactionFeeAmount = creditTransactionFeeAmount;
    }

    public String getCurrencyCodeSettlement() {
        return currencyCodeSettlement;
    }

    public void setCurrencyCodeSettlement(String currencyCodeSettlement) {

        lg.generateLog(3, 50, 1, currencyCodeSettlement.length(), currencyCodeSettlement, 'n');
        super.setBitmap(50);
        this.currencyCodeSettlement = currencyCodeSettlement;
    }

    public String getCurrencyCodeTransaction() {
        return currencyCodeTransaction;
    }

    public void setCurrencyCodeTransaction(String currencyCodeTransaction) {

        lg.generateLog(3, 49, 1, currencyCodeTransaction.length(), currencyCodeTransaction, 'n');
        super.setBitmap(49);
        this.currencyCodeTransaction = currencyCodeTransaction;
    }

    public String getDateAction() {
        return dateAction;
    }

    public void setDateAction(String dateAction) {

        lg.generateLog(6, 73, 1, dateAction.length(), dateAction, 'n');
        super.setBitmap(73);
        this.dateAction = dateAction;
    }

    public String getDateConversion() {
        return dateConversion;
    }

    public void setDateConversion(String dateConversion) {

        lg.generateLog(4, 16, 1, dateConversion.length(), dateConversion, 'n');
        super.setBitmap(16);
        this.dateConversion = dateConversion;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {

        lg.generateLog(4, 14, 1, dateExpiration.length(), dateExpiration, 'n');
        super.setBitmap(14);
        this.dateExpiration = dateExpiration;
    }

    public String getDateLocalTransaction() {
        return dateLocalTransaction;
    }

    public void setDateLocalTransaction(String dateLocalTransaction) {

        lg.generateLog(4, 13, 1, dateLocalTransaction.length(), dateLocalTransaction, 'n');

        super.setBitmap(13);
        this.dateLocalTransaction = dateLocalTransaction;
    }

    public String getDateSettlement() {
        return dateSettlement;
    }

    public void setDateSettlement(String dateSettlement) {

        lg.generateLog(4, 15, 1, dateSettlement.length(), dateSettlement, 'n');
        super.setBitmap(15);
        this.dateSettlement = dateSettlement;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {

        lg.generateLog(16, 88, 1, debitAmount.length(), debitAmount, 'n');
        super.setBitmap(88);
        this.debitAmount = debitAmount;
    }

    public String getDebitNumber() {
        return debitNumber;
    }

    public void setDebitNumber(String debitNumber) {

        lg.generateLog(10, 76, 1, debitNumber.length(), debitNumber, 'n');
        super.setBitmap(76);
        this.debitNumber = debitNumber;
    }

    public String getDebitProcessingFeeAmount() {
        return debitProcessingFeeAmount;
    }

    public void setDebitProcessingFeeAmount(String debitProcessingFeeAmount) {

        lg.generateLog(12, 84, 1, debitProcessingFeeAmount.length(), debitProcessingFeeAmount, 'n');
        super.setBitmap(84);
        this.debitProcessingFeeAmount = debitProcessingFeeAmount;
    }

    public String getDebitReversalAmount() {
        return debitReversalAmount;
    }

    public void setDebitReversalAmount(String debitReversalAmount) {

        lg.generateLog(16, 89, 1, debitReversalAmount.length(), debitReversalAmount, 'n');
        super.setBitmap(89);
        this.debitReversalAmount = debitReversalAmount;
    }

    public String getDebitReversalNumber() {
        return debitReversalNumber;
    }

    public void setDebitReversalNumber(String debitReversalNumber) {

        lg.generateLog(10, 77, 1, debitReversalNumber.length(), debitReversalNumber, 'n');
        super.setBitmap(77);
        this.debitReversalNumber = debitReversalNumber;
    }

    public String getDebitTransactionFeeAmount() {
        return debitTransactionFeeAmount;
    }

    public void setDebitTransactionFeeAmount(String debitTransactionFeeAmount) {

        lg.generateLog(12, 85, 1, debitTransactionFeeAmount.length(), debitTransactionFeeAmount, 'n');
        super.setBitmap(85);
        this.debitTransactionFeeAmount = debitTransactionFeeAmount;
    }

    public String getEchoData() {
        return echoData;
    }

    public void setEchoData(String echoData) {

        lg.generateLog(255, 59, 0, echoData.length(), echoData, 's');
        super.setBitmap(59);
        this.echoData = echoData;
    }
    
    public String getExtTranAttrData() {
        return extTranAttr;
    }

    public void setExtTranAttrData(String extTranAttr) {

        lg.generateLog(999, 62, 0, extTranAttr.length(), extTranAttr, 's');
        super.setBitmap(62);
        this.extTranAttr = extTranAttr;
    }

    public String getExtendedPaymentCode() {
        return extendedPaymentCode;
    }

    public void setExtendedPaymentCode(String extendedPaymentCode) {

        lg.generateLog(2, 67, 1, extendedPaymentCode.length(), extendedPaymentCode, 'n');
        super.setBitmap(67);
        this.extendedPaymentCode = extendedPaymentCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {

        lg.generateLog(17, 101, 0, fileName.length(), fileName, 's');
        super.setBitmap(101);
        this.fileName = fileName;
    }

    public String getFileUpdateCode() {
        return fileUpdateCode;
    }

    public void setFileUpdateCode(String fileUpdateCode) {

        lg.generateLog(1, 91, 1, fileUpdateCode.length(), fileUpdateCode, 'p');
        super.setBitmap(91);
        this.fileUpdateCode = fileUpdateCode;
    }

    public String getForwardingInstitutionIdCode() {

        return forwardingInstitutionIdCode;
    }

    public void setForwardingInstitutionIdCode(String forwardingInstitutionIdCode) {

        lg.generateLog(11, 33, 0, forwardingInstitutionIdCode.length(), forwardingInstitutionIdCode, 'p');
        super.setBitmap(33);
        this.forwardingInstitutionIdCode = forwardingInstitutionIdCode;
    }

    public String getInquiriesNumber() {
        return inquiriesNumber;
    }

    public void setInquiriesNumber(String inquiriesNumber) {

        lg.generateLog(10, 80, 1, inquiriesNumber.length(), inquiriesNumber, 'p');
        super.setBitmap(80);
        this.inquiriesNumber = inquiriesNumber;
    }

    public String getMacExtended() {
        return macExtended;
    }

    public void setMacExtended(String macExtended) {

        lg.generateLog(8, 128, 1, macExtended.length(), macExtended, 'b');
        super.setBitmap(128);
        this.macExtended = macExtended;
    }

    public String getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(String merchantType) {

        lg.generateLog(4, 18, 1, merchantType.length(), merchantType, 'n');
        super.setBitmap(18);
        this.merchantType = merchantType;
    }

    public String getMessageReasonCode() {
        return messageReasonCode;
    }

    public void setMessageReasonCode(String messageReasonCode) {

        lg.generateLog(4, 56, 1, messageReasonCode.length(), messageReasonCode, 'n');
        super.setBitmap(56);
        this.messageReasonCode = messageReasonCode;
    }

    public String getNetworkInformationManagementCode() {
        return networkInformationManagementCode;
    }

    public void setNetworkInformationManagementCode(String networkInformationManagementCode) {

        lg.generateLog(3, 70, 1, networkInformationManagementCode.length(), networkInformationManagementCode, 'n');
        super.setBitmap(70);
        this.networkInformationManagementCode = networkInformationManagementCode;
    }

    public String getNetworkManagementInformation() {

        return networkManagementInformation;
    }

    public void setNetworkManagementInformation(String networkManagementInformation) {
        lg.generateLog(40, 125, 2, networkManagementInformation.length(), networkManagementInformation, 's');
        super.setBitmap(125);

        this.networkManagementInformation = networkManagementInformation;
    }

    public String getOriginalDataElement() {
        return originalDataElement;
    }

    public void setOriginalDataElement(String originalDataElement) {

        lg.generateLog(42, 90, 1, originalDataElement.length(), originalDataElement, 'n');
        super.setBitmap(90);
        this.originalDataElement = originalDataElement;
    }

    public String getPan() {

        return pan;
    }

    public void setPan(String pan) {

        lg.generateLog(19, 2, 0, pan.length(), pan, 'n');
        super.setBitmap(2);
        this.pan = pan;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {

        lg.generateLog(25, 98, 1, payee.length(), payee, 's');
        super.setBitmap(98);
        this.payee = payee;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {

        lg.generateLog(10, 118, 1, paymentNumber.length(), paymentNumber, 'n');
        super.setBitmap(118);
        this.paymentNumber = paymentNumber;
    }

    public String getPaymentReversalNumber() {
        return paymentReversalNumber;
    }

    public void setPaymentReversalNumber(String paymentReversalNumber) {

        lg.generateLog(10, 119, 1, paymentReversalNumber.length(), paymentReversalNumber, 'n');
        super.setBitmap(119);
        this.paymentReversalNumber = paymentReversalNumber;
    }

    public String getPosConditionCode() {
        return posConditionCode;
    }

    public void setPosConditionCode(String posConditionCode) {

        lg.generateLog(2, 25, 1, posConditionCode.length(), posConditionCode, 'n');
        super.setBitmap(25);
        this.posConditionCode = posConditionCode;
    }

    public String getPosDataCode() {
        return posDataCode;
    }

    public void setPosDataCode(String posDataCode) {

        lg.generateLog(15, 123, 0, posDataCode.length(), posDataCode, 'p');
        super.setBitmap(123);
        this.posDataCode = posDataCode;
    }

    public String getPosEntryMode() {
        return posEntryMode;
    }

    public void setPosEntryMode(String posEntryMode) {

        lg.generateLog(3, 22, 1, posEntryMode.length(), posEntryMode, 'n');
        super.setBitmap(22);
        this.posEntryMode = posEntryMode;
    }

    public String getPosPINCaptureCode() {
        return posPINCaptureCode;
    }

    public void setPosPINCaptureCode(String posPINCaptureCode) {

        lg.generateLog(2, 26, 1, posPINCaptureCode.length(), posPINCaptureCode, 'n');
        super.setBitmap(26);
        this.posPINCaptureCode = posPINCaptureCode;
    }

    public String getProcessingCodes() {
        return processingCodes;
    }

    public void setProcessingCodes(String processingCodes) {

        lg.generateLog(6, 3, 1, processingCodes.length(), processingCodes, 'n');
        super.setBitmap(3);
        this.processingCodes = processingCodes;
    }

    public String getReceivingInstitutionIdCode() {
        return receivingInstitutionIdCode;
    }

    public void setReceivingInstitutionIdCode(String receivingInstitutionIdCode) {

        lg.generateLog(11, 100, 0, receivingInstitutionIdCode.length(), receivingInstitutionIdCode, 'n');
        super.setBitmap(100);
        this.receivingInstitutionIdCode = receivingInstitutionIdCode;
    }

    public String getReplacementAmount() {
        return replacementAmount;
    }

    public void setReplacementAmount(String replacementAmount) {

        lg.generateLog(42, 95, 1, replacementAmount.length(), replacementAmount, 's');
        super.setBitmap(95);
        this.replacementAmount = replacementAmount;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {

        lg.generateLog(2, 39, 1, responseCode.length(), responseCode, 'n');
        super.setBitmap(39);
        this.responseCode = responseCode;
    }

    public String getRetrievalRefrenceNumber() {
        return retrievalRefrenceNumber;
    }

    public void setRetrievalRefrenceNumber(String retrievalRefrenceNumber) {

        lg.generateLog(12, 37, 1, retrievalRefrenceNumber.length(), retrievalRefrenceNumber, 'p');
        super.setBitmap(37);
        this.retrievalRefrenceNumber = retrievalRefrenceNumber;
    }

    public String getSecurityRelatedControlCode() {
        return securityRelatedControlCode;
    }

    public void setSecurityRelatedControlCode(String securityRelatedControlCode) {

        lg.generateLog(48, 53, 1, securityRelatedControlCode.length(), securityRelatedControlCode, 'p');
        super.setBitmap(53);
        this.securityRelatedControlCode = securityRelatedControlCode;
    }

    public String getServiceRestrictionCode() {
        return serviceRestrictionCode;
    }

    public void setServiceRestrictionCode(String serviceRestrictionCode) {

        lg.generateLog(3, 40, 1, serviceRestrictionCode.length(), serviceRestrictionCode, 'p');
        super.setBitmap(40);
        this.serviceRestrictionCode = serviceRestrictionCode;
    }

    public String getSettlementCode() {
        return SettlementCode;
    }

    public void setSettlementCode(String SettlementCode) {

        lg.generateLog(1, 66, 1, SettlementCode.length(), SettlementCode, 'n');
        super.setBitmap(66);
        this.SettlementCode = SettlementCode;
    }

    public String getSystemTraceAudit() {
        return systemTraceAudit;
    }

    public void setSystemTraceAudit(String systemTraceAudit) {

        lg.generateLog(6, 11, 1, systemTraceAudit.length(), systemTraceAudit, 'n');
        super.setBitmap(11);
        this.systemTraceAudit = systemTraceAudit;
    }

    public String getTimeLocalTransaction() {
        return timeLocalTransaction;
    }

    public void setTimeLocalTransaction(String timeLocalTransaction) {

        lg.generateLog(6, 12, 1, timeLocalTransaction.length(), timeLocalTransaction, 'n');
        super.setBitmap(12);
        this.timeLocalTransaction = timeLocalTransaction;
    }

    public String getTrack1Data() {
        return track1Data;
    }

    public void setTrack1Data(String track1Data) {

        lg.generateLog(76, 45, 0, track1Data.length(), track1Data, 's');
        super.setBitmap(45);
        this.track1Data = track1Data;
    }

    public String getTrack2Data() {
        return track2Data;
    }

    public void setTrack2Data(String track2Data) {

        lg.generateLog(37, 35, 0, track2Data.length(), track2Data, 's');
        super.setBitmap(35);
        this.track2Data = track2Data;
    }

    public String getTrack3Data() {
        return track3Data;
    }

    public void setTrack3Data(String track3Data) {
        super.setBitmap(36);
        this.track3Data = track3Data;
    }

    public String getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(String transferNumber) {

        lg.generateLog(10, 78, 1, transferNumber.length(), transferNumber, 'n');
        super.setBitmap(78);
        this.transferNumber = transferNumber;
    }

    public String getTransferReferenceNumber() {
        return transferReferenceNumber;
    }

    public void setTransferReferenceNumber(String transferReferenceNumber) {

        lg.generateLog(10, 79, 1, transferReferenceNumber.length(), transferReferenceNumber, 'n');
        super.setBitmap(79);
        this.transferReferenceNumber = transferReferenceNumber;
    }

    public String getTransmissionDateTime() {
        return transmissionDateTime;
    }

    public void setTransmissionDateTime(String transmissionDateTime) {

        lg.generateLog(10, 7, 1, transmissionDateTime.length(), transmissionDateTime, 'n');
        super.setBitmap(7);
        this.transmissionDateTime = transmissionDateTime;
    }

    public String toStringsb() {
        return super.toString();
    }

    @Override
    public String toString() {
        return mti + bitmap + pan + processingCodes + amountTransaction + amountSettlement + transmissionDateTime + conversionRate
                + systemTraceAudit + timeLocalTransaction + dateLocalTransaction + dateExpiration + dateSettlement
                + dateConversion + merchantType + posEntryMode + cardSequenceNumber + posConditionCode
                + posPINCaptureCode + authorizationIdResponseLength + amountTransactionFee + amountSettlementFee + amountTransactionProcessingFee
                + amountSettlementProcessingFee + acquiringInstitutionIdCode + forwardingInstitutionIdCode + track2Data
                + retrievalRefrenceNumber + authorizationIdResponse + responseCode + serviceRestrictionCode + cardAcceptorTerminalId
                + cardAccepttorIdCode + cardAcceptorIdLocation + additionalResponseData + track1Data + additionalData + currencyCodeTransaction
                + currencyCodeSettlement + PINData + securityRelatedControlCode + addtionalAmount + messageReasonCode + authorizationLifeCycle
                + authorizationAgentInstitution + echoData + adviceCode + extTranAttr + SettlementCode + extendedPaymentCode + networkInformationManagementCode + dateAction
                + creditNumber + creditReversalNumber + debitNumber + debitReversalNumber + transferNumber + transferReferenceNumber + inquiriesNumber + authorizationNumber
                + creditProcessingFeeAmount + creditTransactionFeeAmount + debitProcessingFeeAmount + debitTransactionFeeAmount + creditAmount
                + creditReversalAmount + debitAmount + debitReversalAmount + originalDataElement + fileUpdateCode + replacementAmount + amountNetSettlement + payee + receivingInstitutionIdCode + fileName + accountIdNumber1 + accountIdNumber2
                + paymentNumber + paymentReversalNumber + posDataCode + networkManagementInformation + ISO8583subfield + macExtended;
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
