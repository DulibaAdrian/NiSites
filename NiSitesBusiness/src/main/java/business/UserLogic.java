package business;

import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import dto.UserDTO;

@RestController
@RequestMapping("/api/business/user")
public class UserLogic {

	String userUrl = "http://localhost:8080/api/repository/user/";
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDTO[] getUserList() {
		UserDTO[] listUsers = this.restTemplate.getForObject(this.userUrl, UserDTO[].class);
		return listUsers;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addUser(@RequestBody UserDTO userDTO) {
		this.restTemplate.postForEntity(this.userUrl, userDTO, UserDTO.class);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void editUser(@RequestBody UserDTO userDTO) {
		this.restTemplate.put(this.userUrl, userDTO, UserDTO.class);
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserById(@PathVariable UUID userId) {
		UserDTO userDTO = this.restTemplate.getForObject(this.userUrl + userId.toString(), UserDTO.class);
		return userDTO;
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserByEmail(@PathVariable String email) {
		UserDTO userDTO = this.restTemplate.getForObject(this.userUrl + "email/" + email, UserDTO.class);
		return userDTO;
	}

	@RequestMapping(value = "/name/{userName}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserByName(@PathVariable String userName) {
		UserDTO userDTO = this.restTemplate.getForObject(this.userUrl + "name/" + userName, UserDTO.class);
		return userDTO;
	}

	@RequestMapping(value = "/email/{email}/password/{password}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public UserDTO getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
		UserDTO userDTO = this.restTemplate.getForObject(this.userUrl + "email/" + email + "/password/" + password,
				UserDTO.class);
		return userDTO;
	}

	@RequestMapping(value = "/sendMail/user/{user}/password/{password}/to/{to}/subject/{subject}/text/{text}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })

	private void sendEmail(@PathVariable String user, @PathVariable String password, @PathVariable String to,
			@PathVariable String subject, @PathVariable String text) {

		String host = "smtp.gmail.com";
		if (!user.equals("") && !password.equals("")) {
			String SMTP_PORT = "465";
			String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			props.put("mail.smtp.port", SMTP_PORT);
			props.put("mail.smtp.socketFactory.port", SMTP_PORT);
			props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.put("mail.smtp.socketFactory.fallback", "false");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			try {
				MimeMessage message = new MimeMessage(session);
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(message, "text/html");
				message.setFrom(new InternetAddress(user));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(subject);
				message.setText(text);
				Transport.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
