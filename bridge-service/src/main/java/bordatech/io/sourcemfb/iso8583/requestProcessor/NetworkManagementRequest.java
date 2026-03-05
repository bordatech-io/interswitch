package bordatech.io.sourcemfb.iso8583.requestProcessor;

import com.bordatech.beans.ISO8583;

import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import bordatech.io.sourcemfb.iso8583.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NetworkManagementRequest {
    @Value("${BIN}")
    private String BIN;

    public NetworkManagementRequest() {

    }

    public ISO8583 performNetworkManagement(ISO8583 fromPostBridge) {
        ISO8583 isoResMsg = new ISO8583(MessageAction.NETWORK_MGT_RESPONSE.getValue());
        System.out.println("=======================About to Start Network Mgt===========");
        isoResMsg.setTransmissionDateTime(fromPostBridge.getTransmissionDateTime()); //007  [Fixed  n      10 010] 007 [0709114518]
        isoResMsg.setSystemTraceAudit(fromPostBridge.getSystemTraceAudit()); //011   [Fixed  n       6 006] 011 [678881]
        isoResMsg.setTimeLocalTransaction(fromPostBridge.getTimeLocalTransaction()); //012 [Fixed  n       6 006] 012 [114518]
        isoResMsg.setDateLocalTransaction(fromPostBridge.getDateLocalTransaction()); //013  [Fixed  n       4 004] 013 [0709]
        isoResMsg.setNetworkInformationManagementCode(fromPostBridge.getNetworkInformationManagementCode()); // 070
        isoResMsg.setResponseCode(ResponseCode.APPROVED_OR_COMPLETED_SUCCESSFULLY.getValue());
        return isoResMsg;
    }
}
