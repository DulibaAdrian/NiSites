package presentation;

import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import models.SiteModel;

@RestController
@RequestMapping("/api/presentation/site")
public class SiteController {

	String siteUrl = "http://localhost:8081/api/business/site/";
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public void addSite(@PathVariable UUID userId, @RequestBody SiteModel siteModel) {
		this.restTemplate.postForEntity(this.siteUrl + userId.toString(), siteModel, SiteModel.class);
	}

	@RequestMapping(value = "/{siteId}", method = RequestMethod.DELETE)
	public void deleteSite(@PathVariable UUID siteId) {
		this.restTemplate.delete(this.siteUrl + siteId.toString());
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SiteModel[] getSiteList(@PathVariable UUID userId) {
		SiteModel[] listSites = this.restTemplate.getForObject(this.siteUrl + userId.toString(), SiteModel[].class);
		return listSites;
	}
}
