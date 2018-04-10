package business;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import business.DTO.UserDTO;

@RestController
@RequestMapping("/api/business/user")
public class UserLogic {

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDTO[] getUserList() {
		RestTemplate restTemplate = new RestTemplate();
		UserDTO[] listUsers = restTemplate.getForObject("http://localhost:8080/api/repository/user/", UserDTO[].class);		
		return listUsers;
	}
}