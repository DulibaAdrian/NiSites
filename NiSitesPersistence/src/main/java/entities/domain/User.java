package entities.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.NonNull;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String password;
	@NonNull
	private Date creationDate;

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public User() {
		this.creationDate = new Date();
	}
}
