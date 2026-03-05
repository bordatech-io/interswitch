package bordatech.io.sourcemfb.cardFusion.models.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountEnquiryRequest {
    @JsonProperty("AccountNo")
    private String AccountNo;

    public AccountEnquiryRequest(){}

    public AccountEnquiryRequest(String AccountNo){
        this.AccountNo = AccountNo;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }
}
