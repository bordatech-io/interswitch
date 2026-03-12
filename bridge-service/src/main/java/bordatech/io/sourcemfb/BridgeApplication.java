package bordatech.io.sourcemfb;

import bordatech.io.sourcemfb.iso8583.Iso8583Service;
import bordatech.io.sourcemfb.utils.SaveToLog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
public class BridgeApplication {
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	@Bean
	public SaveToLog saveToLog(){ return new SaveToLog();}

	public static void main(String[] args) {
		SpringApplication.run(BridgeApplication.class, args);
		System.out.println("Bridge Application now running...17.85k");
	}

	@Bean
	CommandLineRunner run(Iso8583Service iso8583Service) {
		System.out.println("=======Iso8583Service starting========");
		return args -> iso8583Service.start();
	}
}