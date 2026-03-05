package bordatech.io.sourcemfb.cardFusion;

import bordatech.io.sourcemfb.cardFusion.models.Request.AccountEnquiryRequest;
import bordatech.io.sourcemfb.cardFusion.models.Request.IssuanceChargeRequest;
import bordatech.io.sourcemfb.cardFusion.models.Request.SendSMSRequest;
import bordatech.io.sourcemfb.cardFusion.models.Response.AccountEnquiryResponse;
import bordatech.io.sourcemfb.cardFusion.models.Response.IssuanceChargeResponse;
import bordatech.io.sourcemfb.cardFusion.models.Response.SendSMSResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.http.HttpStatus;

@RestController
public class CardFusion {
    private static final Logger logger = LogManager.getLogger(CardFusion.class);
    private final CardFileBatchService cardFileBatchService;


    public CardFusion(CardFileBatchService cardFileBatchService) {
        this.cardFileBatchService = cardFileBatchService;
    }

    @PostMapping(value = "/CardFusion/Account/AccountEnquiry")
    AccountEnquiryResponse accountEnquiryResponse (@RequestBody AccountEnquiryRequest accountEnquiryRequest) throws IOException {
        logger.info("++++++++++++++++++++++++++++++++");
        logger.info(accountEnquiryRequest.getAccountNo());
        logger.info("+++++++++++++++++++++++++++++++++");

//        customers.Customers.NameEnquiryResponse nameEnqiry; // = this.customerServiceRequest.NameEnquiryByAccount(accountEnquiryRequest.getAccountNo());
//        String PhoneNumber = "";
//        String bvn = "";

//        if(accountEnquiryRequest.getAccountNo().startsWith("2") || accountEnquiryRequest.getAccountNo().startsWith("6") || accountEnquiryRequest.getAccountNo().startsWith("7")){
//            GetCustomerByAccountResponsePojo getCustomerByAccount =  this.customerServiceRequest.GetCustomerByAccount(accountEnquiryRequest.getAccountNo(),"");
//            PhoneNumber = getCustomerByAccount.getGetCustomerResponse().getContactPhoneNumber();
//            bvn = getCustomerByAccount.getGetCustomerResponse().getKyc().getBvn();
//        }else {
//            GetCustomerByAccountResponsePojo getCustomerByAccount =  this.customerServiceRequest.getCorporateByAccountResponse(accountEnquiryRequest.getAccountNo(),"");
//            PhoneNumber = getCustomerByAccount.getGetCorporateByAccountResponse().getContactInfo().getContactPhoneNumber();
//            bvn = getCustomerByAccount.getGetCorporateByAccountResponse().getKyc().getBvn();
//        }
        AccountEnquiryResponse accountEnquiryResponse = new AccountEnquiryResponse();
//        BalanceEnqiryPojo balanceEnqiryPojo = transactionServiceRequest.BalanceEnquiry(accountEnquiryRequest.getAccountNo());

//        logger.info("+++++++++++++==+++++++++++++++++++");
//        logger.info(nameEnqiry.getAccountName());
//        logger.info("+++++++++++++==++++++++++++++++++++");

        // customers.Service.Customer Customer = this.customerServiceRequest.GetCustomerByAccount(accountEnquiryRequest.getAccountNo(),"");
        if (1<2/*nameEnqiry.getAccountName()!=null*/) {
//            accountEnquiryResponse.setBvn(nameEnqiry.getBvn().equals("NA")?bvn:nameEnqiry.getBvn());
//            accountEnquiryResponse.setName(nameEnqiry.getAccountName());
//            accountEnquiryResponse.setNuban(nameEnqiry.getAccountNumber());
//            accountEnquiryResponse.setPhoneNumber(PhoneNumber);
//            accountEnquiryResponse.setTier(nameEnqiry.getKycLevel());
//            accountEnquiryResponse.setResponseMessage("Customer Found");
//            accountEnquiryResponse.setPndStatus(false);
//            accountEnquiryResponse.setSuccessful(true);
//            accountEnquiryResponse.setCountryCode("NGN");
//            accountEnquiryResponse.setCurrencyCode("566");
//            accountEnquiryResponse.setCurrencyName("NGN");
//            accountEnquiryResponse.setLedgerBalance(balanceEnqiryPojo.getBalanceResponse().getLedgerBalance());
//            accountEnquiryResponse.setAvailableBalance(balanceEnqiryPojo.getBalanceResponse().getLedgerBalance());
        } else {
//            accountEnquiryResponse.setResponseMessage("Customer Not Found");
//            accountEnquiryResponse.setPndStatus(false);
//            accountEnquiryResponse.setSuccessful(false);
        }
        return accountEnquiryResponse;
    }

