/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bordatech.postilion;

import com.bordatech.beans.ISO8583;
import com.bordatech.gateway.IsoTransformer;

/**
 *
 * @author xpat
 */
public class ReadMessage {
    public static ISO8583 readPostilionMessage(byte[] data){
        IsoTransformer transformer = new IsoTransformer();
//      ISOLogger log = new ISOLogger();
        //String receive = "";
        ISO8583 fromPostBridge = new ISO8583("");
        //ISOReader isoReceived = new ISOReader();

        String receive = "";
        for (int i = 2; i < data.length; i++) {
            receive += (char) data[i];
        }

        System.out.println("receive="+receive);
        
        transformer.getMti(fromPostBridge, receive.substring(0, 4));
        transformer.getBitmap(fromPostBridge, receive.substring(0, receive.length()));
        
        // isoReceived.setResponse(fromPostBridge);
        
        System.out.println("MTI:"+fromPostBridge.getMti());
        System.out.println("PAN:"+fromPostBridge.getPan());

//        fromPostBridge.setMti("0210");
//        fromPostBridge.setPan("195061360206564676447");
//        fromPostBridge.setResponseCode("00");
        
//        fromPostBridge.setBitmapsb(true);
//        String subfields = fromPostBridge.toStringsb();
//        String length = String.format("%06d", subfields.length());
//        fromPostBridge.setISO8583subfield(length + subfields);
//
//        fromPostBridge.setSecondaryBitmap();
//        fromPostBridge.setBitmap(true);
//        
//        String message = fromPostBridge.toString();
//        
//        //System.out.println("Response:"+fromPostBridge.toString());
//        
//        byte[] bcdmeslen = fromPostBridge.toBCD(String.format("%04x", message.length()));
//        String bcdmeslenChar = "";
//        for (int i = 0; i < bcdmeslen.length; i++) {
//            bcdmeslenChar += (char) bcdmeslen[i];
//        }
//
//        message = (bcdmeslenChar).concat(message);
//
//        byte[] ti = new byte[message.length()];
//
//        for (int k = 0; k < message.length(); k++) {
//            ti[k] = (byte) message.charAt(k);
//        }
//        
//        System.out.println("Response:"+new String(ti));
         
        return  fromPostBridge;
    }
}
