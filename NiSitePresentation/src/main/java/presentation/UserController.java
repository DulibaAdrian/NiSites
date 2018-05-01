package presentation;

import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import models.UserModel;

@RestController
@RequestMapping("/api/presentation/user")
public class UserController {

	String userUrl = "http://localhost:8081/api/business/user/";
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserModel[] getUserList() {
		UserModel[] listUsers = this.restTemplate.getForObject(this.userUrl, UserModel[].class);
		return listUsers;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addUser(@RequestBody UserModel userModel) {
		this.restTemplate.postForEntity(this.userUrl, userModel, UserModel.class);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserModel getUserById(@PathVariable UUID userId) {
		UserModel userDTO = this.restTemplate.getForObject(this.userUrl + userId.toString(), UserModel.class);
		return userDTO;
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserModel getUserByEmail(@PathVariable String email) {
		UserModel userDTO = this.restTemplate.getForObject(this.userUrl + "email/" + email, UserModel.class);
		return userDTO;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void editUser(@RequestBody UserModel userModel) {
		this.restTemplate.put(this.userUrl, userModel, UserModel.class);
	}
	
	@RequestMapping(value = "/name/{userName}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserModel getUserByName(@PathVariable String userName) {
		UserModel userDTO = this.restTemplate.getForObject(this.userUrl + "name/" + userName, UserModel.class);
		return userDTO;
	}
}