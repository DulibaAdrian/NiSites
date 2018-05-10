package business;

import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dto.SiteDTO;

@RestController
@RequestMapping("/api/business/site")
public class SiteLogic {

	String siteUrl = "http://localhost:8080/api/repository/site/";
	RestTemplate restTemplate = new RestTemplate();

	private String getSiteName(String url) {
		int startIndex = url.lastIndexOf('/');
		String name = url.substring(startIndex + 1, url.length());
		return name;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public SiteDTO[] getSiteList(@PathVariable UUID userId) {
		SiteDTO[] listSites = this.restTemplate.getForObject(this.siteUrl + userId.toString(), SiteDTO[].class);
		for (SiteDTO site : listSites) {
			site.setSiteName(getSiteName(site.getUrl()));
		}
		return listSites;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.POST)
	public void addSite(@PathVariable UUID userId, @RequestBody SiteDTO siteDto) {
		String siteName = siteDto.getUrl();
		if (siteName != null && !siteName.isEmpty()) {
			siteDto.setUrl("https://sites.niSites.com/" + siteName);
			this.restTemplate.postForEntity(this.siteUrl + userId.toString(), siteDto, SiteDTO.class);
		} else {
			return;
		}
	}

	@RequestMapping(value = "/{siteId}", method = RequestMethod.DELETE)
	public void deleteSite(@PathVariable UUID siteId) {
		this.restTemplate.delete(this.siteUrl + siteId.toString());
	}

}
