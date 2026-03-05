package bordatech.io.sourcemfb.iso8583.requestProcessor;

import bordatech.io.sourcemfb.iso8583.IsoHelper;
import bordatech.io.sourcemfb.request.CbaMiddleWare;
import bordatech.io.sourcemfb.request.model.BlockRequest;
import bordatech.io.sourcemfb.request.model.DebitRequest;
import bordatech.io.sourcemfb.request.model.ReversalRequest;
import bordatech.io.sourcemfb.request.model.TransactionResponse;
import com.bordatech.beans.ISO8583;
import com.bordatech.postilion.CopyMessage;

import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import bordatech.io.sourcemfb.iso8583.enums.ResponseCode;
import bordatech.io.sourcemfb.utils.ConvertToJson;
import bordatech.io.sourcemfb.utils.SaveToLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TransactionAdvice {
    @Value("${BIN}")
    private String BIN;
    @Autowired
    CbaMiddleWare cbaMiddleWare;
    SaveToLog saveToLog;
    IsoHelper isoHelper;
    public TransactionAdvice (IsoHelper isoHelper,
                                 SaveToLog saveToLog) {

        this.saveToLog = saveToLog;
        this.isoHelper = isoHelper;
    }

    public ISO8583 performAdvice(ISO8583 fromPostBridge) {
        ISO8583 isoResMsg_ = new ISO8583(MessageAction.TRANSACTION_ADVICE_RESPONSE.getValue());
        System.out.println("=======================about to start transaction advice===========");
        double amount = Double.parseDouble(fromPostBridge.getAmountTransaction()) / 100;
        isoResMsg_.setAcquiringInstitutionIdCode(BIN.length() + BIN);
        String nuban = fromPostBridge.getAccountIdNumber1();

        isoResMsg_ = new CopyMessage().copy(fromPostBridge, isoResMsg_);


        // its time to debit the money been hold
        String narrationString = String.join("|",
                safe(fromPostBridge.getAuthorizationAgentInstitution()),
                safe(fromPostBridge.getCardAcceptorIdLocation()),
                safe(fromPostBridge.getTerminalOwner()));

        BlockRequest reversalRequest =  new BlockRequest();
        reversalRequest.setTransactionreference(fromPostBridge.getRetrievalRefrenceNumber());
        reversalRequest.setAccountnumber(nuban);
        reversalRequest.setAmount(amount);
        TransactionResponse transactionResponse = cbaMiddleWare.unblock(reversalRequest);
        if(transactionResponse.getCode().equals("00")){
            String returnedCode = this.isoHelper.doInternalDebit(
                    "debit",
                    nuban,
                    amount,
                    IsoHelper.extractFeeOrZero(fromPostBridge.getAmountTransactionFee()),
                    fromPostBridge.getRetrievalRefrenceNumber(),
                    narrationString
            );

            if(Objects.equals(returnedCode, "00")){
                isoResMsg_.setResponseCode(ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY.getValue());
            }else {
                isoResMsg_.setResponseCode(ResponseCode.SYSTEM_MALFUNCTION.getValue());
                // block the money back
                BlockRequest debitRequest = new BlockRequest();
                debitRequest.setAccountnumber(nuban);
                debitRequest.setTransactionreference(fromPostBridge.getRetrievalRefrenceNumber());
                debitRequest.setAmount(amount);
                cbaMiddleWare.block(debitRequest);
            }
        }




        saveToLog.saveToLogTable(
                fromPostBridge.getRetrievalRefrenceNumber(),
                ConvertToJson.setJsonString(isoResMsg_),
                isoResMsg_.toString(),
                ConvertToJson.setJsonString(isoResMsg_),
                "Transaction Advice",
                "CardOperation"
        );
        return isoResMsg_;
    }

    private static String safe(String value) {
        return value == null ? "" : value;
    }
}
