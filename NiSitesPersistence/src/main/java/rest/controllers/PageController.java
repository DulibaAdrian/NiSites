package rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import Mapper.ModelMapperConfigurations;
import dto.PageDTO;
import dto.UserDTO;
import entities.domain.Page;
import entities.domain.Site;
import entities.domain.User;
import repository.interfaces.IPageRepository;
import repository.interfaces.ISiteRepository;

@RestController
@RequestMapping("/api/repository/page")
public class PageController {

	@Autowired
	private IPageRepository pageRepository;
	@Autowired
	private ISiteRepository siteRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<PageDTO> getPagesList() {
		List<Page> pageList = this.pageRepository.findAll();
		List<PageDTO> pageListDTO = new ArrayList<>();
		for (int i = 0; i < pageList.size(); i++) {
			pageListDTO.add(ModelMapperConfigurations.mapPageHelper(pageList.get(i)));
		}
		return pageListDTO;
	}

	@RequestMapping(value = "/{pageId}", method = RequestMethod.GET)
	public PageDTO getPageById(@PathVariable UUID pageId) {
		Page page = pageRepository.findOne(pageId);
		PageDTO pageDTO = ModelMapperConfigurations.mapPageHelper(page);
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

	@RequestMapping(value = "/{pageId}", method = RequestMethod.PUT)
	public void editPage(@PathVariable UUID pageId, @RequestBody PageDTO newPage) {
		Page page = this.pageRepository.findOne(pageId);
		if (page == null) {
			return;
		}
		ModelMapperConfigurations.map(newPage, page);
		page.setId(pageId);
		this.pageRepository.save(page);
	}
}
