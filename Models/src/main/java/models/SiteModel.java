package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SiteModel {
	private UUID id;
	private String url;
	private Date creationDate;
	private String siteName;
	private Set<UserModel> userList;
	private Set<PageModel> pageList;
	private boolean isDeleted;

	SiteModel() {
		this.setUserList(new HashSet<UserModel>());
		this.setPageList(new HashSet<PageModel>());
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

	public Set<UserModel> getUserList() {
		return userList;
	}

	public void setUserList(Set<UserModel> userList) {
		this.userList = userList;
	}

	public Set<PageModel> getPageList() {
		return pageList;
	}

	public void setPageList(Set<PageModel> pageList) {
		this.pageList = pageList;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
