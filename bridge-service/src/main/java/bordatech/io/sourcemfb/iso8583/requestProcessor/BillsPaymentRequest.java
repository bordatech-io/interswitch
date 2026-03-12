package bordatech.io.sourcemfb.iso8583.requestProcessor;

import bordatech.io.sourcemfb.request.CbaMiddleWare;
import bordatech.io.sourcemfb.request.model.BalanceEnquiryResponse;
import com.bordatech.beans.ISO8583;
import com.bordatech.postilion.CopyMessage;


import bordatech.io.sourcemfb.iso8583.IsoHelper;
import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import bordatech.io.sourcemfb.iso8583.enums.ResponseCode;
import bordatech.io.sourcemfb.utils.ConvertToJson;
import bordatech.io.sourcemfb.utils.SaveToLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static bordatech.io.sourcemfb.iso8583.IsoHelper.formatLeadingC;

@Component
public class BillsPaymentRequest {
    @Value("${BIN}")
    private String BIN;

    @Autowired
    CbaMiddleWare cbaMiddleWare;
    IsoHelper isoHelper;
    SaveToLog saveToLog;
    public BillsPaymentRequest (IsoHelper isoHelper, SaveToLog saveToLog) {
        this.isoHelper = isoHelper;
        this.saveToLog = saveToLog;
    }

    public ISO8583 performBillsPayment(ISO8583 fromPostBridge, String mode) {
        ISO8583 isoResMsg = new ISO8583(mode.equalsIgnoreCase("reversal") ?
                        MessageAction.REVERSAL_ADVICE_RESPONSE.getValue() :
                        MessageAction.TRANSACTION_RESPONSE.getValue());
        System.out.println("=======================about to start bills payment==========="+mode);

        String pan = fromPostBridge.getPan();
        String nuban = fromPostBridge.getAccountIdNumber1(); // pan2Account(pan);

        double amount = (Double.parseDouble(fromPostBridge.getAmountTransaction())) / 100;
        double transFee = (Double.parseDouble(formatLeadingC(fromPostBridge.getAmountTransactionFee()))) / 100;
        double transProcessingFee = (Double.parseDouble(formatLeadingC(fromPostBridge.getAmountTransactionProcessingFee()))) / 100;

        double cummulativeFee = amount + transFee + transProcessingFee;

        System.out.println("RAW AMount: "+fromPostBridge.getAmountTransaction() );
        System.out.println("RAW MOUNT TRANSACTION FEE: "+fromPostBridge.getAmountTransactionFee() );
        System.out.println("PAN: "+pan);
        System.out.println("NUBAN: "+nuban);
        System.out.println("AMOUNT: "+amount);
        System.out.println("AMOUNT TRANSACTION FEE: "+transFee);
        System.out.println("AMOUNT TRANSACTION PROCESSING FEE: "+transProcessingFee);
        System.out.println("CUMMULATIVE FEE: "+cummulativeFee);

        BalanceEnquiryResponse balanceEnquiryResponse = cbaMiddleWare.balanceEnquiry(nuban);
        double effectiveBalance = balanceEnquiryResponse.getEffectiveBalance();

        System.out.println("BALANCE: "+ effectiveBalance);
        isoResMsg = new CopyMessage().copy(fromPostBridge, isoResMsg);
        isoResMsg.setAcquiringInstitutionIdCode(BIN.length()+BIN); //0032   [LLVAR  n    ..11 011] 032 [12345894736]
        System.out.println("32: "+fromPostBridge.getAcquiringInstitutionIdCode().length()+fromPostBridge.getAcquiringInstitutionIdCode());
        isoResMsg.setAuthorizationProfile("11"); //127.006

        if (cummulativeFee < effectiveBalance) {

            String narrationString = String.join("|",
                    "reversal".equalsIgnoreCase(mode) ? "Bill-Reversal" : "Bill",
                    safe(fromPostBridge.getSponsorBank()),
                    safe(fromPostBridge.getCardAcceptorIdLocation()),
                    safe(fromPostBridge.getTerminalOwner()),
                    safe(fromPostBridge.getRetrievalRefrenceNumber())
            );
            String returnedCode = this.isoHelper.doInternalDebit(
                    mode,
                    nuban,
                    cummulativeFee,
                    IsoHelper.extractFeeOrZero(fromPostBridge.getAmountTransactionFee()),
                    fromPostBridge.getRetrievalRefrenceNumber(),
                    narrationString
            );

            System.out.println("doInternalDebit returnedCode: "+returnedCode);

            isoResMsg.setResponseCode(returnedCode);
        } else {
            // insufficient balance
            isoResMsg.setResponseCode(ResponseCode.NOT_SUFFICIENT_FUNDS.getValue()); //51
        }

        System.out.println("performBillsPayment isoResMsg response : "+isoResMsg);

        saveToLog.saveToLogTable(
                fromPostBridge.getRetrievalRefrenceNumber(),
                ConvertToJson.setJsonString(fromPostBridge),
                fromPostBridge.toString(),
                ConvertToJson.setJsonString(isoResMsg),
                "BillsPayment",
                "CardOperation"
        );

        return isoResMsg;
    }

    private static String safe(String value) {
        return value == null ? "" : value;
    }

}
