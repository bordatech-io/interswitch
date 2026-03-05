package bordatech.io.sourcemfb.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameEnquiryRequest {
    @JsonProperty("account_number")
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
