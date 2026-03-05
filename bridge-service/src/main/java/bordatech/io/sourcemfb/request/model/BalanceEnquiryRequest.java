package bordatech.io.sourcemfb.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceEnquiryRequest {
    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_id")
    private String accountId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
