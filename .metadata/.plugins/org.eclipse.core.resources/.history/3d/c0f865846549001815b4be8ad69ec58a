package business.Models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class UserModel {
	private UUID id;
	private String name;
	private String email;
	private String password;
	private Date creationDate;
	private Set<SiteModel> sites;

	public UserModel() {
		this.setCreationDate(new Date());
		this.setSites(new HashSet<SiteModel>());
	}

	public Set<SiteModel> getSites() {
		return sites;
	}

	public void setSites(Set<SiteModel> sites) {
		this.sites = sites;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
