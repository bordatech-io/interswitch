package bordatech.io.sourcemfb.iso8583.requestProcessor;

import bordatech.io.sourcemfb.iso8583.IsoHelper;
import bordatech.io.sourcemfb.iso8583.enums.ResponseCode;
import bordatech.io.sourcemfb.request.CbaMiddleWare;
import bordatech.io.sourcemfb.request.model.BalanceEnquiryResponse;
import com.bordatech.beans.ISO8583;
import com.bordatech.postilion.CopyMessage;
import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import bordatech.io.sourcemfb.utils.ConvertToJson;
import bordatech.io.sourcemfb.utils.SaveToLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BalanceEnquiryRequest {
    @Value("${BIN}")
    private String BIN;

    @Autowired
    CbaMiddleWare cbaMiddleWare;

    SaveToLog saveToLog;
    public BalanceEnquiryRequest (SaveToLog saveToLog) {
        this.saveToLog = saveToLog;
    }
    public ISO8583 performBalanceEnquiry(ISO8583 fromPostBridge) {
        ISO8583 isoResMsg = new ISO8583(MessageAction.TRANSACTION_RESPONSE.getValue());
        // means balance enquiry
        String pan  = fromPostBridge.getPan();
        String nuban = fromPostBridge.getAccountIdNumber1(); // pan2Account(pan);
        //check balance


        BalanceEnquiryResponse balanceEnquiryResponse = cbaMiddleWare.balanceEnquiry(nuban);
        String responseCode = balanceEnquiryResponse.getResponseCode();

        if (responseCode != null && !responseCode.equals("14")) {

            String balance = IsoHelper.composeField54(
                    balanceEnquiryResponse.getLedgerBalance(),
                    balanceEnquiryResponse.getEffectiveBalance(),
                    fromPostBridge.getProcessingCodes().substring(0,1)
            );

            System.out.println("===============Balance==========");
            System.out.println(balance);
            System.out.println("===============Balance==========");

            isoResMsg.setAdditionalAmount(balance);
            isoResMsg.setResponseCode(ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY.getValue());

        } else {
            isoResMsg.setResponseCode(ResponseCode.SYSTEM_MALFUNCTION.getValue());
        }

        isoResMsg = new CopyMessage().copy(fromPostBridge, isoResMsg);
        isoResMsg.setAcquiringInstitutionIdCode(BIN.length()+BIN); //0032   [LLVAR  n    ..11 011] 032 [12345894736]



        saveToLog.saveToLogTable(
                fromPostBridge.getRetrievalRefrenceNumber(),
                ConvertToJson.setJsonString(fromPostBridge),
                fromPostBridge.toString(),
                ConvertToJson.setJsonString(isoResMsg),
                "BalanceEnquiry",
                "CardOperation"
        );

        return isoResMsg;
    }
}
