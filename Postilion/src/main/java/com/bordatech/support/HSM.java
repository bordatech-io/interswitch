/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.support;

import com.bordatech.beans.ISOLogger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import javax.crypto.SecretKey;
//import org.apache.xmlrpc.XmlRpcException;

/**
 *
 *
 */
public class HSM implements ResponseCode {

    ISOLogger log = new ISOLogger();

    public Map<String, String> TranslatePIN(Map<String, String> map) {
        System.out.println(map);
        Map<String, String> params = new HashMap<String, String>();
        String keyType = resourceMap.get("keyType");

        try {
            String sourceType = map.get("SourceType");
            if (sourceType.equals("1") && keyType.equalsIgnoreCase("3DESDUKPT")) {
                params = use3DESDukpt(map);
            } else if (keyType.equalsIgnoreCase("3DES")) {
                params = use3DES(map);
            } else {
                String errorCode = "98";
                params.put("errorCode", errorCode);
                params.put("STATUS", errorCode.equals("00") ? "0" : "1");
                return params;
            }
        } catch (Exception ex) {
            String errorCode = "98";
            params.put("errorCode", errorCode);
            params.put("STATUS", errorCode.equals("00") ? "0" : "1");
            return params;
        }
        return params;

    }

    public Map<String, String> use3DES(Map<String, String> map) {//throws XmlRpcException {

        Map<String, String> params = new HashMap<String, String>();

        String errorCode = "10";

        String pinPadkey1 = "";

        pinPadkey1 = resourceMap.get("pinpadKey1").trim();
        pinPadkey1 = pinPadkey1 + pinPadkey1.substring(0, 16);

        String pinPadkey2 = "";
        pinPadkey2 = resourceMap.get("pinpadKey2").trim();
        pinPadkey2 = pinPadkey2 + pinPadkey2.substring(0, 16);

        String pinblock = (String) map.get("SourcePIN");

        if (pinblock == null || pinblock.length() < 16) {
            params.put("errorCode", errorCode);
            params.put("STATUS", errorCode.equals("00") ? "0" : "1");
            return params;
        }
        byte[] rawkey = new byte[24];
        byte[] pinPadkey1b = TripleDES.hexToByte(pinPadkey1);
        byte[] pinPadkey2b = TripleDES.hexToByte(pinPadkey2);
        for (int i = 0; i < pinPadkey2b.length; i++) {
            rawkey[i] = (byte) (pinPadkey1b[i] ^ pinPadkey2b[i]);
        }

        String sourceData = (String) map.get("SourceData");//pan
        String sourcePIN = (String) map.get("SourcePIN");//pin block

        SecretKey key = TripleDES.readKey(rawkey);
        String pinPadPIN = TripleDES.Decrypt(key, sourcePIN);

        //System.out.println("the cleard block 1:  " + pinPadkey1);
        //System.out.println("the clear dblock 2:  " + pinPadkey2);
        //System.out.println("the clear block 1:  " + pinPadPIN);
        String pinBlock = pinPadPIN.substring(0, 16);
        //System.out.println("the clear block " + pinBlock);

        if (sourceData == null || sourceData.length() < 13) {
            params.put("errorCode", errorCode);
            params.put("STATUS", errorCode.equals("00") ? "0" : "1");
            return params;
        }

        String zpk = "";
        /*MessageType mes = new MessageType();
        if (mes.getZPK() != null && !mes.getZPK().equals("")) {
            zpk = mes.getZPK();
        } else {
            params.put("errorCode", errorCode);
            params.put("STATUS", errorCode.equals("00") ? "0" : "1");
            return params;
        }*/
        zpk = zpk + zpk.substring(0, 16);
        byte[] pkeyb = TripleDES.hexToByte(zpk);
        SecretKey postBridgekey = TripleDES.readKey(pkeyb);

        pinBlock = TripleDES.Encrypt(postBridgekey, pinBlock);

        if (pinBlock.length() == 16) {
            errorCode = "00";
            params.put("pin", pinBlock);
            params.put("errorCode", errorCode);
            params.put("STATUS", errorCode.equals("00") ? "0" : "1");
            return params;
        }

        return params;
    }

