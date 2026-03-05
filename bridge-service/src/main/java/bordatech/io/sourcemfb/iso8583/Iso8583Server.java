package bordatech.io.sourcemfb.iso8583;



import com.bordatech.beans.ISO8583;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import bordatech.io.sourcemfb.iso8583.enums.MessageAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import com.bordatech.postilion.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Component
public class Iso8583Server {

    @Autowired
    ProcessIsoRequest  transaction;
    public void startServer_(int port) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("ISO 8583 server listening on port " + port + "...");

            while (true) {
                // Accept an incoming connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Received a connection from " + clientSocket.getRemoteSocketAddress());

                // Read and process the incoming message
                processClientMessage(clientSocket);
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
    private static final int THREAD_POOL_SIZE = 50; // Adjust based on expected load
    private final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public void startServer(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("ISO 8583 server listening on port " + port + "...");

            while (true) {
                try {
                    // Accept an incoming connection
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Received a connection from " + clientSocket.getRemoteSocketAddress());

                    // Handle the client in a separate thread
                    threadPool.execute(() -> handleClient(clientSocket));
                } catch (IOException e) {
                    System.err.println("Error accepting connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
    private void handleClient(Socket clientSocket) {
        try {
            System.out.println("Processing message from: " + clientSocket.getRemoteSocketAddress());

            // Keep the connection alive and process incoming data
            while (!clientSocket.isClosed()) {
                processClientMessage(clientSocket);
            }

        } catch (Exception e) {
            System.err.println("Error processing client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connection closed: " + clientSocket.getRemoteSocketAddress());
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
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

//    private void processClientMessage(Socket clientSocket) {
//
//        try (InputStream inputStream = clientSocket.getInputStream()) {
//            // Read the incoming message
//            byte[] buffer = new byte[4096];
//            int bytesRead = inputStream.read(buffer);
//            if (bytesRead > 0) {
//                byte[] receivedData = Arrays.copyOf(buffer, bytesRead);
//                String HexData =  ToHexString(receivedData);
//
//                System.out.println("============HexData=================");
//                System.out.println(HexData);
//                System.out.println("============HexData=================");
//
//                System.out.println("Received ISO 8583 message2: " + new String(receivedData, StandardCharsets.ISO_8859_1));
//
//                ISO8583 fromPostBridge =  ReadMessage.readPostilionMessage(receivedData);
//
//                System.out.println("========================message========================");
//                System.out.println();
//                System.out.println(fromPostBridge.getPan());
//                System.out.println(fromPostBridge.getAmountSettlement());
//                System.out.println("========================message========================");
//
//                ISO8583 isoResMsg = transaction.ProcessTransaction(fromPostBridge.getMti(),fromPostBridge);
//
//                byte [] res = isoResMsg.getMti().equals(MessageAction.NETWORK_MGT_RESPONSE.getValue())? WriteMessage.WriteToPostilionWIthSub(isoResMsg): WriteMessage.WriteToPostilion(isoResMsg);
//
//                OutputStream outputStream = clientSocket.getOutputStream();
//                outputStream.write(res);
//                outputStream.flush();
//                System.out.println("=======================clientSocket=======================");
//                System.out.println(clientSocket);
//                String HexData2=  ToHexString(res);
//                System.out.println("============HexData=================");
//                System.out.println(HexData2);
//                System.out.println("============HexData=================");
//                System.out.println("====================written back==========================");
//                System.out.println("=======================clientSocket=======================");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        finally {
////            try {
////                clientSocket.close();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//        }
//    }

    private void processClientMessage(Socket clientSocket) {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());

            byte[] buffer = new byte[4096];

            while (!clientSocket.isClosed()) { // Keep reading messages until the client disconnects
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    // Client has closed the connection
                    System.out.println("Client disconnected: " + clientSocket.getRemoteSocketAddress());
                    break;
                }

                // Process the received message
                byte[] receivedData = Arrays.copyOf(buffer, bytesRead);
                String hexData = ToHexString(receivedData);
                System.out.println("Received Hex Data: " + hexData);

                ISO8583 fromPostBridge = ReadMessage.readPostilionMessage(receivedData);
                System.out.println("Processing Transaction for PAN: " + fromPostBridge.getPan());

                ISO8583 isoResMsg = transaction.ProcessTransaction(fromPostBridge.getMti(), fromPostBridge);
                byte[] response = isoResMsg.getMti().equals(MessageAction.NETWORK_MGT_RESPONSE.getValue())
                        ? WriteMessage.WriteToPostilionWIthSub(isoResMsg)
                        : WriteMessage.WriteToPostilion(isoResMsg);

                // Send response back to client
                outputStream.write(response);
                outputStream.flush();
                System.out.println("Response sent: " + ToHexString(response));
            }

        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connection closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String ToHexString(byte[] toAsciiData) {
        String hexString = "";

        for (byte b : toAsciiData) {
            hexString += String.format("%02X", b);
        }
        return hexString;
    }
}

