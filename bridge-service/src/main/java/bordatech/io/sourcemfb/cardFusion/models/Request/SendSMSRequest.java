package bordatech.io.sourcemfb.cardFusion.models.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendSMSRequest {
    @JsonProperty("messageBody")
    private String messageBody;
    @JsonProperty("accountNo")
    private String accountNo;

    public SendSMSRequest(){

    }

    public SendSMSRequest(String messageBody,String accountNo){
        this.messageBody = messageBody;
        this.setAccountNo(accountNo);
    }

}