    public Map<String, String> use3DESDukpt(Map<String, String> map) {//throws XmlRpcException {
        System.out.println(map);
        Map<String, String> params = new HashMap<String, String>();
        String pin = (String) map.get("SourceKey");
        String destKey = (String) map.get("DestKey");
        String sourceType = (String) map.get("SourceType");
        String sourceFormat = (String) map.get("SourceFormat");
        String sourceData = (String) map.get("SourceData");
        String destData = (String) map.get("DestData");
        String sourcePIN = (String) map.get("SourcePIN");
        String KeySerial = (String) map.get("KeySerial");

        String keyIndex = KeySerial.substring(0, 2);
        keyIndex = "key".concat(keyIndex);
        String bdk = resourceMap.get(keyIndex).trim();
        System.out.println("bdk: " + bdk);
        if (bdk == null) {
            //throw new XmlRpcException("Bdk " + keyIndex + " reference not found for the POS");
        }

        String zpk = "";//(new MessageType()).getZPK();
        if (zpk == null) {
            //throw new XmlRpcException("Bdk " + keyIndex + " reference not found for the POS");
        }
        System.out.println("zpk: " + zpk);
        if (zpk == null) {
            params.put("STATUS", "1");
            params.put("pin", "");
            return params;
        }
        if (sourceData.length() < 13) {
            params.put("STATUS", "1");
            params.put("pin", "");
            return params;
        }
        sourceData = sourceData.substring(sourceData.length() - 13, sourceData.length() - 1);

        String data = bdk.trim() + 'U' + zpk.trim() + "605" + KeySerial.trim() + sourcePIN.trim() + "0101" + sourceData.trim();
        String useSheme = resourceMap.get("useScheme");
        if (useSheme == null || useSheme.isEmpty() || useSheme.trim().equalsIgnoreCase("Y")) {
            data = 'U' + data;
        }

        String returnString = sendRequest("111111111111", "G0", data, "");
        System.out.println("Message from HSM: " + returnString);
        int messageheaderLen = Integer.parseInt(resourceMap.get("hsmHeaderLength").trim());

        int messageLen = getMessageLength(returnString);
        System.out.println("Message length from HSM: " + messageLen);
        returnString = returnString.substring(2, returnString.length());
        String errorCode = returnString.substring(messageheaderLen + 2, messageheaderLen + 4);
        params.put("responseCode", returnString.substring(messageheaderLen, messageheaderLen + 2));
        params.put("errorCode", errorCode);
        params.put("STATUS", errorCode.charAt(0) == HSM_OK ? "0" : "1");

        if (errorCode.charAt(0) == HSM_OK) {
            params.put("pin", returnString.substring(messageheaderLen + 6, messageheaderLen + 22));
        }

        System.out.println("Message from HSM: " + returnString);
        return params;
    }

    private static Map<String, String> resourceMap = new HashMap<String, String>();

    public void put(Map<String, String> mapf) {
        resourceMap = mapf;
    }

    public Map<String, String> useEncryptedZMK(Map<String, String> map) {
        char keytype = '#';
        Map<String, String> params = new HashMap<String, String>();

        String zmk = "F2215D85CC70C03C34D308D3F0774EE5";
        zmk = resourceMap.get("encryptedZMK").trim();
        //String   zmk="13D193752FEAAAAA554AD3405150877F";
        String zpk = map.get("zpk");

        String data = 'U' + zmk + 'X' + zpk;//+((char)';')+((char)0)+'U'+((char)1);

        String returnString = sendRequest("Headertext 123", "FA", data, "");

        int messageheaderLen = Integer.parseInt(resourceMap.get("hsmHeaderLength").trim());

        int messageLen = getMessageLength(returnString);
        System.out.println("Message from HSM: " + returnString);
        if (messageLen >= (messageheaderLen + 4)) {
            returnString = returnString.substring(2, returnString.length());
            params.put("responseCode", returnString.substring(messageheaderLen, messageheaderLen + 2));
            params.put("errorCode", returnString.substring(messageheaderLen + 2, messageheaderLen + 4));
            System.out.println("Value of Response Code: " + returnString.substring(messageheaderLen, messageheaderLen + 2));
            System.out.println("Value of Error Code: " + returnString.substring(messageheaderLen + 2, messageheaderLen + 4));
        }
        if (messageLen > (messageheaderLen + 4)) {
            keytype = returnString.charAt(messageheaderLen + 4);
            if (keytype == 'U') {
                params.put("zpk", returnString.substring(messageheaderLen + 5, messageheaderLen + 37));
                System.out.println("Value of ZPK encrypted under LMK: " + returnString.substring(messageheaderLen + 5, messageheaderLen + 37));
            }//if
        }//if

        return params;
    }

