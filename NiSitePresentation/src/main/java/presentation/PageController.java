package presentation;

import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dto.PageDTO;
import models.PageModel;

@RestController
@RequestMapping("/api/presentation/page")
public class PageController {

	String pageUrl = "http://localhost:8081/api/business/page/";
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/{siteId}", method = RequestMethod.POST)
	public void addPage(@PathVariable UUID siteId, @RequestBody PageModel pageModel) {
		this.restTemplate.postForEntity(this.pageUrl + siteId.toString(), pageModel, PageModel.class);
	}
	
	@RequestMapping(value = "/{siteId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public PageModel[] getPageList(@PathVariable UUID siteId) {
		PageModel[] listPages = this.restTemplate.getForObject(this.pageUrl + siteId.toString(), PageModel[].class);
		return listPages;
	}
	
	@RequestMapping(value = "/{pageId}", method = RequestMethod.PUT)
	public void editPage(@PathVariable UUID pageId, @RequestBody PageModel pageModel) {
		this.restTemplate.put(this.pageUrl + pageId.toString(), pageModel, PageModel.class);
	}
	
	@RequestMapping(value = "/{pageId}", method = RequestMethod.DELETE)
	public void deletePage(@PathVariable UUID pageId) {
		this.restTemplate.delete(this.pageUrl + pageId.toString());
	}
	
	@RequestMapping(value = "/getPageById/{pageId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public PageModel getPageById(@PathVariable UUID pageId) {
		PageModel pageModel = this.restTemplate.getForObject(this.pageUrl + "getPageById/" + pageId.toString(),
				PageModel.class);
		return pageModel;
	}
	
	@RequestMapping(value = "/editPageContent/{pageId}", method = RequestMethod.PUT)
	public void editPageContent(@PathVariable UUID pageId, @RequestBody String pageConetnt) {
		this.restTemplate.put(this.pageUrl + "editPageContent/" + pageId.toString(), pageConetnt, String.class);
	}

	@RequestMapping(value = "/editPageHeader/{pageId}", method = RequestMethod.PUT)
	public void editPageHeader(@PathVariable UUID pageId, @RequestBody String pageHeader) {
		this.restTemplate.put(this.pageUrl + "editPageHeader/" + pageId.toString(), pageHeader, String.class);
	}
}
