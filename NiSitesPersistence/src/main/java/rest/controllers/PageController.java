package rest.controllers;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Mapper.ModelMapperConfigurations;
import business.DTO.PageDTO;
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

	@RequestMapping(value = "/{pageId}", method = RequestMethod.GET)
	public PageDTO getPageById(@PathVariable UUID pageId) {
		Page page = pageRepository.findOne(pageId);
		PageDTO pageDTO = ModelMapperConfigurations.map(page, PageDTO.class);
		ModelMapperConfigurations.mapPageHelper(pageDTO, page);
		return pageDTO;
	}

	@RequestMapping(value = "/{siteId}", method = RequestMethod.POST)
	public void addPage(@PathVariable UUID siteId, @RequestBody PageDTO pageDTO) {
		Site site = this.siteRepository.findOne(siteId);
		Page page = ModelMapperConfigurations.map(pageDTO, Page.class);
		site.getPageList().add(page);
		page.setSite(site);
		this.pageRepository.save(page);
	}

	@RequestMapping(value = "/{pageId}", method = RequestMethod.DELETE)
	public void deletePage(@PathVariable UUID pageId) {
		this.pageRepository.delete(pageId);
	}
	
}
