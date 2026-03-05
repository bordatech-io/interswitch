package bordatech.io.sourcemfb.cardFusion.models.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountEnquiryResponse {

    private String name;
    private String nuban;
    private String phoneNumber;
    private String bvn;
    private Double availableBalance;
    private Double ledgerBalance;
    private String tier;
    private String responseMessage;
    private boolean pndStatus;

    @JsonProperty("isSuccessful") // This customizes the JSON field name
    private boolean isSuccessful;

    private String currencyCode;
    private String countryCode;
    private String currencyName;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNuban() {
        return nuban;
    }

    public void setNuban(String nuban) {
        this.nuban = nuban;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Double getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(Double ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public boolean isPndStatus() {
        return pndStatus;
    }

    public void setPndStatus(boolean pndStatus) {
        this.pndStatus = pndStatus;
    }

    @JsonProperty("isSuccessful")
    public boolean isSuccessful() {
        return isSuccessful;
    }

    @JsonProperty("isSuccessful")
    public void setSuccessful(boolean successful) {
        this.isSuccessful = successful;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
