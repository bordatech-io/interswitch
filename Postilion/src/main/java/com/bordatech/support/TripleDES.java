/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.support;

/**
 *
 *
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * Copyright (c) 2000 David Flanagan.  All rights reserved.
 * This code is from the book Java Examples in a Nutshell, 2nd Edition.
 * It is provided AS-IS, WITHOUT ANY WARRANTY either expressed or implied.
 * You may study, use, and modify it for any non-commercial purpose.
 * You may distribute it non-commercially as long as you retain this notice.
 * For a commercial use license, or to purchase the book (recommended),
 * visit http://www.davidflanagan.com/javaexamples2.
 */
import com.bordatech.beans.ErrorCodes;
import com.bordatech.beans.ISOLogger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

/**
 * This class defines methods for encrypting and decrypting using the Triple DES
 * algorithm and for generating, reading and writing Triple DES keys. It also
 * defines a main() method that allows these methods to be used from the command
 * line.
 */
public class TripleDES {

    /**
     * Read a TripleDES secret key from a byte array
     */
    public static SecretKey readKey(byte[] rawkey) {
        // Read the raw bytes from the keyfile
        try {
            DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);

            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key;

            key = keyfactory.generateSecret(keyspec);
            key = keyfactory.translateKey(key);

            return key;
        } catch (InvalidKeySpecException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        } catch (NoSuchAlgorithmException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        } catch (InvalidKeyException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        }
    }

    public static SecretKey readSingleKey(byte[] rawkey) throws IOException,
            NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException,
            InvalidKeySpecException, IllegalBlockSizeException, NoSuchProviderException {
        // Read the raw bytes from the keyfile

        DESKeySpec keyspec = new DESKeySpec(rawkey);

        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyfactory.generateSecret(keyspec);

        return key;
    }

    public static String EncryptDES(Key key, String clearComp) throws NoSuchAlgorithmException, InvalidKeyException, IOException,
            IllegalBlockSizeException, NoSuchPaddingException,
            BadPaddingException {
        try {

            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            System.out.println(cipher.getOutputSize(3));
            byte[] clearText = hexToByte(clearComp);

            CipherOutputStream out = new CipherOutputStream(bytes, cipher);
            out.write(clearText);
            out.flush();
            out.close();
            byte[] ciphertext = bytes.toByteArray();
            bytes.flush();
            bytes.close();

            String encrypted = ToHexString(ciphertext);
            System.out.println("Enc 3: " + ToHexString(ciphertext));

            java.util.Arrays.fill(clearText, (byte) 0);
            java.util.Arrays.fill(ciphertext, (byte) 0);

            return encrypted;
        } catch (InvalidKeyException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        }

    }

    public static String Encrypt(Key key, String clearComp) {
        try {

            Cipher cipher;

            cipher = Cipher.getInstance("DESede/ECB/NoPadding");

            cipher.init(Cipher.ENCRYPT_MODE, key);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            byte[] clearText = hexToByte(clearComp);

            CipherOutputStream out = new CipherOutputStream(bytes, cipher);
            out.write(clearText);
            out.flush();
            out.close();
            byte[] ciphertext = bytes.toByteArray();
            bytes.flush();
            bytes.close();

            String encrypted = ToHexString(ciphertext);

            java.util.Arrays.fill(clearText, (byte) 0);
            java.util.Arrays.fill(ciphertext, (byte) 0);

            return encrypted;
        } catch (IOException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        } catch (NoSuchPaddingException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        } catch (NoSuchAlgorithmException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        } catch (InvalidKeyException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        }

    }

    public static String Decrypt(Key key, String cipherComp) {
        try {

            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, key);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            byte[] ciphertext = hexToByte(cipherComp);
            CipherOutputStream out;
            out = new CipherOutputStream(bytes, cipher);
            out.write(ciphertext);
            out.flush();
            out.close();
            byte[] deciphertext = bytes.toByteArray();
            bytes.flush();
            bytes.close();

            String decrypted = ToHexString(deciphertext);

            java.util.Arrays.fill(ciphertext, (byte) 0);
            java.util.Arrays.fill(deciphertext, (byte) 0);

            return decrypted;
        } catch (IOException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        } catch (NoSuchPaddingException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        } catch (NoSuchAlgorithmException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        } catch (InvalidKeyException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        }
    }

    public static String DecryptDES(Key key, String cipherComp) throws NoSuchAlgorithmException, InvalidKeyException, IOException,
            IllegalBlockSizeException, NoSuchPaddingException,
            BadPaddingException {
        try {
            System.out.println("cipher componnent: " + cipherComp);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            byte[] cipherText = hexToByte(cipherComp);
            CipherOutputStream out;
            out = new CipherOutputStream(bytes, cipher);
            out.write(cipherText);
            out.flush();
            out.close();
            byte[] deciphertext = bytes.toByteArray();
            bytes.flush();
            bytes.close();
            System.out.println("Dec 3: " + ToHexString(deciphertext));

            String decrypted = ToHexString(deciphertext);

            java.util.Arrays.fill(cipherText, (byte) 0);
            java.util.Arrays.fill(deciphertext, (byte) 0);

            return decrypted;
        } catch (InvalidKeyException ex) {
            String[] error = {ErrorCodes.EXCEPTION.toString(), "Caused:     ", ex.getLocalizedMessage()};
            ISOLogger.generateLog2(error);
            return null;
        }

    }

    public static String ToHexString(byte[] toAsciiData) {
        String hexString = "";

        for (byte b : toAsciiData) {
            hexString += String.format("%02X", b);
        }
        return hexString;
    }

    public static byte[] hexToByte(String hexString) {
        String str = new String("0123456789ABCDEF");
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0, j = 0; i < hexString.length(); i++) {
            byte firstQuad = (byte) ((str.indexOf(hexString.charAt(i))) << 4);
            byte secondQuad = (byte) str.indexOf(hexString.charAt(++i));
            bytes[j++] = (byte) (firstQuad | secondQuad);

        }
        return bytes;
    }

    public static String toAscii(String toAsciiData) {
        String hexString = "";
        char hexChars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        int i = 0;
        while (toAsciiData.length() > i) {

            int hbits = (toAsciiData.charAt(i) & 0x00f0) >>> 4;
            int lbits = (toAsciiData.charAt(i) & 0x000f);

            hexString += hexChars[hbits];
            hexString += hexChars[lbits];
            i++;
        }
        return hexString;
    }

    public static String toBCDStr(String hexString) {
        String bcd = "";
        String hex = new String("0123456789ABCDEF");
        for (int i = 0, j = 0; i < hexString.length(); i++, j++) {
            byte firstQuadByte = (byte) (hex.indexOf(hexString.toUpperCase().charAt(i)) << 4);
            byte secondQuadByte = (byte) (hex.indexOf(hexString.toUpperCase().charAt(++i)));
            bcd += (char) (firstQuadByte | secondQuadByte);
        }
        return bcd;
    }

    public static void main(String[] ad) {

        String key1 = "10101010101010101010101010101010";
        String key2 = "01010101010101010101010101010101";
        String key3 = "FDCE7989D5C2940DD63EBA867307A401";
        byte[] keyB1 = TripleDES.hexToByte(key1 + key1.substring(0, 16));
        byte[] keyB2 = TripleDES.hexToByte(key2 + key2.substring(0, 16));
        byte[] keyB3 = TripleDES.hexToByte(key3 + key3.substring(0, 16));

        for (int i = 0; i < keyB2.length; i++) {
            keyB1[i] = (byte) (((byte) (keyB1[i] ^ keyB2[i])));
        }

        SecretKey key = TripleDES.readKey(keyB1);

        /*String pin="045383FFFFFFFFFF";
        String pan="0000555535120488";

        byte[] pinb=TripleDES.hexToByte(pin);
        byte[] panb=TripleDES.hexToByte(pan);

        for(int i=0; i<pinb.length; i++) {
           pinb[i]=(byte)(pinb[i]^panb[i]);
       }


        System.out.println("The check value: "+TripleDES.ToHexString(keyB1));
         System.out.println("The pinblock value: "+TripleDES.Encrypt(key, ToHexString(pinb)));
     *
     * */
        //byte[] keyB3=TripleDES.hexToByte("00112233445566778899AABBCCDDEEFF0011223344556677");
        //SecretKey key=TripleDES.readKey(keyB1);
        System.out.println("The pinblock value: " + TripleDES.Encrypt(key, "0000000000000000"));
    }
}
