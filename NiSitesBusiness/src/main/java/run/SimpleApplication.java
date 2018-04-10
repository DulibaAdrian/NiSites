package run;

import java.util.HashMap;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"business"})
@EntityScan({"business.DTO"})
public class SimpleApplication {

	public static void main(String[] args) {

		HashMap<String, Object> props = new HashMap<>();
		props.put("server.port", 9999);

		new SpringApplicationBuilder()
		    .sources(SimpleApplication.class)                
		    .properties(props)
		    .run(args);
		
	}
}