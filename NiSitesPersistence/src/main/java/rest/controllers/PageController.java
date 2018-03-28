package rest.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entities.domain.Page;
import entities.domain.Site;
import repository.interfaces.IPageRepository;
import repository.interfaces.ISiteRepository;

@RestController
@RequestMapping("/api/page")
public class PageController {

	@Autowired
	private IPageRepository pageRepository;
	@Autowired
	private ISiteRepository siteRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Page> getPagesList() {
		return pageRepository.findAll();
	}

	@RequestMapping(value = "/{pageId}", method = RequestMethod.PUT)
	public void editPage(@PathVariable UUID pageId, @RequestBody Page page) {
		Page pageToUpdate = pageRepository.findOne(pageId);
		pageToUpdate.setContent(page.getContent());
		this.pageRepository.save(pageToUpdate);
	}

	@RequestMapping(value = "/{siteId}", method = RequestMethod.POST)
	public void addPage(@PathVariable UUID siteId, @RequestBody Page page) {
		Site site=this.siteRepository.findOne(siteId);
		site.getPageList().add(page);
		page.setSite(site);
		this.pageRepository.save(page);
	}

	@RequestMapping(value = "/{pageId}", method = RequestMethod.DELETE)
	public void deletePage(@PathVariable UUID pageId) {
		this.pageRepository.delete(pageId);
	}
}
