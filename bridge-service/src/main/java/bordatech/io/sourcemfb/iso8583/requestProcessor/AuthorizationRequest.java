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
