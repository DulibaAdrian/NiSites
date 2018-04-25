package business;

import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import business.DTO.PageDTO;

@RestController
@RequestMapping("/api/business/page")
public class PageLogic {

	String pageUrl = "http://localhost:8080/api/repository/page/";
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/{siteId}", method = RequestMethod.POST)
	public void addPage(@PathVariable UUID siteId, @RequestBody PageDTO pageDTO) {
		this.restTemplate.postForEntity(this.pageUrl + siteId.toString(), pageDTO, PageDTO.class);
	}

	@RequestMapping(value = "/{pageId}", method = RequestMethod.DELETE)
	public void deletePage(@PathVariable UUID pageId) {
		this.restTemplate.delete(this.pageUrl + pageId.toString());
	}

	@RequestMapping(value = "/{pageId}", method = RequestMethod.PUT)
	public void editPage(@PathVariable UUID pageId, @RequestBody PageDTO pageDTO) {
		this.restTemplate.put(this.pageUrl + pageId.toString(), pageDTO, PageDTO.class);
	}
}