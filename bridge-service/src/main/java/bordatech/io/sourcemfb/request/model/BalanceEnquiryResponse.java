package bordatech.io.sourcemfb.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class BalanceEnquiryResponse {
    @JsonProperty("effective_balance")
    private double effectiveBalance;

    @JsonProperty("ledger_balance")
    private double ledgerBalance;


    @JsonProperty("response_code")
    private String responseCode;




}
