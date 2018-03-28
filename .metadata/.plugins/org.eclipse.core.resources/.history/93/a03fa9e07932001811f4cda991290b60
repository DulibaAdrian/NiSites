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
import entities.domain.User;
import interfaces.IPageRepository;
import interfaces.ISiteRepository;
import interfaces.IUserRepository;

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
	public List<Site> getSitesList() {
		return siteRepository.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public void addSite(@PathVariable Integer userId, @RequestBody Site site) {
		Page homePage = new Page();
		homePage.setPageNumber(1);
		homePage.setContent(
				"<!DOCTYPE html><html><head><title>Home Page</title></head>" + "<body>" + "</body>" + "</html>");
		homePage.setSite(site);		
		User user= this.userRepository.findOne(userId);
		user.getSiteList().add(site);
		site.getPageList().add(homePage);
		site.getUserList().add(user);
		this.pageRepository.save(homePage);
	}

	@RequestMapping(value = "/{siteId}", method = RequestMethod.PUT)
	public void editSite(@PathVariable Integer siteId, @RequestBody Site site) {
		Site siteToUpdate = siteRepository.findOne(siteId);
		siteToUpdate.setUrl(site.getUrl());
		this.siteRepository.save(siteToUpdate);
	}

	@RequestMapping(value = "/{siteId}", method = RequestMethod.DELETE)
	public void deleteSite(@PathVariable Integer siteId) {
		this.siteRepository.delete(siteId);
	}
}
