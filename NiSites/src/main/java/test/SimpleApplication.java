package test;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleApplication {

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    final TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
	    // tomcatFactory.setAddress(InetAddress.getLocalHost());// you can restrict localhost access
	    tomcatFactory.setPort(8080);

	    final Connector connector = new Connector();
	    connector.setPort(8443);
	    connector.setSecure(true);
	    connector.setScheme("https");
	    connector.setProperty("SSLEnabled", "true");
	    tomcatFactory.addAdditionalTomcatConnectors(connector);
	    return tomcatFactory;
	}
	 

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}
}
