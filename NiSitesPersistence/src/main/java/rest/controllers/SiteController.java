package rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import entities.domain.Site;
import interfaces.ISiteRepository;

@RestController
@RequestMapping("/api/site")
public class SiteController {
	@Autowired
	private ISiteRepository siteRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Site> getSitesList() {
		return siteRepository.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void addSite(@RequestBody Site site) {
		this.siteRepository.save(site);
	}

}
