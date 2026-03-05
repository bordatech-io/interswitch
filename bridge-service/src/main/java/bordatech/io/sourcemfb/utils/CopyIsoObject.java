package bordatech.io.sourcemfb.utils;

import bordatech.io.sourcemfb.iso8583.IsoHelper;
import com.bordatech.beans.ISO8583;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyIsoObject {

    @Autowired
    IsoHelper isoHelper;
    public  CopyIsoObject(){

    }
    public ISO8583 doCopy(ISO8583 fromPostBridge, ISO8583 isoResMsg_){
        // Setting all fields with null checks
        System.out.println("================Setting fields that are present===================");
        if (fromPostBridge.getPan() != null && !fromPostBridge.getPan().isEmpty()) {
            isoResMsg_.setPan(fromPostBridge.getPan());
        }
        if (fromPostBridge.getProcessingCodes() != null) {
            isoResMsg_.setProcessingCodes(fromPostBridge.getProcessingCodes());
        }
        if (fromPostBridge.getAmountTransaction() != null) {
            isoResMsg_.setAmountTransaction(fromPostBridge.getAmountTransaction());
        }
        if (fromPostBridge.getAmountSettlement() != null) {
            isoResMsg_.setAmountSettlement(fromPostBridge.getAmountSettlement());
        }
        if (fromPostBridge.getTransmissionDateTime() != null) {
            isoResMsg_.setTransmissionDateTime(fromPostBridge.getTransmissionDateTime());
        }
        if (fromPostBridge.getConversionRate() != null) {
            isoResMsg_.setConversionRate(fromPostBridge.getConversionRate());
        }
        if (fromPostBridge.getSystemTraceAudit() != null) {
            isoResMsg_.setSystemTraceAudit(fromPostBridge.getSystemTraceAudit());
        }
        if (fromPostBridge.getTimeLocalTransaction() != null) {
            isoResMsg_.setTimeLocalTransaction(fromPostBridge.getTimeLocalTransaction());
        }
        if (fromPostBridge.getDateLocalTransaction() != null) {
            isoResMsg_.setDateLocalTransaction(fromPostBridge.getDateLocalTransaction());
        }
        if (fromPostBridge.getDateExpiration() != null) {
            isoResMsg_.setDateExpiration(fromPostBridge.getDateExpiration());
        }
        if (fromPostBridge.getDateSettlement() != null) {
            isoResMsg_.setDateSettlement(fromPostBridge.getDateSettlement());
        }
        if (fromPostBridge.getDateConversion() != null) {
            isoResMsg_.setDateConversion(fromPostBridge.getDateConversion());
        }
        if (fromPostBridge.getMerchantType() != null) {
            isoResMsg_.setMerchantType(fromPostBridge.getMerchantType());
        }
        if (fromPostBridge.getPosEntryMode() != null) {
            isoResMsg_.setPosEntryMode(fromPostBridge.getPosEntryMode());
        }
        if (fromPostBridge.getCardSequenceNumber() != null) {
            isoResMsg_.setCardSequenceNumber(fromPostBridge.getCardSequenceNumber());
        }
        if (fromPostBridge.getPosConditionCode() != null) {
            isoResMsg_.setPosConditionCode(fromPostBridge.getPosConditionCode());
        }
        if (fromPostBridge.getPosPINCaptureCode() != null) {
            isoResMsg_.setPosPINCaptureCode(fromPostBridge.getPosPINCaptureCode());
        }
        if (fromPostBridge.getCardAcceptorTerminalId() != null) {
            isoResMsg_.setCardAcceptorTerminalId(fromPostBridge.getCardAcceptorTerminalId());
        }
        if (fromPostBridge.getCardAcceptorIdCode() != null) {
            isoResMsg_.setCardAcceptorIdCode(fromPostBridge.getCardAcceptorIdCode());
        }
        if (fromPostBridge.getCardAcceptorIdLocation() != null) {
            isoResMsg_.setCardAcceptorIdLocation(isoHelper.formatField(fromPostBridge.getCardAcceptorIdLocation(),40));
        }
        if (fromPostBridge.getRetrievalRefrenceNumber() != null) {
            isoResMsg_.setRetrievalRefrenceNumber(fromPostBridge.getRetrievalRefrenceNumber());
        }
        if (fromPostBridge.getAuthorizationIdResponse() != null) {
            isoResMsg_.setAuthorizationIdResponse(fromPostBridge.getAuthorizationIdResponse());
        }
        if (fromPostBridge.getResponseCode() != null) {
            isoResMsg_.setResponseCode(fromPostBridge.getResponseCode());
        }
        if (fromPostBridge.getServiceRestrictionCode() != null) {
            isoResMsg_.setServiceRestrictionCode(fromPostBridge.getServiceRestrictionCode());
        }
        if (fromPostBridge.getTrack2Data() != null) {
            isoResMsg_.setTrack2Data(fromPostBridge.getTrack2Data());
        }
        if (fromPostBridge.getTrack3Data() != null) {
            isoResMsg_.setTrack3Data(fromPostBridge.getTrack3Data());
        }
        if (fromPostBridge.getTrack1Data() != null) {
            isoResMsg_.setTrack1Data(fromPostBridge.getTrack1Data());
        }
        if (fromPostBridge.getCurrencyCodeTransaction() != null) {
            isoResMsg_.setCurrencyCodeTransaction(fromPostBridge.getCurrencyCodeTransaction());
        }
        if (fromPostBridge.getCurrencyCodeSettlement() != null) {
            isoResMsg_.setCurrencyCodeSettlement(fromPostBridge.getCurrencyCodeSettlement());
        }
        if (fromPostBridge.getAmountTransactionFee() != null) {
            isoResMsg_.setAmountTransactionFee(fromPostBridge.getAmountTransactionFee());
        }
        if (fromPostBridge.getAmountSettlementFee() != null) {
            isoResMsg_.setAmountSettlementFee(fromPostBridge.getAmountSettlementFee());
        }
        if (fromPostBridge.getAmountTransactionProcessingFee() != null) {
            isoResMsg_.setAmountTransactionProcessingFee(fromPostBridge.getAmountTransactionProcessingFee());
        }
        if (fromPostBridge.getAmountSettlementProcessingFee() != null) {
            isoResMsg_.setAmountSettlementProcessingFee(fromPostBridge.getAmountSettlementProcessingFee());
        }
        if (fromPostBridge.getAdditionalData() != null) {
            isoResMsg_.setAdditionalData(fromPostBridge.getAdditionalData());
        }
        if (fromPostBridge.getPINData() != null) {
            isoResMsg_.setPINData(fromPostBridge.getPINData());
        }
        if (fromPostBridge.getSecurityRelatedControlCode() != null) {
            isoResMsg_.setSecurityRelatedControlCode(fromPostBridge.getSecurityRelatedControlCode());
        }
        if (fromPostBridge.getSettlementCode() != null) {
            isoResMsg_.setSettlementCode(fromPostBridge.getSettlementCode());
        }
        if (fromPostBridge.getNetworkInformationManagementCode() != null) {
            isoResMsg_.setNetworkInformationManagementCode(fromPostBridge.getNetworkInformationManagementCode());
        }
        if (fromPostBridge.getFileName() != null) {
            isoResMsg_.setFileName(fromPostBridge.getFileName());
        }
        if (fromPostBridge.getAccountIdNumber1() != null) {
            isoResMsg_.setAccountIdNumber1(fromPostBridge.getAccountIdNumber1());
        }
        if (fromPostBridge.getAccountIdNumber2() != null) {
            isoResMsg_.setAccountIdNumber2(fromPostBridge.getAccountIdNumber2());
        }
        if (fromPostBridge.getPaymentNumber() != null) {
            isoResMsg_.setPaymentNumber(fromPostBridge.getPaymentNumber());
        }
        if (fromPostBridge.getPaymentReversalNumber() != null) {
            isoResMsg_.setPaymentReversalNumber(fromPostBridge.getPaymentReversalNumber());
        }
        if (fromPostBridge.getPosDataCode() != null) {
            isoResMsg_.setPosDataCode(fromPostBridge.getPosDataCode());
        }
        if (fromPostBridge.getNetworkManagementInformation() != null) {
            isoResMsg_.setNetworkManagementInformation(fromPostBridge.getNetworkManagementInformation());
        }
        if (fromPostBridge.getISO8583subfield() != null) {
            isoResMsg_.setISO8583subfield(fromPostBridge.getISO8583subfield());
        }
        if (fromPostBridge.getMacExtended() != null) {
            isoResMsg_.setMacExtended(fromPostBridge.getMacExtended());
        }

        // Setting all fields with null checks
        if (fromPostBridge.getSwitchKey() != null) {
            isoResMsg_.setSwitchKey(fromPostBridge.getSwitchKey());
        }
        if (fromPostBridge.getRoutingInformation() != null) {
            isoResMsg_.setRoutingInformation(fromPostBridge.getRoutingInformation());
        }
        if (fromPostBridge.getPosData() != null) {
            isoResMsg_.setPosData(fromPostBridge.getPosData());
        }
        if (fromPostBridge.getServiceStationData() != null) {
            isoResMsg_.setServiceStationData(fromPostBridge.getServiceStationData());
        }
        if (fromPostBridge.getAuthorizationProfile() != null) {
            isoResMsg_.setAuthorizationProfile(fromPostBridge.getAuthorizationProfile());
        }
        if (fromPostBridge.getCheckData() != null) {
            isoResMsg_.setCheckData(fromPostBridge.getCheckData());
        }
        if (fromPostBridge.getRetentionData() != null) {
            isoResMsg_.setRetentionData(fromPostBridge.getRetentionData());
        }
        if (fromPostBridge.getAdditionalNodeData() != null) {
            isoResMsg_.setAdditionalNodeData(fromPostBridge.getAdditionalNodeData());
        }
        if (fromPostBridge.getCvv2() != null) {
            isoResMsg_.setCvv2(fromPostBridge.getCvv2());
        }
        if (fromPostBridge.getOriginalKey() != null) {
            isoResMsg_.setOriginalKey(fromPostBridge.getOriginalKey());
        }
        if (fromPostBridge.getTerminalOwner() != null) {
            isoResMsg_.setTerminalOwner(fromPostBridge.getTerminalOwner());
        }
        if (fromPostBridge.getPosGeographicData() != null) {
            isoResMsg_.setPosGeographicData(fromPostBridge.getPosGeographicData());
        }
        if (fromPostBridge.getSponsorBank() != null) {
            isoResMsg_.setSponsorBank(fromPostBridge.getSponsorBank());
        }
        if (fromPostBridge.getAddressVerificationData1() != null) {
            isoResMsg_.setAddressVerificationData1(fromPostBridge.getAddressVerificationData1());
        }
        if (fromPostBridge.getAddressVerificationResult() != null) {
            isoResMsg_.setAddressVerificationResult(fromPostBridge.getAddressVerificationResult());
        }
        if (fromPostBridge.getCardHolderInformation() != null) {
            isoResMsg_.setCardHolderInformation(fromPostBridge.getCardHolderInformation());
        }
        if (fromPostBridge.getValidationData() != null) {
            isoResMsg_.setValidationData(fromPostBridge.getValidationData());
        }
        if (fromPostBridge.getBankDetails() != null) {
            isoResMsg_.setBankDetails(fromPostBridge.getBankDetails());
        }
        if (fromPostBridge.getOriginatorDateSettlement() != null) {
            isoResMsg_.setOriginatorDateSettlement(fromPostBridge.getOriginatorDateSettlement());
        }
        if (fromPostBridge.getRecordIdentification() != null) {
            isoResMsg_.setRecordIdentification(fromPostBridge.getRecordIdentification());
        }
        if (fromPostBridge.getStructureData() != null) {
            isoResMsg_.setStructureData(fromPostBridge.getStructureData());
        }
        if (fromPostBridge.getPayeeNameAddress() != null) {
            isoResMsg_.setPayeeNameAddress(fromPostBridge.getPayeeNameAddress());
        }
        if (fromPostBridge.getPayerAccount() != null) {
            isoResMsg_.setPayerAccount(fromPostBridge.getPayerAccount());
        }
        if (fromPostBridge.getIccData() != null) {
            isoResMsg_.setIccData(fromPostBridge.getIccData());
        }
        if (fromPostBridge.getOriginalNode() != null) {
            isoResMsg_.setOriginalNode(fromPostBridge.getOriginalNode());
        }
        if (fromPostBridge.getCardVerificationResult() != null) {
            isoResMsg_.setCardVerificationResult(fromPostBridge.getCardVerificationResult());
        }
        if (fromPostBridge.getAmericanExpressCardId() != null) {
            isoResMsg_.setAmericanExpressCardId(fromPostBridge.getAmericanExpressCardId());
        }
        if (fromPostBridge.getSecureData() != null) {
            isoResMsg_.setSecureData(fromPostBridge.getSecureData());
        }
        if (fromPostBridge.getSecureResult() != null) {
            isoResMsg_.setSecureResult(fromPostBridge.getSecureResult());
        }
        if (fromPostBridge.getIssuerNetworkId() != null) {
            isoResMsg_.setIssuerNetworkId(fromPostBridge.getIssuerNetworkId());
        }
        if (fromPostBridge.getUcafData() != null) {
            isoResMsg_.setUcafData(fromPostBridge.getUcafData());
        }
        if (fromPostBridge.getExtendedTransactionType() != null) {
            isoResMsg_.setExtendedTransactionType(fromPostBridge.getExtendedTransactionType());
        }
        if (fromPostBridge.getAccountTypeQualifier() != null) {
            isoResMsg_.setAccountTypeQualifier(fromPostBridge.getAccountTypeQualifier());
        }
        if (fromPostBridge.getAcquirerNetworkId() != null) {
            isoResMsg_.setAcquirerNetworkId(fromPostBridge.getAcquirerNetworkId());
        }
        System.out.println("================Done Setting fields that are present===================");
    return  isoResMsg_;
    }
}
