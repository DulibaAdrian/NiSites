package models;

import java.util.Date;
import java.util.UUID;

public class PageModel {
	private UUID id;
	private String content;
	private Date creationDate;
	private String pageName;
	private SiteModel site;
	private boolean isDeleted;

	public PageModel() {
		this.creationDate = new Date();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public SiteModel getSite() {
		return site;
	}

	public void setSite(SiteModel site) {
		this.site = site;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
