package bordatech.io.sourcemfb.iso8583.requestProcessor;

import bordatech.io.sourcemfb.iso8583.IsoHelper;
import bordatech.io.sourcemfb.request.CbaMiddleWare;
import bordatech.io.sourcemfb.request.model.BalanceEnquiryResponse;
import com.bordatech.beans.ISO8583;
import com.bordatech.postilion.CopyMessage;

import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import bordatech.io.sourcemfb.iso8583.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TransfersRequest {
    @Value("${BIN}")
    private String BIN;
    @Autowired
    CbaMiddleWare cbaMiddleWare;
    IsoHelper isoHelper;

    public TransfersRequest( IsoHelper isoHelper) {

        this.isoHelper = isoHelper;
    }


    public ISO8583 performTransfer(ISO8583 fromPostBridge, String mode) {
        ISO8583 isoResMsg = new ISO8583(mode.equalsIgnoreCase("reversal") ?
                        MessageAction.REVERSAL_ADVICE_RESPONSE.getValue() :
                        MessageAction.TRANSACTION_RESPONSE.getValue());
        System.out.println("=======================about to start transfer==========="+mode);

        String pan = fromPostBridge.getPan();
        String nuban = fromPostBridge.getAccountIdNumber1(); // pan2Account(pan);

        double amount = (Double.parseDouble(fromPostBridge.getAmountTransaction())) / 100;
        double transFee = (Double.parseDouble(IsoHelper.formatLeadingC(fromPostBridge.getAmountTransactionFee()))) / 100;
        double transProcessingFee = (Double.parseDouble(IsoHelper.formatLeadingC(fromPostBridge.getAmountTransactionProcessingFee()))) / 100;

        double cummulativeFee = amount + transFee + transProcessingFee;

        System.out.println("PAN: "+pan);
        System.out.println("NUBAN: "+nuban);
        System.out.println("AMOUNT: "+amount);
        System.out.println("AMOUNT TRANSACTION FEE: "+transFee);
        System.out.println("AMOUNT TRANSACTION PROCESSING FEE: "+transProcessingFee);

        // check balance
        BalanceEnquiryResponse balanceEnquiryResponse = cbaMiddleWare.balanceEnquiry(nuban);
        double effectiveBalance = balanceEnquiryResponse.getEffectiveBalance();

        System.out.println("BALANCE: "+ effectiveBalance);

        isoResMsg.setAcquiringInstitutionIdCode(BIN.length()+BIN); //0032   [LLVAR  n    ..11 011] 032 [12345894736]

        isoResMsg = new CopyMessage().copy(fromPostBridge, isoResMsg);


        if (cummulativeFee < effectiveBalance) {
            String narrationString = String.join("|",
                    "reversal".equalsIgnoreCase(mode) ? "Transfer-Reversal" : "Transfer",
                    safe(fromPostBridge.getSponsorBank()),
                    safe(fromPostBridge.getCardAcceptorIdLocation()),
                    safe(fromPostBridge.getTerminalOwner())
            );
            String returnedCode = this.isoHelper.doInternalDebit(
                    mode,
                    nuban,
                    cummulativeFee,
                    IsoHelper.extractFeeOrZero(fromPostBridge.getAmountTransactionFee()),
                    fromPostBridge.getRetrievalRefrenceNumber(),
                    narrationString);
            isoResMsg.setResponseCode(returnedCode);
        } else {
            // insufficient balance
            isoResMsg.setResponseCode(ResponseCode.NOT_SUFFICIENT_FUNDS.getValue()); //51
        }
        return isoResMsg;

    }
    private static String safe(String value) {
        return value == null ? "" : value;
    }
}
