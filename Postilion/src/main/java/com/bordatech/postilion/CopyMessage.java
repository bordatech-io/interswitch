package com.bordatech.postilion;

import com.bordatech.beans.ISO8583;

public class CopyMessage {
    public ISO8583 copy(ISO8583 fromPostBridge , ISO8583 isoResMsg_){
        if (!fromPostBridge.getPan().isEmpty()) {
            isoResMsg_.setPan(getLength(fromPostBridge.getPan().length(),2)+fromPostBridge.getPan());
        }
        if (!fromPostBridge.getProcessingCodes().isEmpty()) {
            isoResMsg_.setProcessingCodes(fromPostBridge.getProcessingCodes());
        }
        if (!fromPostBridge.getAmountTransaction().isEmpty()) {
            isoResMsg_.setAmountTransaction(fromPostBridge.getAmountTransaction());
        }
        if (!fromPostBridge.getAmountSettlement().isEmpty()) {
            isoResMsg_.setAmountSettlement(fromPostBridge.getAmountSettlement());
        }
        if (!fromPostBridge.getTransmissionDateTime().isEmpty()) {
            isoResMsg_.setTransmissionDateTime(fromPostBridge.getTransmissionDateTime());
        }
        if (!fromPostBridge.getConversionRate().isEmpty()) {
            isoResMsg_.setConversionRate(fromPostBridge.getConversionRate());
        }

        if (!fromPostBridge.getEchoData().isEmpty()) {
            isoResMsg_.setEchoData(getLength(fromPostBridge.getEchoData().length(),3)+fromPostBridge.getEchoData());
        }
        if (!fromPostBridge.getMessageReasonCode().isEmpty()) {
            isoResMsg_.setMessageReasonCode("004"+fromPostBridge.getMessageReasonCode());
        }

        if (!fromPostBridge.getSystemTraceAudit().isEmpty()) {
            isoResMsg_.setSystemTraceAudit(fromPostBridge.getSystemTraceAudit());
        }
        if (!fromPostBridge.getTimeLocalTransaction().isEmpty()) {
            isoResMsg_.setTimeLocalTransaction(fromPostBridge.getTimeLocalTransaction());
        }
        if (!fromPostBridge.getDateLocalTransaction().isEmpty()) {
            isoResMsg_.setDateLocalTransaction(fromPostBridge.getDateLocalTransaction());
        }
        if (!fromPostBridge.getDateExpiration().isEmpty()) {
            isoResMsg_.setDateExpiration(fromPostBridge.getDateExpiration());
        }
        if (!fromPostBridge.getDateSettlement().isEmpty()) {
            isoResMsg_.setDateSettlement(fromPostBridge.getDateSettlement());
        }
        if (!fromPostBridge.getDateConversion().isEmpty()) {
            isoResMsg_.setDateConversion(fromPostBridge.getDateConversion());
        }
        if (!fromPostBridge.getMerchantType().isEmpty()) {
            isoResMsg_.setMerchantType(fromPostBridge.getMerchantType());
        }
        if (!fromPostBridge.getPosEntryMode().isEmpty()) {
            isoResMsg_.setPosEntryMode(fromPostBridge.getPosEntryMode());
        }
        if (!fromPostBridge.getCardSequenceNumber().isEmpty()) {
            isoResMsg_.setCardSequenceNumber(fromPostBridge.getCardSequenceNumber());
        }
        if (!fromPostBridge.getPosConditionCode().isEmpty()) {
            isoResMsg_.setPosConditionCode(fromPostBridge.getPosConditionCode());
        }
        if (!fromPostBridge.getPosPINCaptureCode().isEmpty()) {
            isoResMsg_.setPosPINCaptureCode(fromPostBridge.getPosPINCaptureCode());
        }
        if (!fromPostBridge.getCardAcceptorTerminalId().isEmpty()) {
            isoResMsg_.setCardAcceptorTerminalId(fromPostBridge.getCardAcceptorTerminalId());
        }
        if (!fromPostBridge.getCardAcceptorIdCode().isEmpty()) {
            isoResMsg_.setCardAcceptorIdCode(fromPostBridge.getCardAcceptorIdCode());
        }
        if (!fromPostBridge.getCardAcceptorIdLocation().isEmpty()) {
            isoResMsg_.setCardAcceptorIdLocation((fromPostBridge.getCardAcceptorIdLocation()));
        }
        if (!fromPostBridge.getRetrievalRefrenceNumber().isEmpty()) {
            isoResMsg_.setRetrievalRefrenceNumber(fromPostBridge.getRetrievalRefrenceNumber());
        }
        if (!fromPostBridge.getAuthorizationIdResponse().isEmpty()) {
            isoResMsg_.setAuthorizationIdResponse(fromPostBridge.getAuthorizationIdResponse());
        }

        if (!fromPostBridge.getServiceRestrictionCode().isEmpty()) {
            isoResMsg_.setServiceRestrictionCode(fromPostBridge.getServiceRestrictionCode());
        }
        if (!fromPostBridge.getTrack2Data().isEmpty()) {
            System.out.println(getLength(fromPostBridge.getTrack2Data().length(),2) + " / "+ fromPostBridge.getTrack2Data());
            isoResMsg_.setTrack2Data((fromPostBridge.getTrack2Data().length())+fromPostBridge.getTrack2Data());
        }
        if (!fromPostBridge.getTrack3Data().isEmpty()) {
            isoResMsg_.setTrack3Data(fromPostBridge.getTrack3Data());
        }


        if(!fromPostBridge.getAuthorizationLifeCycle().isEmpty()){
            isoResMsg_.setAuthorizationLifeCycle(getLength(fromPostBridge.getAuthorizationLifeCycle().length(),3)+fromPostBridge.getAuthorizationLifeCycle());
        }


        if (!fromPostBridge.getTrack1Data().isEmpty()) {
            isoResMsg_.setTrack1Data(fromPostBridge.getTrack1Data());
        }
        if (!fromPostBridge.getCurrencyCodeTransaction().isEmpty()) {
            isoResMsg_.setCurrencyCodeTransaction(fromPostBridge.getCurrencyCodeTransaction());
        }
        if (!fromPostBridge.getCurrencyCodeSettlement().isEmpty()) {
            isoResMsg_.setCurrencyCodeSettlement(fromPostBridge.getCurrencyCodeSettlement());
        }
        if (!fromPostBridge.getAmountTransactionFee().isEmpty()) {
            isoResMsg_.setAmountTransactionFee(fromPostBridge.getAmountTransactionFee());
        }
        if (!fromPostBridge.getAmountSettlementFee().isEmpty()) {
            isoResMsg_.setAmountSettlementFee(fromPostBridge.getAmountSettlementFee());
        }
        if (!fromPostBridge.getAmountTransactionProcessingFee().isEmpty()) {
            isoResMsg_.setAmountTransactionProcessingFee(fromPostBridge.getAmountTransactionProcessingFee());
        }
        if (!fromPostBridge.getAmountSettlementProcessingFee().isEmpty()) {
            isoResMsg_.setAmountSettlementProcessingFee(fromPostBridge.getAmountSettlementProcessingFee());
        }
        if (!fromPostBridge.getAdditionalData().isEmpty()) {
            isoResMsg_.setAdditionalData(fromPostBridge.getAdditionalData());
        }
        if (!fromPostBridge.getPINData().isEmpty()) {
            isoResMsg_.setPINData(fromPostBridge.getPINData());
        }
        if (!fromPostBridge.getSecurityRelatedControlCode().isEmpty()) {
            isoResMsg_.setSecurityRelatedControlCode(fromPostBridge.getSecurityRelatedControlCode());
        }
        if (!fromPostBridge.getSettlementCode().isEmpty()) {
            isoResMsg_.setSettlementCode(fromPostBridge.getSettlementCode());
        }
        if (!fromPostBridge.getNetworkInformationManagementCode().isEmpty()) {
            isoResMsg_.setNetworkInformationManagementCode(fromPostBridge.getNetworkInformationManagementCode());
        }
        if (!fromPostBridge.getFileName().isEmpty()) {
            isoResMsg_.setFileName(fromPostBridge.getFileName());
        }
        if (!fromPostBridge.getAccountIdNumber1().isEmpty()) {
            isoResMsg_.setAccountIdNumber1(getLength(fromPostBridge.getAccountIdNumber1().length(),2)+fromPostBridge.getAccountIdNumber1()); //102
        }
        if (!fromPostBridge.getAccountIdNumber2().isEmpty()) {
            isoResMsg_.setAccountIdNumber2(getLength(fromPostBridge.getAccountIdNumber2().length(),2)+fromPostBridge.getAccountIdNumber2());
        }
        if (!fromPostBridge.getPaymentNumber().isEmpty()) {
            isoResMsg_.setPaymentNumber(fromPostBridge.getPaymentNumber());
        }
        if (!fromPostBridge.getPaymentReversalNumber().isEmpty()) {
            isoResMsg_.setPaymentReversalNumber(fromPostBridge.getPaymentReversalNumber());
        }



        if (!fromPostBridge.getNetworkManagementInformation().isEmpty()) {
            isoResMsg_.setNetworkManagementInformation(fromPostBridge.getNetworkManagementInformation());
        }
        if (!fromPostBridge.getISO8583subfield().isEmpty()) {
            System.out.println("Sub field");
            System.out.println(fromPostBridge.getISO8583subfield());
            isoResMsg_.setISO8583subfield(fromPostBridge.getISO8583subfield());
        }
        if (!fromPostBridge.getMacExtended().isEmpty()) {
            isoResMsg_.setMacExtended(fromPostBridge.getMacExtended());
        }

        if (!fromPostBridge.getPosDataCode().isEmpty()) {
            System.out.println(fromPostBridge.getPosDataCode().length()+ "_"+fromPostBridge.getPosDataCode());
            isoResMsg_.setPosDataCode(getLength(fromPostBridge.getPosDataCode().length(),3)+fromPostBridge.getPosDataCode()); //123
        }

        // Setting all fields with null checks
        if (!fromPostBridge.getSwitchKey().isEmpty()) {
            isoResMsg_.setSwitchKey(getLength(fromPostBridge.getSwitchKey().length(),2)+fromPostBridge.getSwitchKey());
        }
        if (!fromPostBridge.getRoutingInformation().isEmpty()) {
            isoResMsg_.setRoutingInformation(fromPostBridge.getRoutingInformation());
        }
        if (!fromPostBridge.getPosData().isEmpty()) {
            isoResMsg_.setPosData(fromPostBridge.getPosData());
        }
        if (!fromPostBridge.getServiceStationData().isEmpty()) {
            isoResMsg_.setServiceStationData(fromPostBridge.getServiceStationData());
        }
        if (!fromPostBridge.getAuthorizationProfile().isEmpty()) {
            isoResMsg_.setAuthorizationProfile(fromPostBridge.getAuthorizationProfile());
        }
        if (!fromPostBridge.getCheckData().isEmpty()) {
            isoResMsg_.setCheckData(fromPostBridge.getCheckData());
        }
        if (!fromPostBridge.getRetentionData().isEmpty()) {
            isoResMsg_.setRetentionData(fromPostBridge.getRetentionData());
        }
        if (!fromPostBridge.getAdditionalNodeData().isEmpty()) {
            isoResMsg_.setAdditionalNodeData(fromPostBridge.getAdditionalNodeData());
        }
        if (!fromPostBridge.getCvv2().isEmpty()) {
            isoResMsg_.setCvv2(fromPostBridge.getCvv2());
        }
        if (!fromPostBridge.getOriginalKey().isEmpty()) {
            System.out.println("fromPostBridge.getOriginalKey(): "+fromPostBridge.getOriginalKey().length() + "_ "+fromPostBridge.getOriginalKey());
            isoResMsg_.setOriginalKey(getLength(fromPostBridge.getOriginalKey().length(),2)+fromPostBridge.getOriginalKey());
        }
        if (!fromPostBridge.getTerminalOwner().isEmpty()) {
            isoResMsg_.setTerminalOwner(getLength(fromPostBridge.getTerminalOwner().length(),2)+fromPostBridge.getTerminalOwner()); //127.12
        }
        if (!fromPostBridge.getPosGeographicData().isEmpty()) {
            isoResMsg_.setPosGeographicData(fromPostBridge.getPosGeographicData());
        }
        if (!fromPostBridge.getSponsorBank().isEmpty()) {
            isoResMsg_.setSponsorBank(fromPostBridge.getSponsorBank());
        }
        if (!fromPostBridge.getAddressVerificationData1().isEmpty()) {
            isoResMsg_.setAddressVerificationData1(fromPostBridge.getAddressVerificationData1());
        }
        if (!fromPostBridge.getAddressVerificationResult().isEmpty()) {
            isoResMsg_.setAddressVerificationResult(fromPostBridge.getAddressVerificationResult());
        }
        if (!fromPostBridge.getCardHolderInformation().isEmpty()) {
            isoResMsg_.setCardHolderInformation(fromPostBridge.getCardHolderInformation());
        }
        if (!fromPostBridge.getValidationData().isEmpty()) {
            isoResMsg_.setValidationData(getLength(fromPostBridge.getValidationData().length(),2)+fromPostBridge.getValidationData());
        }
        if (!fromPostBridge.getBankDetails().isEmpty()) {
            isoResMsg_.setBankDetails(fromPostBridge.getBankDetails());
        }
        if (!fromPostBridge.getOriginatorDateSettlement().isEmpty()) {
            isoResMsg_.setOriginatorDateSettlement(fromPostBridge.getOriginatorDateSettlement());
        }
        if (!fromPostBridge.getRecordIdentification().isEmpty()) {
            isoResMsg_.setRecordIdentification(fromPostBridge.getRecordIdentification());
        }
        if (!fromPostBridge.getStructureData().isEmpty()) {
            System.out.println("getStructureData");
            System.out.println("00"+fromPostBridge.getStructureData().length() +"_"+fromPostBridge.getStructureData());
            isoResMsg_.setStructureData(getLength(fromPostBridge.getStructureData().length(),5) +fromPostBridge.getStructureData()); // 127.22
        }
        if (!fromPostBridge.getPayeeNameAddress().isEmpty()) {
            isoResMsg_.setPayeeNameAddress(fromPostBridge.getPayeeNameAddress());
        }
        if (!fromPostBridge.getPayerAccount().isEmpty()) {
            isoResMsg_.setPayerAccount(getLength(fromPostBridge.getPayerAccount().length(),2)+fromPostBridge.getPayerAccount());
        }
        if (!fromPostBridge.getIccData().isEmpty()) {
            isoResMsg_.setIccData(fromPostBridge.getIccData());
        }
        if (!fromPostBridge.getOriginalNode().isEmpty()) {
            isoResMsg_.setOriginalNode(getLength(fromPostBridge.getOriginalNode().length(),2)+fromPostBridge.getOriginalNode());
        }
        if (!fromPostBridge.getCardVerificationResult().isEmpty()) {
            isoResMsg_.setCardVerificationResult(fromPostBridge.getCardVerificationResult());
        }
        if (!fromPostBridge.getAmericanExpressCardId().isEmpty()) {
            isoResMsg_.setAmericanExpressCardId(fromPostBridge.getAmericanExpressCardId());
        }
        if(!fromPostBridge.getForwardingInstitutionIdCode().isEmpty()){
            System.out.println(fromPostBridge.getForwardingInstitutionIdCode().length()+"_"+fromPostBridge.getForwardingInstitutionIdCode());
            isoResMsg_.setForwardingInstitutionIdCode(getLength(fromPostBridge.getForwardingInstitutionIdCode().length(),2)+fromPostBridge.getForwardingInstitutionIdCode());
        }
        if (!fromPostBridge.getSecureData().isEmpty()) {
            isoResMsg_.setSecureData(fromPostBridge.getSecureData());
        }
        if (!fromPostBridge.getSecureResult().isEmpty()) {
            isoResMsg_.setSecureResult(fromPostBridge.getSecureResult());
        }
        if (!fromPostBridge.getIssuerNetworkId().isEmpty()) {
            isoResMsg_.setIssuerNetworkId(fromPostBridge.getIssuerNetworkId());
        }
        if (!fromPostBridge.getUcafData().isEmpty()) {
            isoResMsg_.setUcafData(fromPostBridge.getUcafData());
        }
        if (!fromPostBridge.getExtendedTransactionType().isEmpty()) {
            isoResMsg_.setExtendedTransactionType(fromPostBridge.getExtendedTransactionType());
        }
        if (!fromPostBridge.getAccountTypeQualifier().isEmpty()) {
            isoResMsg_.setAccountTypeQualifier(fromPostBridge.getAccountTypeQualifier());
        }
        if (!fromPostBridge.getAcquirerNetworkId().isEmpty()) {
            isoResMsg_.setAcquirerNetworkId(fromPostBridge.getAcquirerNetworkId());
        }
        return isoResMsg_;
    }

    private String getLength(int len, int digit) {
        return String.format("%0" + digit + "d", len);
    }

}
