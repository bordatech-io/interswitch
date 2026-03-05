package com.bordatech.postilion;

import java.util.Map;

public class IsoMessage {
    private final Map<String, String> fields;

    public IsoMessage(Map<String, String> fields) {
        this.fields = fields;
    }

    public String getPan() {
        return fields.get("2");
    }

    public String getProcessingCode() {
        return fields.get("3");
    }

    public String getAmountTransaction() {
        return fields.get("4");
    }

    public String getTransmissionDateTime() {
        return fields.get("7");
    }

    public String getStan() {
        return fields.get("11");
    }

    public String getLocalTime() {
        return fields.get("12");
    }

    public String getLocalDate() {
        return fields.get("13");
    }

    public String getExpiryDate() {
        return fields.get("14");
    }

    public String getMerchantType() {
        return fields.get("18");
    }

    public String getPosEntryMode() {
        return fields.get("22");
    }

    public String getCardSequenceNumber() {
        return fields.get("23");
    }

    public String getPosConditionCode() {
        return fields.get("25");
    }

    public String getTerminalId() {
        return fields.get("41");
    }

    public String getCardAcceptorId() {
        return fields.get("42");
    }

    public String getLocation() {
        return fields.get("43");
    }

    public String getCurrencyCode() {
        return fields.get("49");
    }

    public String getPinBlock() {
        return fields.get("52");
    }

    public String getSubField127_02() {
        return fields.get("127.02");
    }

    public String getSubField127_33() {
        return fields.get("127.33");
    }

    // Add more as needed...
}

