package bordatech.io.sourcemfb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ConvertToJson {
    public static String setJsonString(Object content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(content);
            return jsonInString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public String convertXMLtoJSON(String xml) throws Exception {
        ObjectMapper xmlMapper = new XmlMapper();
        Object obj = xmlMapper.readValue(xml, Object.class);
        ObjectMapper jsonMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        return jsonMapper.writeValueAsString(obj);
    }
}