    @PostMapping(value = "/CardFusion/Account/IssuanceCharge")
    IssuanceChargeResponse issuanceChargeResponse (@RequestBody IssuanceChargeRequest issuanceChargeRequest){
        IssuanceChargeResponse issuanceChargeResponse = new IssuanceChargeResponse();
//        customers.Customers.NameEnquiryResponse nameEnqiry; //= this.customerServiceRequest.NameEnquiryByAccount(issuanceChargeRequest.getAccountToDebit());


        if (1<2/*nameEnqiry.getAccountName()!=null*/) {
            //post debit
//            issuanceChargeRequest.getAccountName()
//            DebitCreditPojo debitCreditPojo = new DebitCreditPojo();
//            debitCreditPojo.setNarration("Card IssuanceCharge");
//            debitCreditPojo.setAmount(issuanceChargeRequest.getAmount());
//            debitCreditPojo.setAccountstatus("active");
//            debitCreditPojo.setTransactionreference(issuanceChargeRequest.getRetrievalReference());
//            debitCreditPojo.setDrcr("db");
//            debitCreditPojo.setIsccode("5003");
//            debitCreditPojo.setAccountnumber(issuanceChargeRequest.getAccountToDebit());

           String responseCode ="";
//                   =  this.transactionServiceRequest.DebitCredit(
//                   debitCreditPojo
//           );
            logger.info("===============response code===============");
            logger.info(responseCode);
            logger.info("===============response code===============");
           if (responseCode.equals("00")) {
               issuanceChargeResponse.setResponseCode("00");
               issuanceChargeResponse.setResponseMessage("debited");
               issuanceChargeResponse.setReferenceId(issuanceChargeRequest.getRetrievalReference());
           } else {
               issuanceChargeResponse.setResponseCode("01");
               issuanceChargeResponse.setResponseMessage("not debited");
               issuanceChargeResponse.setReferenceId(issuanceChargeRequest.getRetrievalReference());
           }
        }
        return issuanceChargeResponse;
    }


    @PostMapping(value = "CardFusion/Account/SendSMS")
    SendSMSResponse sendSMSResponse (@RequestBody SendSMSRequest sendSMSRequest){
        SendSMSResponse sendSMSResponse = new SendSMSResponse();
        String PhoneNumber = "";

//        if(sendSMSRequest.getAccountNo().startsWith("2") || sendSMSRequest.getAccountNo().startsWith("6") || sendSMSRequest.getAccountNo().startsWith("7")){
//            GetCustomerByAccountResponsePojo getCustomerByAccount =  this.customerServiceRequest.GetCustomerByAccount(sendSMSRequest.getAccountNo(),"");
//            PhoneNumber = getCustomerByAccount.getGetCustomerResponse().getContactPhoneNumber();
//        }else {
//            GetCustomerByAccountResponsePojo getCustomerByAccount =  this.customerServiceRequest.getCorporateByAccountResponse(sendSMSRequest.getAccountNo(),"");
//            PhoneNumber = getCustomerByAccount.getGetCorporateByAccountResponse().getContactInfo().getContactPhoneNumber();
//        }
//
//        System.out.println("PhoneNumber: "+PhoneNumber);

        String response="200";  //= this.notificationServiceRequest.sendSMSNotification(sendSMSRequest.getMessageBody(), PhoneNumber.length()==10? "0"+PhoneNumber: PhoneNumber);

        if(response.equals("200")){
            sendSMSResponse.setResponseMessage("Message sent");
            sendSMSResponse.setResponseCode("00");
        }else{
            sendSMSResponse.setResponseMessage("Could not send message");
            sendSMSResponse.setResponseCode("01");
        }

        return sendSMSResponse;
    }

    @PostMapping(value = "/CardFusion/CardFiles/Generate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/zip")
    public ResponseEntity<byte[]> generateCardFilesZip(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] zipPayload;
        try {
            zipPayload = cardFileBatchService.generateCardFilesZip(file);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.setContentDisposition(ContentDisposition.attachment().filename("CARDFILES.zip").build());
        return ResponseEntity.ok().headers(headers).body(zipPayload);
    }
}
