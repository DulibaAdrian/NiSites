package test;

import java.util.Arrays;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import business.DTO.UserDTO;

@SpringBootApplication
public class SimpleApplication {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();
		UserDTO[] listUsers = restTemplate.getForObject("http://localhost:8080/api/user/", UserDTO[].class);
		System.out.println(Arrays.toString(listUsers));
	}
}
