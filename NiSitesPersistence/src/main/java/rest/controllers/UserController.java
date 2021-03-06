package rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Mapper.ModelMapperConfigurations;
import dto.UserDTO;
import entities.domain.User;
import repository.interfaces.IUserRepository;

@RestController
@RequestMapping("/api/repository/user")
public class UserController {

	@Autowired
	private IUserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<UserDTO> getUserList() {
		List<User> userList = this.userRepository.findAll();
		List<UserDTO> userListDTO = new ArrayList<>();
		for (int i = 0; i < userList.size(); i++) {
			userListDTO.add(ModelMapperConfigurations.mapUserHelper(userList.get(i)));
		}
		return userListDTO;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserById(@PathVariable UUID userId) {
		User user = this.userRepository.findOne(userId);
		if (user == null) {
			return null;
		}
		UserDTO userDTO = ModelMapperConfigurations.mapUserHelper(user);
		return userDTO;
	}

	@RequestMapping(value = "/name/{userName}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserByName(@PathVariable String userName) {
		User user = this.userRepository.findByName(userName);
		if (user == null) {
			return null;
		}
		UserDTO userDTO = ModelMapperConfigurations.mapUserHelper(user);
		return userDTO;
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserByEmail(@PathVariable String email) {
		User user = this.userRepository.findByEmail(email);
		if (user == null) {
			return null;
		}
		UserDTO userDTO = ModelMapperConfigurations.mapUserHelper(user);
		return userDTO;
	}

	@RequestMapping(value = "/email/{email}/password/{password}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
		User user = this.userRepository.findByEmailAndPassword(email, password);
		if (user == null) {
			return null;
		}
		UserDTO userDTO = ModelMapperConfigurations.mapUserHelper(user);
		return userDTO;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addUser(@RequestBody UserDTO userDTO) {
		User user = ModelMapperConfigurations.map(userDTO, User.class);
		if (user == null) {
			return;
		}
		this.userRepository.save(user);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable UUID userId){
		this.userRepository.delete(userId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void editUser(@RequestBody UserDTO newUser) {
		User user = this.userRepository.findByEmail(newUser.getEmail());
		if (user == null) {
			return;
		}
		UUID id = user.getId();
		ModelMapperConfigurations.map(newUser, user);
		user.setId(id);
		this.userRepository.save(user);
	}
}
