package run;

import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"presentation"})
@EntityScan({"models"})
public class StartPresentation {

	public static void main(String[] args) {

		HashMap<String, Object> props = new HashMap<>();
		props.put("server.port", 8082);

		new SpringApplicationBuilder()
		    .sources(StartPresentation.class)                
		    .properties(props)
		    .run(args);
	}

}
