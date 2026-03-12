package bordatech.io.sourcemfb.iso8583;

import bordatech.io.sourcemfb.iso8583.requestProcessor.*;
import com.bordatech.beans.ISO8583;

import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import bordatech.io.sourcemfb.iso8583.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProcessIsoRequest {

    private IsoHelper isoHelper;
    @Value("${BIN}")
    private String BIN;

    WithdrawalRequest withdrawalRequest;
    BalanceEnquiryRequest balanceEnquiryRequest;
    BillsPaymentRequest billsPaymentRequest;
    TransfersRequest transfersRequest;
    AuthorizationRequest authorizationRequest;
    TransactionAdvice transactionAdvice;
    TransactionNotification transactionNotification;

    NetworkManagementRequest networkManagementRequest;
    public ProcessIsoRequest(
                             WithdrawalRequest withdrawalRequest,
                             BalanceEnquiryRequest balanceEnquiryRequest,
                             BillsPaymentRequest billsPaymentRequest,
                             TransfersRequest transfersRequest,
                             NetworkManagementRequest networkManagementRequest,
                             AuthorizationRequest authorizationRequest,
                             TransactionAdvice transactionAdvice,
                             TransactionNotification transactionNotification) {

        this.withdrawalRequest = withdrawalRequest;
        this.balanceEnquiryRequest = balanceEnquiryRequest;
        this.billsPaymentRequest = billsPaymentRequest;
        this.transfersRequest = transfersRequest;
        this.authorizationRequest = authorizationRequest;
        this.transactionAdvice = transactionAdvice;
        this.transactionNotification = transactionNotification;
        this.networkManagementRequest =  networkManagementRequest;
    }

    public ISO8583 ProcessTransaction_(String MTI, ISO8583 fromPostBridge){
        System.out.println("================get MTI ================");
        System.out.println(MTI);
        System.out.println("================MTI end================");
        return MTI.equals(MessageAction.TRANSACTION_REQUEST.getValue()) ?
                    ProcessFinRequest(fromPostBridge) :
                MTI.equals(MessageAction.REVERSAL_ADVICE.getValue()) ?
                    ProcessFinReversalRequest(fromPostBridge) :
                        MTI.equals(MessageAction.REVERSAL_ADVICE_REPEAT.getValue()) ?
                    ProcessFinReversalRequest(fromPostBridge) :
                MTI.equals(MessageAction.AUTHORIZATION_REQUEST.getValue()) ?
                    ProcessAuthorizationRequest(fromPostBridge) :
                MTI.equals(MessageAction.TRANSACTION_ADVICE.getValue()) ?
                    ProcessTransactionAdviceRequest(fromPostBridge) :
                        MTI.equals(MessageAction.TRANSACTION_ADVICE_REPEAT.getValue()) ?
                                ProcessTransactionAdviceRequest(fromPostBridge) :
                MTI.equals(MessageAction.TRANSACTION_NOTIFICATION_ADVICE.getValue()) ?
                    ProcessTransactionNotificationRequest(fromPostBridge) :
                MTI.equals(MessageAction.NETWORK_MGT_REQUEST.getValue()) ?
                        ProcessNetworkManagementRequest(fromPostBridge):
                ProcessInvalidRequest(fromPostBridge);
    }

    public ISO8583 ProcessTransaction(String MTI, ISO8583 fromPostBridge) {
        System.out.println("================get MTI ================");
        System.out.println(MTI);
        System.out.println("================MTI end================");

        if (MTI == null) {
            return ProcessInvalidRequest(fromPostBridge);
        }

        switch (MTI) {
            case "0200":
                return ProcessFinRequest(fromPostBridge);

            case "0420":
            case "0421":
                return ProcessFinReversalRequest(fromPostBridge);

            case "0100":
                return ProcessAuthorizationRequest(fromPostBridge);

            case "0220":
            case "0221":
                return ProcessTransactionAdviceRequest(fromPostBridge);

            case "9220":
            case "9221":
                return ProcessTransactionNotificationRequest(fromPostBridge);

            case "0800":
                return ProcessNetworkManagementRequest(fromPostBridge);

            default:
                return ProcessInvalidRequest(fromPostBridge);
        }
    }

    public ISO8583 ProcessFinRequest(ISO8583 fromPostBridge) {
        System.out.println("================target before ProcessFinRequest ================");

        System.out.println("================target before ProcessFinRequest ================");
        ISO8583 isoResMsg = new ISO8583(MessageAction.TRANSACTION_RESPONSE.getValue());
        String processingCodePrefix = fromPostBridge.getProcessingCodes().substring(0, 1);
        String transMode = "normal";
        switch (processingCodePrefix) {
            case "0":
                isoResMsg = this.withdrawalRequest.performWithdrawal(fromPostBridge, transMode);
                break;
            case "3":
                isoResMsg = this.balanceEnquiryRequest.performBalanceEnquiry(fromPostBridge);
                break;
            case "4":
                isoResMsg = this.transfersRequest.performTransfer(fromPostBridge, transMode);
                break;
            case "5":
                isoResMsg = this.billsPaymentRequest.performBillsPayment(fromPostBridge, transMode);
                break;
            default:
                isoResMsg.setResponseCode(ResponseCode.INVALID_TRANSACTION.getValue());
                break;
        }
        return isoResMsg;
    }


    public ISO8583 ProcessFinReversalRequest(ISO8583 fromPostBridge) {
        System.out.println("================target before ProcessFinReversalRequest ================");

        System.out.println("================target before ProcessFinReversalRequest ================");
        ISO8583 isoResMsg = new ISO8583(MessageAction.REVERSAL_ADVICE_RESPONSE.getValue());
        String processingCodePrefix = fromPostBridge.getProcessingCodes().substring(0, 1);
        String transMode = "reversal";
        switch (processingCodePrefix) {
            case "0":
                isoResMsg = this.withdrawalRequest.performWithdrawal(fromPostBridge, transMode);
                break;
            case "3":
                isoResMsg = this.balanceEnquiryRequest.performBalanceEnquiry(fromPostBridge);
                break;
            case "4":
                isoResMsg = this.transfersRequest.performTransfer(fromPostBridge, transMode);
                break;
            case "5":
                isoResMsg = this.billsPaymentRequest.performBillsPayment(fromPostBridge, transMode);
                break;
            default:
                isoResMsg.setResponseCode(ResponseCode.INVALID_TRANSACTION.getValue()); // tentative for invalid request - response code
                break;
        }
        return isoResMsg;
    }

    public ISO8583 ProcessInvalidRequest(ISO8583 fromPostBridge) {
        ISO8583 isoResMsg = new ISO8583(MessageAction.TRANSACTION_RESPONSE.getValue());
        isoResMsg.setResponseCode(ResponseCode.INVALID_RESPONSE.getValue());
        return isoResMsg;
    }

    public ISO8583 ProcessAuthorizationRequest(ISO8583 fromPostBridge) {
        return this.authorizationRequest.performAuthorization(fromPostBridge);
    }

    public ISO8583 ProcessTransactionAdviceRequest(ISO8583 fromPostBridge) {
        return this.transactionAdvice.performAdvice(fromPostBridge);
    }

    public ISO8583 ProcessTransactionNotificationRequest(ISO8583 fromPostBridge) {
        return this.transactionNotification.performNotification(fromPostBridge);
    }

    public ISO8583 ProcessNetworkManagementRequest(ISO8583 fromPostBridge) {
        return this.networkManagementRequest.performNetworkManagement(fromPostBridge);
    }

}
