package dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class SiteDTO {
	
	private UUID id;
	private String url;
	private String siteName;
	private Date creationDate;
	private Set<UserDTO> userList;
	private Set<PageDTO> pageList;
	
	public SiteDTO() {
		this.setUserList(new HashSet<UserDTO>());
		this.setPageList(new HashSet<PageDTO>());
		this.setCreationDate(new Date());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Set<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(Set<UserDTO> userList) {
		this.userList = userList;
	}

	public Set<PageDTO> getPageList() {
		return pageList;
	}

	public void setPageList(Set<PageDTO> pageList) {
		this.pageList = pageList;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
}
