package rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entities.domain.Page;
import interfaces.IPageRepository;

@RestController
@RequestMapping("/api/page")
public class PageController {

	@Autowired
	private IPageRepository pageRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Page> getPagesList() {
		return pageRepository.findAll();
	}

	@RequestMapping(value = "/{pageId}", method = RequestMethod.PUT)
	public void editPage(@PathVariable Integer pageId, @RequestBody Page page) {
		Page pageToUpdate = pageRepository.findOne(pageId);
		pageToUpdate.setContent(page.getContent());
		this.pageRepository.save(pageToUpdate);
	}
	
	@RequestMapping(value = "/{pageId}", method = RequestMethod.DELETE)
	public void deletePage(@PathVariable Integer pageId) {
		this.pageRepository.delete(pageId);
	}
}
