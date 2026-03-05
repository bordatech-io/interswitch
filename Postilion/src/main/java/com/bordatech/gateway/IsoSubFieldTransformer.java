/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.gateway;

import com.bordatech.beans.ISO8583;
import com.bordatech.beans.ISOLogger;
import com.bordatech.support.ISO8583Exception;

/**
 *
 *
 */
public class IsoSubFieldTransformer {

    static ISOLogger log = new ISOLogger();

//Note that your algo is based on the lower byte portion of the char   
    public void getBitmap(ISO8583 data, String isoField) {
        int p = 8;

        int bits = p * 8;

        byte eBit = '\0';

        int sposition = p;
        for (int i = 0; i < bits; i++) {
            int m = (int) (i / 8);
            if ((i % 8) == 0) {
                eBit = '\0';
                byte hexForm = (byte) isoField.charAt(m);

                eBit = hexForm;
            }
            byte eBits = (byte) (eBit & (-128));

            if (eBits != '\0') {
                sposition = getField(data, isoField, i, sposition);
            }

            eBit = (byte) (eBit << 1);

        }
    }

    public String getValue(String isoField, int sposition, int len) {

        return isoField.substring(sposition, sposition + len);
    }

    public int getField(ISO8583 data, String isoField, int position, int sPosition) {
        position++;
        String fieldStr = "";
        int length = 0;
        switch (position) {
            case 1: {

                break;
            }
            case 2: {
                //this is for pan
                //use a function to get the value of two characters.

                try {

                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }

                data.setSwitchKey(fieldStr);

                break;
            }
            case 3: {
                //this is for processing codes
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 48);
                    sPosition += 48;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }

                data.setRoutingInformation(fieldStr);
                break;
            }
            case 4: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 22);
                    sPosition += 22;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }

                data.setPosData(fieldStr);

                break;
            }
            case 5: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 73);
                    sPosition += 73;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }

                data.setServiceStationData(fieldStr);
                break;
            }
            case 6: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setAuthorizationProfile(fieldStr);

                break;
            }
            case 7: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }

                data.setCheckData(fieldStr);

                break;
            }
            case 8: {
                try {
                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setRetentionData(fieldStr);
                break;
            }
            case 9: {
                try {
                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    if (length < 255) {
                        String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                        log.generateLog1(form);
                    }

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setAdditionalNodeData(fieldStr);

                break;
            }
            case 10: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setCvv2(fieldStr);

                break;
            }
            case 11: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setOriginalKey(fieldStr);
                break;
            }
            case 12: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setTerminalOwner(fieldStr);

                break;
            }
            case 13: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 17);
                    sPosition += 17;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setPosGeographicData(fieldStr);
                break;
            }
            case 14: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 8);
                    sPosition += 8;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setSponsorBank(fieldStr);
                break;
            }
            case 15: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setAddressVerificationData1(fieldStr);
                break;
            }
            case 16: {

                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 1);
                    sPosition += 1;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setAddressVerificationResult(fieldStr);
                break;
            }
            case 17: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setCardHolderInformation(fieldStr);
                break;
            }
            case 18: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setValidationData(fieldStr);
                break;
            }
            case 19: {
                try {

                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 31);
                    sPosition += 31;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setBankDetails(fieldStr);
                break;
            }
            case 20: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 8);
                    sPosition += 8;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setOriginatorDateSettlement(fieldStr);
                break;
            }
            case 21: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setRecordIdentification(fieldStr);

                break;
            }
            case 22: {
                try {
                    fieldStr += getValue(isoField, sPosition, 5);
                    sPosition += 5;
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setStructureData(fieldStr);
                break;
            }
            case 23: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 253);
                    sPosition += 253;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setPayeeNameAddress(fieldStr);
                break;
            }
            case 24: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setPayerAccount(fieldStr);

                break;
            }
            case 25: {
                try {
                    fieldStr += getValue(isoField, sPosition, 4);
                    sPosition += 4;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setIccData(fieldStr);
                break;
            }
            case 26: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setOriginalNode(fieldStr);

                break;
            }
            case 27: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 1);
                    sPosition += 1;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setCardVerificationResult(fieldStr);

                break;
            }
            case 28: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 4);
                    sPosition += 4;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setAmericanExpressCardId(fieldStr);

                break;
            }
            case 29: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 40);
                    sPosition += 40;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }

                data.setSecureData(fieldStr);
                break;
            }
            case 30: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 1);
                    sPosition += 1;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setSecureResult(fieldStr);
                break;
            }
            case 31: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setIssuerNetworkId(fieldStr);

                break;
            }
            case 32: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setUcafData(fieldStr);
                break;
            }
            case 33: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 4);
                    sPosition += 4;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setExtendedTransactionType(fieldStr);

                break;
            }
            case 34: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }
                data.setAccountTypeQualifier(fieldStr);

                break;
            }
            case 35: {
                fieldStr += getValue(isoField, sPosition, 2);
                sPosition += 2;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at 127th Subfield position", Integer.toString(sPosition)};
                    log.generateLog1(form);
                }

                data.setAcquirerNetworkId(fieldStr);

                break;
            }
            case 36: {

                break;
            }
            case 37: {

                break;
            }
            case 38: {

                break;
            }
            case 39: {

                break;
            }
            case 40: {

                break;
            }
            case 41: {

                break;
            }
            case 42: {

                break;
            }
            case 43: {

                break;
            }
            case 44: {

                break;
            }
            case 45: {

                break;
            }
            case 46: {

                break;
            }
            case 47: {

                break;
            }
            case 48: {

                break;
            }
            case 49: {

                break;
            }
            case 50: {

                break;
            }
            case 51: {

                break;
            }
            case 52: {

                break;
            }
            case 53: {

                break;
            }
            case 54: {

                break;
            }
            case 55: {

                break;
            }
            case 56: {

                break;
            }
            case 57: {

                break;
            }
            case 58: {

                break;
            }
            case 59: {

                break;
            }
            case 60: {

                break;
            }
            case 61: {

                break;
            }
            case 62: {

                break;
            }
            case 63: {

                break;
            }
            case 64: {

                break;
            }
            default: {
                throw new ISO8583Exception();

            }
        }
        return sPosition;
    }

}
