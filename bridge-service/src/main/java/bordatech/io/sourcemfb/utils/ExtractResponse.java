package bordatech.io.sourcemfb.utils;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class ExtractResponse {
    public String returnValue (String xmlContent) {
        String returnValue = null;
        // Create an instance of Jaxb2Marshaller
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        // Set context path to the package where your generated classes are located
        marshaller.setContextPath("com.example.generated");

        // Create a WebServiceTemplate
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);

        try {
            // Parse XML content into a Document object
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlContent)));

            // Get the value inside the <return> tag
            NodeList returnNodes = document.getElementsByTagName("return");
            if (returnNodes.getLength() > 0) {
                returnValue = returnNodes.item(0).getTextContent();
                System.out.println("Value inside <return> tag: " + returnValue);
            } else {
                System.out.println("No <return> tag found.");
                returnValue = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }


    public String requestValue (String xmlContent) {
        String requestValue = null;
        // Create an instance of Jaxb2Marshaller
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        // Set context path to the package where your generated classes are located
        marshaller.setContextPath("org.neptunesystem.webservices.soap.bridge.nip.nibss");

        // Create a WebServiceTemplate
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);

        try {
            // Parse XML content into a Document object
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlContent)));

            // Get the value inside the <return> tag
            NodeList requestNodes = document.getElementsByTagName("request");
            if (requestNodes.getLength() > 0) {
                requestValue = requestNodes.item(0).getTextContent();
                System.out.println("Value inside <request> tag: " + requestValue);
            } else {
                System.out.println("No <request> tag found.");
                requestValue = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestValue;
    }
}
