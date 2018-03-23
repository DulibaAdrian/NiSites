package entities.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity
public class Site {

	@Id
	@GeneratedValue
	private Integer id;
	private Date creationDate;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_SITE", joinColumns = { @JoinColumn(name = "SITE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private Set<User> userList;
	
    @OneToMany(mappedBy = "site")
	private Set<Page> pageList;

    public Site(){
    	this.userList = new HashSet<User>();
    	this.pageList = new HashSet<Page>();
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Set<User> getUserList() {
		return userList;
	}

	public void setUserList(Set<User> userList) {
		this.userList = userList;
	}
}