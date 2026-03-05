package bordatech.io.sourcemfb.iso8583.requestProcessor;

import bordatech.io.sourcemfb.request.CbaMiddleWare;
import bordatech.io.sourcemfb.request.model.BalanceEnquiryResponse;
import bordatech.io.sourcemfb.request.model.BlockRequest;
import bordatech.io.sourcemfb.request.model.DebitRequest;
import com.bordatech.beans.ISO8583;
import com.bordatech.postilion.CopyMessage;

import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import bordatech.io.sourcemfb.iso8583.enums.ResponseCode;
import bordatech.io.sourcemfb.utils.SaveToLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationRequest {
    @Value("${BIN}")
    private String BIN;
    @Autowired
    CbaMiddleWare cbaMiddleWare;
    SaveToLog saveToLog;
    public AuthorizationRequest (
                                  SaveToLog saveToLog) {

        this.saveToLog = saveToLog;
    }

    public ISO8583 performAuthorization_(ISO8583 fromPostBridge) {
        ISO8583 isoResMsg = new ISO8583(MessageAction.AUTHORIZATION_RESPONSE.getValue());

        System.out.println("=======================about to start authorization request===========");



        isoResMsg.setPan(fromPostBridge.getPan().length()+fromPostBridge.getPan()); //002  [LLVAR  n    ..19 019] 002 [5061360206564676447]  Primary Account Number (PAN)
        isoResMsg.setProcessingCodes(fromPostBridge.getProcessingCodes()); //003  [Fixed  n       6 006] 003 [312000] Processing Code
        isoResMsg.setAmountTransaction(fromPostBridge.getAmountTransaction()); //004  [None   n         012] 004 [000000000000]
        isoResMsg.setTransmissionDateTime(fromPostBridge.getTransmissionDateTime()); //007  [Fixed  n      10 010] 007 [0709114518]
        isoResMsg.setSystemTraceAudit(fromPostBridge.getSystemTraceAudit()); //011   [Fixed  n       6 006] 011 [678881]
        isoResMsg.setTimeLocalTransaction(fromPostBridge.getTimeLocalTransaction()); //012 [Fixed  n       6 006] 012 [114518]
        isoResMsg.setDateLocalTransaction(fromPostBridge.getDateLocalTransaction()); //013  [Fixed  n       4 004] 013 [0709]
        isoResMsg.setDateExpiration(fromPostBridge.getDateExpiration()); //014  [Fixed  n       4 004] 014 [1506]
        isoResMsg.setDateSettlement(fromPostBridge.getDateSettlement()); //015   [Fixed  n       4 004] 015 [0127]
        isoResMsg.setMerchantType(fromPostBridge.getMerchantType()); //018  [Fixed  n       4 004] 018 [6011]
        isoResMsg.setPosEntryMode("000"); //022  [Fixed  n       3 003] 022 [051]
        isoResMsg.setCardSequenceNumber(fromPostBridge.getCardSequenceNumber()); //023   [None   n         003] 023 [001]
        isoResMsg.setPosConditionCode(fromPostBridge.getPosConditionCode()); //025    [Fixed  n       2 002]  025 [27]
        isoResMsg.setPosPINCaptureCode(fromPostBridge.getPosPINCaptureCode()); //026  [Fixed   n       2 002] 026 [00]
        isoResMsg.setAmountTransactionFee("C00000000"); //028  [Fixed  x+n     9 009] 028 [C00000000]
        System.out.println("isoResMsg.getAmountTransactionFee() "+ isoResMsg.getAmountTransactionFee());
        isoResMsg.setAmountTransactionProcessingFee("C00000000"); //030   [Fixed  x+n     9 009] 030 [C00000000]
        isoResMsg.setAcquiringInstitutionIdCode(BIN.length()+BIN); //032   [LLVAR  n    ..11 011] 032 [12345894736]

        if(!isoResMsg.getForwardingInstitutionIdCode().isEmpty()){
            isoResMsg.setForwardingInstitutionIdCode(
                    (fromPostBridge.getForwardingInstitutionIdCode().length()<10?fromPostBridge.getForwardingInstitutionIdCode():"0"+fromPostBridge.getForwardingInstitutionIdCode())
                            +fromPostBridge.getForwardingInstitutionIdCode()); //033 [LLVAR  an   11  6] 033 [013670]
        }

        isoResMsg.setTrack2Data("27"+fromPostBridge.getTrack2Data()); //035  [LLVAR  z    ..37 027] 035 [5061360206564676447=1506101]
        isoResMsg.setRetrievalRefrenceNumber(fromPostBridge.getRetrievalRefrenceNumber()); //037  [Fixed  anp    12 012] 037 [000001285082]
        isoResMsg.setServiceRestrictionCode(fromPostBridge.getServiceRestrictionCode()); //040   [Fixed  an      3 003] 040 [101]
        isoResMsg.setCardAcceptorTerminalId(fromPostBridge.getCardAcceptorTerminalId()); //041  [Fixed  ans     8 008] 041 [11050011]
        isoResMsg.setCardAcceptorIdCode(fromPostBridge.getCardAcceptorIdCode()); // 042  [Fixed  ans    15 015] 042 [CAIC10440051001]
        isoResMsg.setCardAcceptorIdLocation(fromPostBridge.getCardAcceptorIdLocation()); //043  [Fixed  ans    40 040] 043 [ABULE  MFB   ATM  LAGOS               NG]
        isoResMsg.setCurrencyCodeTransaction(fromPostBridge.getCurrencyCodeTransaction()); //049   [Fixed  a/n     3 003] 049 [566]
        if(!fromPostBridge.getEchoData().isEmpty()){
            System.out.println("Echo is not empty "+fromPostBridge.getEchoData());
            isoResMsg.setEchoData("010"+fromPostBridge.getEchoData()); // 059   [LLLVAR ans ..500 010] 059 [0000010104]
        }

        isoResMsg.setAccountIdNumber1("10"+fromPostBridge.getAccountIdNumber1()); //102 [LLVAR  ans  ..28 010] 102 [2031235682]
        isoResMsg.setPosDataCode("015"+fromPostBridge.getPosDataCode()); //123 [LLLVAR an 15 015] 123 [511201213344002]
        // sub
        isoResMsg.setSwitchKey(fromPostBridge.getSwitchKey().length()+fromPostBridge.getSwitchKey());
        isoResMsg.setRoutingInformation(fromPostBridge.getRoutingInformation()); //127.003 [Fixed  ans*   48 048] 127.003 [SAMsrc      NeptuneSnk  678881678881NeptuneTG   ]
        isoResMsg.setAuthorizationProfile("11"); //127.006
        isoResMsg.setTerminalOwner(fromPostBridge.getTerminalOwner().length()+fromPostBridge.getTerminalOwner()); //127.012
        isoResMsg.setOriginatorDateSettlement(fromPostBridge.getOriginatorDateSettlement()); //127.020
        isoResMsg.setResponseCode(ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY.getValue());
        return isoResMsg;
    }
    public ISO8583 performAuthorization(ISO8583 fromPostBridge) {
        ISO8583 isoResMsg = new ISO8583(MessageAction.AUTHORIZATION_RESPONSE.getValue());

        System.out.println("=======================about to start authorization request===========" + isoResMsg.getMti());
        isoResMsg = new CopyMessage().copy(fromPostBridge, isoResMsg);
        isoResMsg.setAcquiringInstitutionIdCode(BIN.length()+BIN); //0032   [LLVAR  n    ..11 011] 032 [12345894736]


        String pan = fromPostBridge.getPan();
        double amount = Double.parseDouble(fromPostBridge.getAmountTransaction()) / 100;
        String nuban = fromPostBridge.getAccountIdNumber1(); // pan2Account(pan);

        System.out.println("PAN: " + pan);
        System.out.println("NUBAN: " + nuban);
        System.out.println("AMOUNT: " + amount);
        System.out.println("authorization isoResMsg_ before response: " + isoResMsg);

        BalanceEnquiryResponse balanceEnquiryResponse = cbaMiddleWare.balanceEnquiry(nuban);
        double effectiveBalance = balanceEnquiryResponse.getEffectiveBalance();

//        System.out.println("EFFECTIVE BALANCE: " + effectiveBalance);


        if (amount < effectiveBalance) {
            // hold the amount in the balance
            BlockRequest debitRequest = new BlockRequest();
            debitRequest.setAccountnumber(nuban);
            debitRequest.setTransactionreference(fromPostBridge.getRetrievalRefrenceNumber());
            debitRequest.setAmount(amount);

            String responseCode = cbaMiddleWare.block(debitRequest).getCode();
            if(responseCode.equals("00")){
                isoResMsg.setResponseCode(ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY.getValue());
            }else {
                isoResMsg.setResponseCode(ResponseCode.DO_NOT_HONOUR.getValue());
            }
        } else {
            // Insufficient balance
            isoResMsg.setResponseCode(ResponseCode.NOT_SUFFICIENT_FUNDS.getValue()); // 39
        }

        isoResMsg.setResponseCode(ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY.getValue());
        return isoResMsg;
    }
}
