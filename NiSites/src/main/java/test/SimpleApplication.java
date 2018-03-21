package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import rest.services.UserController;

@SpringBootApplication
@ComponentScan(basePackages ={"rest.services"} ,basePackageClasses =UserController.class)
@EntityScan({"entities.domain"})
@EnableJpaRepositories("entities.repositoryInterfaces")
public class SimpleApplication {

	public static void main(String[] args) {
		
	/*	ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("classpath:/spring.xml");

		IUserRepository userRepository = ctx.getBean(IUserRepository.class);		
		User user= new User();
		user.setName("secondUser");
		user.setEmail("secondEmail");
		user.setPassword("pass");
		user.setCreationDate(new Date());
		userRepository.save(user);
		ctx.close();*/
        SpringApplication.run(SimpleApplication.class, args);

	}
}
