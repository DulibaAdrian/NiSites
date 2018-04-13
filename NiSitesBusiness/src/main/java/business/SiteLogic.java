package business;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import business.DTO.SiteDTO;


@RestController
@RequestMapping("/api/business/site")
public class SiteLogic {

	String siteUrl = "http://localhost:8080/api/repository/site/";
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SiteDTO[] getSiteList() {
		SiteDTO[] listSites = restTemplate.getForObject(this.siteUrl, SiteDTO[].class);
		return listSites;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public void addSite(@PathVariable UUID userId, @RequestBody SiteDTO siteDto) {
		restTemplate.postForEntity(this.siteUrl + userId.toString(), siteDto, SiteDTO.class);
	}

}