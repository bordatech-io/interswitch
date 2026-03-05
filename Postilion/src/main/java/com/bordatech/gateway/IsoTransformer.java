/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bordatech.gateway;

import com.bordatech.beans.ISO8583;
import com.bordatech.beans.ISOLogger;

/**
 *
 *
 */
public class IsoTransformer {

    static ISOLogger log = new ISOLogger();

//Note that your algo is based on the lower byte portion of the char   
    public void getBitmap(ISO8583 data, String isoField) {
        /*
         * Please, check if secondary bitmap is checked b4 proceeding. 
         Ve u checked it?? Y
         * ***/

        int p = 16;
        byte sBitmap = '\0';
        //sBitmap = (byte) ((hexFormOf(isoField.charAt(8), isoField.charAt(9), 2)) & 0x80);
        //this changes is made to process the string the immediately after it is received from the postilion
        sBitmap = (byte) ((byte) isoField.charAt(4) & 0x80);
        if (sBitmap == 0x00) {
            p = 8;
        }
        int bits = (p) * 8;
        //String bitmap = isoField.substring(8, p + 8);

        byte eBit = '\0';
        int n = 4;
        int sposition = p + 4;
        for (int i = 0; i < bits; i++) {
            int m = n + (int) (i / 8);
            if ((i % 8) == 0) {
                eBit = '\0';
                //byte hexForm = hexFormOf(isoField.charAt(m), isoField.charAt(++m), 2);
                //eBit = hexFormOf(isoField.charAt(m), isoField.charAt(++m), 2);
                eBit = (byte) isoField.charAt(m);
            }
            byte eBits = (byte) (eBit & (-128));    //~(0x7f)

            if (eBits != 0) {
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

                String[] forms = {"Field start at position: ", Integer.toString(sPosition)};
                log.generateLog1(forms);
                try {

                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";
                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;

                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setPan(fieldStr);

                break;
            }
            case 3: {
                //this is for processing codes
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 6);
                    sPosition += 6;

                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setProcessingCodes(fieldStr);
                break;
            }
            case 4: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 12);
                    sPosition += 12;

                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setAmountTransaction(fieldStr);

                break;
            }
            case 5: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 12);
                    sPosition += 12;

                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setAmountSettlement(fieldStr);
                break;
            }
            case 6: {

                break;
            }
            case 7: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setTransmissionDateTime(fieldStr);

                break;
            }
            case 8: {

                break;
            }
            case 9: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 8);
                    sPosition += 8;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                Double value = Double.parseDouble(fieldStr.substring(1));
                value = value / (Math.pow(10, Integer.parseInt(fieldStr.substring(0, 1))));
                data.setConversionRate("" + value);
                break;
            }
            case 10: {

                break;
            }
            case 11: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 6);
                    sPosition += 6;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setSystemTraceAudit(fieldStr);

                break;
            }
            case 12: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 6);
                    sPosition += 6;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setTimeLocalTransaction(fieldStr);
                break;
            }
            case 13: {

                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 4);
                    sPosition += 4;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDateLocalTransaction(fieldStr);
                break;
            }
            case 14: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 4);
                    sPosition += 4;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDateExpiration(fieldStr);

                break;
            }
            case 15: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 4);
                    sPosition += 4;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDateSettlement(fieldStr);
                break;
            }
            case 16: {
                try {
                    fieldStr = "";
                    fieldStr += getValue(isoField, sPosition, 4);
                    sPosition += 4;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDateConversion(fieldStr);

                break;
            }
            case 17: {

                break;
            }
            case 18: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 4);
                    sPosition += 4;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setMerchantType(fieldStr);

                break;
            }
            case 19: {

                break;
            }
            case 20: {

                break;
            }
            case 21: {

                break;
            }
            case 22: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setPosEntryMode(fieldStr);

                break;
            }
            case 23: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;

                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCardSequenceNumber(fieldStr);

                break;
            }
            case 24: {

                break;
            }
            case 25: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setPosConditionCode(fieldStr);

                break;
            }
            case 26: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setPosPINCaptureCode(fieldStr);

                break;
            }
            case 27: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 1);
                    sPosition += 1;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAuthorizationIdResponseLength(fieldStr);

                break;
            }
            case 28: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 9);
                    sPosition += 9;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAmountTransactionFee(fieldStr);

                break;
            }
            case 29: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 9);
                    sPosition += 9;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAmountSettlementFee(fieldStr);

                break;
            }
            case 30: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 9);
                    sPosition += 9;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAmountTransactionProcessingFee(fieldStr);

                break;
            }
            case 31: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 9);
                    sPosition += 9;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAmountSettlementProcessingFee(fieldStr);

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
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAcquiringInstitutionIdCode(fieldStr);
                break;
            }
            case 33: {
                try {
                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setForwardingInstitutionIdCode(fieldStr);
                break;
            }
            case 34: {

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
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setTrack2Data(fieldStr);

                break;
            }
            case 36: {

                break;
            }
            case 37: {
                try {

                    fieldStr += getValue(isoField, sPosition, 12);
                    sPosition += 12;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setRetrievalRefrenceNumber(fieldStr);
                break;
            }
            case 38: {
                try {

                    fieldStr += getValue(isoField, sPosition, 6);
                    sPosition += 6;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAuthorizationIdResponse(fieldStr);
                break;
            }
            case 39: {
                try {

                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setResponseCode(fieldStr);
                break;
            }
            case 40: {
                try {

                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setServiceRestrictionCode(fieldStr);
                break;
            }
            case 41: {
                try {

                    fieldStr += getValue(isoField, sPosition, 8);
                    sPosition += 8;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCardAcceptorTerminalId(fieldStr);
                break;
            }
            case 42: {
                try {

                    fieldStr += getValue(isoField, sPosition, 15);
                    sPosition += 15;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCardAcceptorIdCode(fieldStr);
                break;
            }
            case 43: {
                try {

                    fieldStr += getValue(isoField, sPosition, 40);
                    sPosition += 40;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCardAcceptorIdLocation(fieldStr);
                break;
            }
            case 44: {
                fieldStr += getValue(isoField, sPosition, 2);
                sPosition += 2;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAdditionalResponseData(fieldStr);
                break;
            }
            case 45: {
                fieldStr += getValue(isoField, sPosition, 2);
                sPosition += 2;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setTrack1Data(fieldStr);
                break;
            }
            case 46: {

                break;
            }
            case 47: {

                break;
            }
            case 48: {
                fieldStr += getValue(isoField, sPosition, 3);
                sPosition += 3;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";
                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAdditionalData(fieldStr);

                break;
            }

            case 49: {
                try {

                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setCurrencyCodeTransaction(fieldStr);
                break;
            }
            case 50: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCurrencyCodeSettlement(fieldStr);
                break;
            }
            case 51: {

                break;
            }
            case 52: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 8);
                    sPosition += 8;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setPINData(fieldStr);
                break;
            }
            case 53: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 48);
                    sPosition += 48;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setSecurityRelatedControlCode(fieldStr);
                break;
            }
            case 54: {
                fieldStr += getValue(isoField, sPosition, 3);
                sPosition += 3;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAdditionalAmount(fieldStr);
                break;
            }
            case 55: {

                break;
            }
            case 56: {
                fieldStr += getValue(isoField, sPosition, 3);
                sPosition += 3;
                try {
                    int val = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, val);
                    sPosition += val;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setMessageReasonCode(fieldStr);
                break;
            }
            case 57: {
                fieldStr += getValue(isoField, sPosition, 3);
                sPosition += 3;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};

                    log.generateLog1(form);
                }
                data.setAuthorizationLifeCycle(fieldStr);
                break;
            }
            case 58: {
                fieldStr += getValue(isoField, sPosition, 2);
                sPosition += 2;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAuthorizationAgentInstitution(fieldStr);
                break;
            }
            case 59: {
                fieldStr += getValue(isoField, sPosition, 3);
                sPosition += 3;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};

                    log.generateLog1(form);
                }

                data.setEchoData(fieldStr);
                break;
            }
            case 60: {

                break;
            }
            case 61: {

                break;
            }
            case 62: {
                fieldStr += getValue(isoField, sPosition, 3);
                sPosition += 3;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};

                    log.generateLog1(form);
                }

                data.setExtTranAttrData(fieldStr);
                break;
            }
            case 63: {

                break;
            }
            case 64: {

                break;
            }
            case 65: {

                break;
            }
            case 66: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 1);
                    sPosition += 1;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};

                    log.generateLog1(form);
                }
                data.setSettlementCode(fieldStr);
                break;
            }
            case 67: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 2);
                    sPosition += 2;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};

                    log.generateLog1(form);
                }
                data.setExtendedPaymentCode(fieldStr);
                break;
            }
            case 69: {

                break;
            }
            case 70: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setNetworkInformationManagementCode(fieldStr);
                break;
            }
            case 71: {

                break;
            }
            case 72: {

                break;
            }
            case 73: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 6);
                    sPosition += 6;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDateAction(fieldStr);

                break;
            }
            case 74: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCreditNumber(fieldStr);
                break;
            }
            case 75: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCreditReversalNumber(fieldStr);
                break;
            }
            case 76: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDebitNumber(fieldStr);
                break;
            }
            case 77: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDebitReversalNumber(fieldStr);
                break;
            }
            case 78: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setTransferNumber(fieldStr);
                break;
            }
            case 79: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setTransferReferenceNumber(fieldStr);
                break;
            }
            case 80: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setInquiriesNumber(fieldStr);
                break;
            }
            case 81: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAuthorizationNumber(fieldStr);
                break;
            }
            case 82: {

                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 12);
                    sPosition += 12;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCreditProcessingFeeAmount(fieldStr);
                break;
            }
            case 83: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 12);
                    sPosition += 12;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCreditTransactionFeeAmount(fieldStr);
                break;
            }
            case 84: {

                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 12);
                    sPosition += 12;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDebitProcessingFeeAmount(fieldStr);
                break;
            }
            case 85: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 12);
                    sPosition += 12;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDebitTransactionFeeAmount(fieldStr);
                break;
            }
            case 86: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 16);
                    sPosition += 16;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCreditAmount(fieldStr);
                break;
            }
            case 87: {

                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 16);
                    sPosition += 16;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setCreditReversalAmount(fieldStr);
                break;
            }
            case 88: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 16);
                    sPosition += 16;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }

                data.setDebitAmount(fieldStr);
                break;
            }
            case 89: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 16);
                    sPosition += 16;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setDebitReversalAmount(fieldStr);

                break;
            }
            case 90: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 42);
                    sPosition += 42;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setOriginalDataElement(fieldStr);
                break;
            }
            case 91: {

                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 1);
                    sPosition += 1;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setFileUpdateCode(fieldStr);
                break;
            }
            case 92: {

                break;
            }
            case 93: {

                break;
            }
            case 94: {

                break;
            }
            case 95: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 42);
                    sPosition += 42;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setReplacementAmount(fieldStr);

                break;
            }
            case 96: {

                break;
            }
            case 97: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 17);
                    sPosition += 17;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAmountNetSettlement(fieldStr);

                break;
            }
            case 98: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 25);
                    sPosition += 25;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setPayee(fieldStr);

                break;
            }
            case 99: {

                break;
            }
            case 100: {
                fieldStr += getValue(isoField, sPosition, 2);
                sPosition += 2;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setReceivingInstitutionIdCode(fieldStr);
                break;
            }
            case 101: {

                fieldStr += getValue(isoField, sPosition, 2);
                sPosition += 2;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setFileName(fieldStr);
                break;
            }
            case 102: {

                fieldStr += getValue(isoField, sPosition, 2);
                sPosition += 2;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAccountIdNumber1(fieldStr);
                break;
            }
            case 103: {

                fieldStr += getValue(isoField, sPosition, 2);
                sPosition += 2;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setAccountIdNumber2(fieldStr);
                break;
            }
            case 118: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setPaymentNumber(fieldStr);
                break;
            }
            case 119: {
                try {
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, 10);
                    sPosition += 10;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setPaymentReversalNumber(fieldStr);

                break;
            }
            case 123: {
                fieldStr += getValue(isoField, sPosition, 3);
                sPosition += 3;
                try {
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;
                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setPosDataCode(fieldStr);

                break;
            }
            case 107: {

                break;
            }
            case 108: {

                break;
            }
            case 125: {
                try {

                    fieldStr += getValue(isoField, sPosition, 3);
                    sPosition += 3;

                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";
                    fieldStr += getValue(isoField, sPosition, length);
                    sPosition += length;

                } catch (NumberFormatException e) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field position", Integer.toString(sPosition), e.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                data.setNetworkManagementInformation(fieldStr);
                break;
            }
            case 127: {
                try {
                    IsoSubFieldTransformer isoSubFieldTransformer = new IsoSubFieldTransformer();
                    fieldStr += getValue(isoField, sPosition, 6);
                    sPosition += 6;
                    length = Integer.parseInt(fieldStr);
                    fieldStr = "";

                    fieldStr = isoField.substring(sPosition, isoField.length());
                    
                    //String[] form = {"FIELD 127", fieldStr, fieldStr};
                    //log.generateLog1(form);

                    String bitmap = fieldStr.substring(0, 16);
                    String fields = fieldStr.substring(16);
                    fieldStr = bitmap + fields;
                    //fieldStr = hexToAscii(bitmap) + fields;
                    
                    isoSubFieldTransformer.getBitmap(data, fieldStr);
                    sPosition += length;
                } catch (Exception ex) {
                    String[] form = {"Fatal Error: Unexpected Data Format at Field 127 position", Integer.toString(sPosition), ex.getLocalizedMessage()};
                    log.generateLog1(form);
                }
                break;
            }
            case 128: {

                break;
            }

        }
        return sPosition;
    }
    
    private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    public void getMti(ISO8583 data, String isoField) {
        String fieldStr = "";
        int sPosition = 0;

        fieldStr += getValue(isoField, sPosition, 4);

        data.setMti(fieldStr);

    }
}