    public Map<String, String> useClearZMK(Map<String, String> map) {
        Map<String, String> params = new HashMap<String, String>();

        String[] tzpk = {"TRANSLATING ZPK with Clear ZMK"};
        log.generateLog1(tzpk);
        
        String zmk1 = "";
        String zmk2 = "";
        zmk1 = resourceMap.get("clearZMK1").trim();
        zmk2 = resourceMap.get("clearZMK2").trim();
        zmk1 = zmk1 + zmk1.substring(0, 16);
        zmk2 = zmk2 + zmk2.substring(0, 16);

        String enczpk = map.get("zpk");
        String checkValue = map.get("checkValue");
        //System.out.println("The Key check Value:   " + checkValue);
        
        String[] kcv1 = {"KCV1 : " + checkValue};
        log.generateLog1(kcv1);

        byte[] zmk1b = TripleDES.hexToByte(zmk1);
        byte[] zmk2b = TripleDES.hexToByte(zmk2);

        byte[] zmk = new byte[zmk1b.length];
        for (int i = 0; i < zmk1b.length; i++) {
            zmk[i] = (byte) (zmk1b[i] ^ zmk2b[i]);
        }

        SecretKey key = TripleDES.readKey(zmk);
        String deczpk = TripleDES.Decrypt(key, enczpk);
        if (deczpk.length() != 32) {
            params.put("errorCode", "15");
            params.put("STATUS", "1");
            return params;
        }
        //System.out.println("The ZPK check Value-------- : " + deczpk);
        byte[] zpkb = TripleDES.hexToByte(deczpk + deczpk.substring(0, deczpk.length() / 2));

        key = TripleDES.readKey(zpkb);
        String checker = TripleDES.Encrypt(key, "0000000000000000");
        //System.out.println("The ZPK check Value: " + checker);
        
        String[] kcv2 = {"KCV2 : " + checker};
        log.generateLog1(kcv2);
        
        if (checker.substring(0, 6).equals(checkValue.substring(0, 6))) {
            params.put("errorCode", "00");
            params.put("STATUS", "0");
            params.put("zpk", deczpk);
        } else {
            params.put("errorCode", "15");
        }
        return params;
    }

    public Map<String, String> translateZPK(Map<String, String> map) {

        Map<String, String> params = new HashMap<String, String>();
        String keyType = resourceMap.get("keyType").trim();
        if (keyType.equalsIgnoreCase("3DES")) {
            params = useClearZMK(map);
        } else if (keyType.equalsIgnoreCase("3DESDUKPT")) {
            params = useEncryptedZMK(map);
        } else {
            params = null;
        }
        return params;

    }

    public Map<String, String> getVersion() throws Exception {

        String returnString = sendRequest("Headertext 123", "NC", "", "");

        int messageheaderLen = Integer.parseInt(resourceMap.get("hsmHeaderLength"));

        Map<String, String> params = new HashMap<String, String>();
        int messageLen = getMessageLength(returnString);
        returnString = returnString.substring(2, messageLen + 2);
        params.put("responseCode", returnString.substring(messageheaderLen, messageheaderLen + 2));
        params.put("errorCode", returnString.substring(messageheaderLen + 2, messageheaderLen + 4));
        //params.put("version", returnString.substring(messageheaderLen+20, messageLen));
        System.out.println("STATUS Code: " + returnString);
        return params;

    }

