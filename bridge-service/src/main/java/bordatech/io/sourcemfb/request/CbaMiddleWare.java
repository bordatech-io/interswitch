package bordatech.io.sourcemfb.request;

import bordatech.io.sourcemfb.request.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Collections;

@Service
public class CbaMiddleWare implements CbaMiddleWareClient {
    private static final Logger logger = LogManager.getLogger(CbaMiddleWare.class);

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String defaultAccountId;

    public CbaMiddleWare(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${cba.middleware.base-url}") String baseUrl,
            @Value("${cba.middleware.username:}") String username,
            @Value("${cba.middleware.password:}") String password,
            @Value("${cba.middleware.default-account-id:}") String defaultAccountId
    ) {
        this.baseUrl = normalizeBaseUrl(baseUrl);
        this.defaultAccountId = defaultAccountId;

        RestTemplateBuilder builder = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(15))
                .setReadTimeout(Duration.ofSeconds(30));

        if (!username.isEmpty() && !password.isEmpty()) {
            builder = builder.basicAuthentication(username, password);
        }

        this.restTemplate = builder.build();
    }

    @Override
    public BalanceEnquiryResponse balanceEnquiry(String accountNumber) {
        return balanceEnquiry(accountNumber, defaultAccountId);
    }

    @Override
    public BalanceEnquiryResponse balanceEnquiry(String accountNumber, String accountId) {
        BalanceEnquiryRequest request = new BalanceEnquiryRequest();
        request.setAccountNumber(accountNumber);
        request.setAccountId(accountId);

        return post("/balance-enquiry", request, BalanceEnquiryResponse.class);
    }

    @Override
    public NameEnquiryResponse nameEnquiry(String accountNumber) {
        NameEnquiryRequest request = new NameEnquiryRequest();
        request.setAccountNumber(accountNumber);

        return post("/name-enquiry", request, NameEnquiryResponse.class);
    }

    @Override
    public TransactionResponse debit(DebitRequest request) {
        return post("/debit", request, TransactionResponse.class);
    }

    @Override
    public TransactionResponse reversal(ReversalRequest request) {
        return post("/reversal", request, TransactionResponse.class);
    }

    @Override
    public TransactionResponse block(BlockRequest request) {
        return post("/block-amount", request, TransactionResponse.class);
    }

    @Override
    public TransactionResponse unblock(BlockRequest request) {
        return post("/unblock-amount", request, TransactionResponse.class);
    }

    private <T> T post(String path, Object body, Class<T> responseType) {
        String url = baseUrl + path;
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, defaultHeaders());

        try {
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
            return response.getBody();
        } catch (RestClientException ex) {
            logger.error("Middleware call failed for endpoint {}: {}", path, ex.getMessage(), ex);
            throw new IllegalStateException("Unable to call middleware endpoint: " + path, ex);
        }
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private String normalizeBaseUrl(String baseUrl) {
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Property cba.middleware.base-url is required");
        }

        String trimmed = baseUrl.trim();
        return trimmed.endsWith("/") ? trimmed.substring(0, trimmed.length() - 1) : trimmed;
    }
}
