/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bordatech.postilion;

import com.bordatech.beans.ISO8583;

/**
 *
 * @author xpat
 */
public class WriteMessage {
    
    public static byte[] WriteToPostilion(ISO8583  toPostBridge){
        
        toPostBridge.setBitmapsb(true);
        String subfields = toPostBridge.toStringsb();
        String length = String.format("%06d", subfields.length());
        toPostBridge.setISO8583subfield(length + subfields);

        toPostBridge.setSecondaryBitmap();
        toPostBridge.setBitmap(true);
        
        String message = toPostBridge.toString();
        
        //System.out.println("Response:"+fromPostBridge.toString());
        
        byte[] bcdmeslen = toPostBridge.toBCD(String.format("%04x", message.length()));
        String bcdmeslenChar = "";
        for (int i = 0; i < bcdmeslen.length; i++) {
            bcdmeslenChar += (char) bcdmeslen[i];
        }

        message = (bcdmeslenChar).concat(message);

        byte[] ti = new byte[message.length()];

        for (int k = 0; k < message.length(); k++) {
            ti[k] = (byte) message.charAt(k);
        }
        
        System.out.println("Response:"+new String(ti));
        
        return ti;
    }

    public static byte[] WriteToPostilionWIthSub(ISO8583  toPostBridge){

        toPostBridge.setBitmapsb(true);
        String subfields = toPostBridge.toStringsb();
        String length = String.format("%06d", subfields.length());
//        toPostBridge.setISO8583subfield(length + subfields);

        toPostBridge.setSecondaryBitmap();
        toPostBridge.setBitmap(true);

        String message = toPostBridge.toString();

        //System.out.println("Response:"+fromPostBridge.toString());

        byte[] bcdmeslen = toPostBridge.toBCD(String.format("%04x", message.length()));
        String bcdmeslenChar = "";
        for (int i = 0; i < bcdmeslen.length; i++) {
            bcdmeslenChar += (char) bcdmeslen[i];
        }

        message = (bcdmeslenChar).concat(message);

        byte[] ti = new byte[message.length()];

        for (int k = 0; k < message.length(); k++) {
            ti[k] = (byte) message.charAt(k);
        }

        System.out.println("Response:"+new String(ti));

        return ti;
    }
}
