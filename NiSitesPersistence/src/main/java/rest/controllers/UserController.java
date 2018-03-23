package rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entities.domain.User;
import interfaces.IUserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private IUserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<User> getUserList() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		this.userRepository.save(user);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public void editUser(@PathVariable Integer userId, @RequestBody User user) {
		 //this.userRepository.save(user);
	}
}