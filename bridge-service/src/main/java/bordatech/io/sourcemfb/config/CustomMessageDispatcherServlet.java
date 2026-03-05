package bordatech.io.sourcemfb.config;

import org.springframework.ws.transport.http.MessageDispatcherServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomMessageDispatcherServlet extends MessageDispatcherServlet {

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Type", "text/xml;charset=UTF-8");
        super.doService(request, response);
    }
}

