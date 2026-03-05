package bordatech.io.sourcemfb.cardFusion.models.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssuanceChargeRequest {

    @JsonProperty("accountToDebit")
    private String accountToDebit;
    @JsonProperty("accountName")
    private  String accountName;
    @JsonProperty("cardType")
    private  String cardType;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("retrievalReference")
    private String retrievalReference;
    @JsonProperty("branchCode")
    private String branchCode;



    public IssuanceChargeRequest(){

    }
    public IssuanceChargeRequest(
            String accountToDebit,
            String accountName,
            String cardType,
            double amount,
            String retrievalReference,
            String branchCode
    ){
            this.accountName = accountName;
            this.accountToDebit = accountToDebit;
            this.cardType = cardType;
            this.branchCode = branchCode;
            this.setAmount(amount);
            this.setRetrievalReference(retrievalReference);
    }
}