    private String sendRequest(String header, String command, String data, String trailer) {

        Socket hsmSocket = null;
        OutputStream outHsm = null;
        InputStream inHsm = null;

        String hsmOutput = "";
        int commandLength = command.length();

        int port = Integer.parseInt(resourceMap.get("hport").trim());
        String ipAddress = resourceMap.get("hipAddress").trim();
        int headerLength = Integer.parseInt(resourceMap.get("hsmHeaderLength").trim());

        String ZEROES = "";
        for (int i = 0; i < headerLength; i++) {
            ZEROES += "0";
        }
        String hsmMessageHeader = "";
        if (header == null) {
            hsmMessageHeader = ZEROES.substring(0, headerLength);
        } else if (header.length() < headerLength) {
            hsmMessageHeader = header + ZEROES.substring(0, headerLength - header.length());
        } else if (header.length() > headerLength) {
            hsmMessageHeader = header.substring(0, headerLength);
        } else {
            hsmMessageHeader = header;
        }
        int length = hsmMessageHeader.length() + commandLength + data.length() + trailer.length();

        try {
            byte[] le = toBCD(String.format("%1$04x", length));
            String bcdLength = new String(le);

            String hsmRequest = bcdLength + hsmMessageHeader + command + data + trailer;

            hsmSocket = new Socket(ipAddress, port);
            hsmSocket.setKeepAlive(true);
            hsmSocket.setSoTimeout(6000);

            outHsm = new DataOutputStream(hsmSocket.getOutputStream());
            inHsm = new DataInputStream(hsmSocket.getInputStream());
            System.out.println("Message to HSM: " + hsmRequest);
            outHsm.write(hsmRequest.getBytes());
            outHsm.flush();

            byte[] bytes = new byte[250];
            int bytesRead = inHsm.read(bytes);

            for (int i = 0; i < bytesRead; i++) {
                hsmOutput += (char) bytes[i];
            }
        } catch (IOException ex) {
            String[] form = {"Fatal from HSM ", "Caused: ", ex.getLocalizedMessage()};
            log.generateLog1(form);

        } catch (Exception ex) {
            String[] form = {"Fatal from HSM ", "Caused: ", ex.getLocalizedMessage()};
            log.generateLog1(form);

        } finally {

            if (hsmSocket.isConnected()) {
                try {
                    hsmSocket.close();
                } catch (IOException ex) {
                    String[] form = {"Fatal from HSM ", "Caused: ", ex.getLocalizedMessage()};
                    log.generateLog1(form);
                }
            }
            return hsmOutput;
        }
    }

    private int getMessageLength(String strLen) throws InputMismatchException {

        int Bytes = 0;
        for (int i = 0; i < 2; i++) {
            Bytes = Bytes * 256 + (byte) strLen.charAt(i);
        }
        return Bytes;
    }

    private byte[] toBCD(String hexString) {
        byte bcd[] = new byte[hexString.length() / 2];
        String hex = new String("0123456789ABCDEF");
        for (int i = 0, j = 0; i < hexString.length(); i++, j++) {
            byte firstQuadByte = (byte) (hex.indexOf(hexString.toUpperCase().charAt(i)) << 4);
            byte secondQuadByte = (byte) (hex.indexOf(hexString.toUpperCase().charAt(++i)));
            bcd[j] = (byte) (firstQuadByte | secondQuadByte);
        }
        return bcd;
    }
   

    public static void main(String[] arg) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("KeySerial", "0600757899E00005");
        map.put("SourcePIN", "8179E55399C58F65");
        map.put("SourceData", "632770021010123819");
        map.put("zpk", "2533E9E26E5C9E57A386273DD7ED8C6F");
        map.put("SourceType", "1");
        /*String pin=(String) map.get("SourceKey");
        String destKey=(String) map.get("DestKey");
        String sourceType=(String) map.get("SourceType");
        String sourceFormat=(String) map.get("SourceFormat");
        String destData=(String) map.get("DestData")*/

        Map<String, String> map2 = (new HSM()).TranslatePIN(map);
        //Map<String,String> map2=(new HSM()).getVersion();
        System.out.println("STATUS Code: " + map2.get("STATUS"));
        
    }
}
