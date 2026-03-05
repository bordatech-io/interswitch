package bordatech.io.sourcemfb.iso8583;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Iso8583Service {

    @Value("${iso8583.port}")
    private String port;
    private final Iso8583Server server;
    private final Iso8583Client client;

    public Iso8583Service(Iso8583Server server, Iso8583Client client) {
        this.server = server;
        this.client = client;
    }

    public void start() throws Exception {
        // Start the server in a new thread
        new Thread(() -> {
            try {
                server.startServer(Integer.parseInt(port));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}


