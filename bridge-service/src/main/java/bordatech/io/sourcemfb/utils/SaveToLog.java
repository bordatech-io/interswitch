package bordatech.io.sourcemfb.utils;

import bordatech.io.sourcemfb.entities.LogEntity;
import bordatech.io.sourcemfb.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class SaveToLog {

    @Autowired LogRepository logRepository;

    public void saveToLogTable(
            String sessionId,
            String requestXML,
            String encryptedRequest,
            String responseXML,
            String type,
            String service) {
        LogEntity logEntity = new LogEntity();
        logEntity.setSessionId(sessionId);
        logEntity.setRequestXml(requestXML);
        logEntity.setEncryptedRequest(encryptedRequest);
        logEntity.setResponseXml(responseXML);
        logEntity.setType(type);
        logEntity.setService(service);
        logEntity.setTimestamp(LocalDateTime.now());

//        logRepository.save(logEntity);
    }
}
