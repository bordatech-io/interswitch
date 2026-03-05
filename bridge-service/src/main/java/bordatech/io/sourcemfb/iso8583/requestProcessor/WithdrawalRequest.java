package bordatech.io.sourcemfb.iso8583.requestProcessor;

import bordatech.io.sourcemfb.iso8583.IsoHelper;
import bordatech.io.sourcemfb.request.CbaMiddleWare;
import bordatech.io.sourcemfb.request.model.BalanceEnquiryResponse;
import com.bordatech.beans.ISO8583;
import com.bordatech.postilion.CopyMessage;

import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import bordatech.io.sourcemfb.iso8583.enums.ResponseCode;
import bordatech.io.sourcemfb.utils.ConvertToJson;
import bordatech.io.sourcemfb.utils.CopyIsoObject;
import bordatech.io.sourcemfb.utils.SaveToLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WithdrawalRequest {
    @Value("${BIN}")
    private String BIN;

    IsoHelper isoHelper;
    @Autowired
    CbaMiddleWare cbaMiddleWare;
    CopyIsoObject copyIsoObject;
    SaveToLog saveToLog;
    public WithdrawalRequest( CopyIsoObject copyIsoObject,
                             IsoHelper isoHelper, SaveToLog saveToLog) {

        this.isoHelper = isoHelper;
        this.saveToLog = saveToLog;
        this.copyIsoObject = copyIsoObject;
    }


    public ISO8583 performWithdrawal(ISO8583 fromPostBridge, String mode) {
        if (fromPostBridge == null) {
            throw new IllegalArgumentException("fromPostBridge cannot be null");
        }

        // Create a new ISO8583 object
        ISO8583 isoResMsg_ = new ISO8583(
                mode.equalsIgnoreCase("reversal") ?
                        MessageAction.REVERSAL_ADVICE_RESPONSE.getValue() :
                        MessageAction.TRANSACTION_RESPONSE.getValue()
        );
        System.out.println("=======================about to start a withdrawal===========" + mode);

        // Copy only non-null values from fromPostBridge to isoResMsg_
        System.out.println("=========about to copy================");
        // Extract transaction details
        String pan = fromPostBridge.getPan();
        double amount = Double.parseDouble(fromPostBridge.getAmountTransaction()) / 100;
        String nuban = fromPostBridge.getAccountIdNumber1(); // pan2Account(pan);

        System.out.println("PAN: " + pan);
        System.out.println("NUBAN: " + nuban);
        System.out.println("AMOUNT: " + amount);
        System.out.println("performWithdrawal isoResMsg_ before response: " + isoResMsg_);



        // check balance
        BalanceEnquiryResponse balanceEnquiryResponse = cbaMiddleWare.balanceEnquiry(nuban);
        double effectiveBalance = balanceEnquiryResponse.getEffectiveBalance();

        System.out.println("EFFECTIVE BALANCE: " + effectiveBalance);

        System.out.println("Transaction fees: "+ fromPostBridge.getAmountTransactionFee() );
        System.out.println("StructureData: "+ fromPostBridge.getStructureData());

        isoResMsg_.setAcquiringInstitutionIdCode(BIN.length() + BIN);
//

        isoResMsg_ = new CopyMessage().copy(fromPostBridge, isoResMsg_);


        if (amount < effectiveBalance) {


            String narrationString = String.join("|",
                    "reversal".equalsIgnoreCase(mode) ? "Withdraw-Reversal" : "Withdraw",
                    safe(fromPostBridge.getSponsorBank()),
                    safe(fromPostBridge.getCardAcceptorIdLocation()),
                    safe(fromPostBridge.getTerminalOwner())
            );
            String returnedCode = this.isoHelper.doInternalDebit(
                    mode,
                    nuban,
                    amount,
                    IsoHelper.extractFeeOrZero(fromPostBridge.getAmountTransactionFee()),
                    fromPostBridge.getRetrievalRefrenceNumber(),
                    narrationString
            );

            System.out.println("doInternalDebit returnedCode: " + returnedCode);
            isoResMsg_.setResponseCode(returnedCode);
        } else {
            // Insufficient balance
            isoResMsg_.setResponseCode(ResponseCode.NOT_SUFFICIENT_FUNDS.getValue()); // 39
        }

        System.out.println("performWithdrawal isoResMsg_ response: " + isoResMsg_);

        saveToLog.saveToLogTable(
                fromPostBridge.getRetrievalRefrenceNumber(),
                ConvertToJson.setJsonString(isoResMsg_),
                isoResMsg_.toString(),
                ConvertToJson.setJsonString(isoResMsg_),
                "Withdrawal",
                "CardOperation"
        );

        return isoResMsg_;
    }
    private static String safe(String value) {
        return value == null ? "" : value;
    }


}
