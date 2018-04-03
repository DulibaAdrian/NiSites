package rest.controllers;

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
import business.DTO.UserDTO;
import entities.domain.User;
import repository.interfaces.IUserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<UserDTO> getUserList() {
		List<User> userList = userRepository.findAll();
		List<UserDTO> userListDTO = ModelMapperConfigurations.mapAll(userList, UserDTO.class);
		for (int i = 0; i < userListDTO.size(); i++) {
			ModelMapperConfigurations.mapUserHelper(userListDTO.get(i), userList.get(i));
		}
		return userListDTO;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserById(@PathVariable UUID userId) {
		User user = userRepository.findOne(userId);
		UserDTO userDTO = ModelMapperConfigurations.map(user, UserDTO.class);
		ModelMapperConfigurations.mapUserHelper(userDTO, user);
		return userDTO;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addUser(@RequestBody UserDTO userDTO) {
		User user = ModelMapperConfigurations.map(userDTO, User.class);
		this.userRepository.save(user);
	}
}
