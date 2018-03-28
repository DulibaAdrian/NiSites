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

import entities.domain.User;
import mapper.Mapper;
import repository.interfaces.IUserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<User> getUserList() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public User getUserById(@PathVariable UUID userId) {
		return this.userRepository.findOne(userId);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		this.userRepository.save(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public void editUser(@PathVariable UUID userId, @RequestBody User user) {
		User usertToUpdate = Mapper.getUserMapping().map(user, User.class);
		usertToUpdate.setId(userId);
		this.userRepository.save(usertToUpdate);
	}
}
