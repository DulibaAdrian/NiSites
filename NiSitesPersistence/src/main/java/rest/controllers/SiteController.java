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
import Mapper.ModelMapperConfigurations;
import business.DTO.SiteDTO;
import entities.domain.Page;
import entities.domain.Site;
import entities.domain.User;
import repository.interfaces.IPageRepository;
import repository.interfaces.ISiteRepository;
import repository.interfaces.IUserRepository;

@RestController
@RequestMapping("/api/site")
public class SiteController {

	@Autowired
	private ISiteRepository siteRepository;
	@Autowired
	private IPageRepository pageRepository;
	@Autowired
	private IUserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<SiteDTO> getSitesList() {
		List<Site> siteList = this.siteRepository.findAll();
		List<SiteDTO> siteListDTO = ModelMapperConfigurations.mapAll(siteList, SiteDTO.class);
		for (int i = 0; i < siteListDTO.size(); i++) {
			ModelMapperConfigurations.mapSiteHelper(siteListDTO.get(i), siteList.get(i));
		}
		return siteListDTO;

	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public void addSite(@PathVariable UUID userId, @RequestBody SiteDTO siteDTO) {
		Page homePage = new Page();
		homePage.setPageNumber(1);
		homePage.setContent(
				"<!DOCTYPE html><html><head><title>Home Page</title></head>" + "<body>" + "</body>" + "</html>");
		Site site = ModelMapperConfigurations.map(siteDTO, Site.class);
		homePage.setSite(site);
		User user = this.userRepository.findOne(userId);
		user.getSiteList().add(site);
		site.getPageList().add(homePage);
		site.getUserList().add(user);
		this.pageRepository.save(homePage);
	}

	@RequestMapping(value = "/{siteId}", method = RequestMethod.DELETE)
	public void deleteSite(@PathVariable UUID siteId) {
		this.siteRepository.delete(siteId);
	}

}
