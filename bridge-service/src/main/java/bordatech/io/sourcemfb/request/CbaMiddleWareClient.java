package bordatech.io.sourcemfb.request;

import bordatech.io.sourcemfb.request.model.*;

public interface CbaMiddleWareClient {
    BalanceEnquiryResponse balanceEnquiry(String accountNumber);

    BalanceEnquiryResponse balanceEnquiry(String accountNumber, String accountId);

    NameEnquiryResponse nameEnquiry(String accountNumber);

    TransactionResponse debit(DebitRequest request);

    TransactionResponse reversal(ReversalRequest request);

    TransactionResponse block(BlockRequest request);

    TransactionResponse unblock(BlockRequest request);
}
