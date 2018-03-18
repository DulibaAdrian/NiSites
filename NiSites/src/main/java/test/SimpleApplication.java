package test;

import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import entities.domain.User;
import entities.repositoryInterfaces.IUserRepository;

public class SimpleApplication {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = 
				new ClassPathXmlApplicationContext("classpath:/spring.xml");

		IUserRepository userRepository = ctx.getBean(IUserRepository.class);		
		User user= new User();
		user.setName("secondUser");
		user.setEmail("secondEmail");
		user.setPassword("pass");
		user.setCreationDate(new Date());
		user.setSiteId(1);
		userRepository.save(user);
		ctx.close();
	}
}
