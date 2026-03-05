package bordatech.io.sourcemfb.iso8583;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class Iso8583WebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private GenericPackager isoPackager;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        try {
            // Parse the incoming ISO 8583 message
            System.out.println("==============handleTextMessage===========");
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(isoPackager);
            isoMsg.unpack(message.asBytes());

            // Process the message and send a response (for demo, echo it back)
            byte[] response = isoMsg.pack();
            session.sendMessage(new TextMessage(response));
            System.out.println(response);

        } catch (ISOException e) {
            session.sendMessage(new TextMessage("Error processing ISO 8583 message: " + e.getMessage()));
        }
    }
}

