package business;

import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import business.DTO.UserDTO;

@RestController
@RequestMapping("/api/business/user")
public class UserLogic {

	String url = "http://localhost:8080/api/repository/user/";
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDTO[] getUserList() {
		UserDTO[] listUsers = restTemplate.getForObject(this.url, UserDTO[].class);
		return listUsers;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addUser(@RequestBody UserDTO userDTO) {
		restTemplate.postForEntity(this.url, userDTO, UserDTO.class);
	}


	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void editUser(@RequestBody UserDTO userDTO) {
		restTemplate.put(this.url, userDTO, UserDTO.class);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserById(@PathVariable UUID userId) {
		UserDTO userDTO = restTemplate.getForObject(this.url + userId.toString(), UserDTO.class);
		return userDTO;
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserByEmail(@PathVariable String email) {
		UserDTO userDTO = restTemplate.getForObject(this.url + "email/" + email, UserDTO.class);
		return userDTO;
	}
}
