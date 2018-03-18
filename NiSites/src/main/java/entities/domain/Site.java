package entities.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Site")
public class Site implements Serializable {
	private static final long serialVersionUID = 4628466944459883435L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer pageId;
	private Date creationDate;
	private Integer userId;
	//private Set<User> userList;
	//@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
	
  /*  @OneToMany(mappedBy="site")
	private Set<Page> pageList;*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

/*	public Set<User> getUserList() {
		return userList;
	}*/

	//@ManyToMany(cascade = CascadeType.ALL)
	//@JoinTable(name = "UserToSite", joinColumns = @JoinColumn(name = "SiteId", referencedColumnName = "ïd") , inverseJoinColumns = @JoinColumn(name = "UserId", referencedColumnName = "id") )
	/*public void setUserList(Set<User> userList) {
		this.userList = userList;
	}*/

	/*public Set<Page> getPageList() {
		return pageList;
	}

	public void setPageList(Set<Page> pageList) {
		this.pageList = pageList;
	}*/
}