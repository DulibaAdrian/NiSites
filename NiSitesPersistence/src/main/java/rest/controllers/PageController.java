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
import entities.domain.Site;
import interfaces.IPageRepository;
import interfaces.ISiteRepository;

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

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addPage(@PathVariable Integer siteId, @RequestBody Page page) {
		Site site = siteRepository.getOne(siteId);
		if (siteId == null) {

		}else{
			site.getPageList().add(page);
		}
		this.pageRepository.save(page);
	}

}
