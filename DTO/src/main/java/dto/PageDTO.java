package dto;

import java.util.Date;
import java.util.UUID;

public class PageDTO {
	
	private UUID id;
	private String content;
	private Date creationDate;
	private Integer pageNumber;
	private SiteDTO site;

	public PageDTO() {
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

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public SiteDTO getSite() {
		return site;
	}

	public void setSite(SiteDTO site) {
		this.site = site;
	}
}