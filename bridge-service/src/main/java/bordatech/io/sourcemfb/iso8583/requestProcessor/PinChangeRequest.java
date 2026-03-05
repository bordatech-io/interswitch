package bordatech.io.sourcemfb.iso8583.requestProcessor;

import com.bordatech.beans.ISO8583;
import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PinChangeRequest {
    @Value("${BIN}")
    private String BIN;

    public PinChangeRequest() {

    }

    public ISO8583 performPinChange(ISO8583 fromPostBridge) {
        ISO8583 isoResMsg = new ISO8583(MessageAction.TRANSACTION_RESPONSE.getValue());

        System.out.println("=======================about to start pin change===========");

        return isoResMsg;
    }
}
