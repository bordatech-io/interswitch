package bordatech.io.sourcemfb.iso8583;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class Iso8583Client {

    public void sendIsoMessage(String host, int port) {
        try {
            // Load the ISO 8583 packager configuration
            InputStream packagerStream = getClass().getClassLoader().getResourceAsStream("packager.xml");
            if (packagerStream == null) {
                throw new IOException("Packager configuration file not found");
            }

            GenericPackager packager = new GenericPackager(packagerStream);

            // Create a new ISOMsg instance
//            ISOMsg isoMsg = new ISOMsg();

            // Sample ISO 8583 message in bytes
//            byte[] messageBytes = "018930323030F23E46D529E081200000000004000022313935303631333630323036353634363736343437333132303030303030303030303030303030303730383135333831333036333833353135333831333037303831353036303330393630313130353130303130303132433030303030303030433030303030303030313135303631333630323036383237353036313336303230363536343637363434373D31353036313031303030303031323835303633313031313130353030313150524F563030303030303030303031414445544F4B554E424F204144454D4F4C4120202020204C41474F5320202020202020204C414E473536363030343135313030313030303031323835303633313032303331323335363832303135353131323031323133333434303032303030303839601010000000000031303030303132383530363353414D7372632020202020204E657074756E65536E6B20203036333833353036333833354E657074756E65544720202031314E657074756E65204D46423230323430313237"
//                    .getBytes("ISO-8859-1");

            // Set the packager to the ISOMsg instance
//            isoMsg.setPackager(packager);
//
//            // Unpack the ISO 8583 message
//            isoMsg.unpack(messageBytes);
//
//            // Print the parsed message fields
//            printISO8583Message(isoMsg);







            // Create a new ISO 8583 message
            ISOMsg isoMsg = new ISOMsg();
            isoMsg.setPackager(packager);

            // Set the fields for the ISO 8583 message
            // Set the MTI and fields
            isoMsg.setMTI("0200");
            isoMsg.set(2, "5061360206564676447");
//            isoMsg.set(3, "312000");
//            isoMsg.set(4, "000000000000");
//            isoMsg.set(7, "0705142700");
//            isoMsg.set(11, "063835");
//            isoMsg.set(12, "142700");
//            isoMsg.set(13, "0705");
//            isoMsg.set(14, "1506");
//            isoMsg.set(15, "0309");
//            isoMsg.set(18, "6011");
//            isoMsg.set(22, "051");
//            isoMsg.set(23, "001");
//            isoMsg.set(25, "00");
//            isoMsg.set(26, "12");
//            isoMsg.set(28, "C00000000");
//            isoMsg.set(30, "C00000000");
//            isoMsg.set(32, "50613602068");
//            isoMsg.set(35, "5061360206564676447=1506101");
//            isoMsg.set(37, "000001285038");
//            isoMsg.set(40, "101");
//            isoMsg.set(41, "11050011");
//            isoMsg.set(42, "CAIC10440051001");
//            isoMsg.set(43, "ABULE  MFB   ATM  LAGOS               NG");
//            isoMsg.set(49, "566");
//            isoMsg.set(56, "1510");
//            isoMsg.set(59, "0001285038");
//            isoMsg.set(102, "2031235682");
//            isoMsg.set(123, "511201213344002");

            // Set composite field 127 with its subfields
//            ISOMsg compositeField127 = new ISOMsg(127);
//            compositeField127.set(2, "0001285038"); // Subfield 127.002
//            compositeField127.set(3, "SAMsrc      NeptuneSnk  063835063835NeptuneTG   "); // Subfield 127.003
//            compositeField127.set(12, "Berachah MFB"); // Subfield 127.012
//            compositeField127.set(20, "20240127"); // Subfield 127.020

            // Add composite field 127 to the main ISO message
//            isoMsg.set(compositeField127);

            // Pack the ISO 8583 message into a byte array
            byte[] packedMessage = isoMsg.pack();

            // Send the packed ISO 8583 message to the server
//            try (Socket socket = new Socket(host, port)) {
//                OutputStream outputStream = socket.getOutputStream();
//                outputStream.write(packedMessage);
//                outputStream.flush();
//                System.out.println("Sent ISO 8583 message: " + new String(packedMessage, StandardCharsets.ISO_8859_1));
//
//                // Receive the response from the server
//                InputStream inputStream = socket.getInputStream();
//                byte[] responseBuffer = new byte[4096];
//                int bytesRead = inputStream.read(responseBuffer);
//                if (bytesRead > 0) {
//                    byte[] responseData = Arrays.copyOf(responseBuffer, bytesRead);
//                    System.out.println("Received response: " + new String(responseData, StandardCharsets.ISO_8859_1));
//
//                    // Unpack the response message
//                    ISOMsg responseMessage = new ISOMsg();
//                    responseMessage.setPackager(packager);
//                    responseMessage.unpack(responseData);
//
//                    // Print the response fields
//                    System.out.println("Unpacked ISO 8583 response:");
//                    for (int i = 0; i <= responseMessage.getMaxField(); i++) {
//                        if (responseMessage.hasField(i)) {
//                            System.out.println("Field " + i + ": " + responseMessage.getString(i));
//                        }
//                    }
//                }
//            }

        } catch (ISOException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void printISO8583Message(ISOMsg isoMsg) {
        try {
            System.out.println("MTI : " + isoMsg.getMTI());
            for (int i = 1; i <= isoMsg.getMaxField(); i++) {
                if (isoMsg.hasField(i)) {
                    System.out.println("Field-" + i + " : " + isoMsg.getString(i));
                }
            }
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}

