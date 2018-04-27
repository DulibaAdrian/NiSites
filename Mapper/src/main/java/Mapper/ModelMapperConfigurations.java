package Mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import dto.PageDTO;
import dto.SiteDTO;
import dto.UserDTO;
import entities.domain.Page;
import entities.domain.Site;
import entities.domain.User;

public class ModelMapperConfigurations {

	private static ModelMapper modelMapper = new ModelMapper();
    
	static {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	private ModelMapperConfigurations() {
	}

	public static Page mapPageHelper(PageDTO pageDTO) {
		Page page = ModelMapperConfigurations.map(pageDTO, Page.class);
		page.setSite(ModelMapperConfigurations.map(pageDTO.getSite(), Site.class));
		page.getSite().setPageList(new HashSet<>());
		page.getSite().setUserList(
				(new HashSet<User>(ModelMapperConfigurations.mapAll(pageDTO.getSite().getUserList(), User.class))));
		return page;
	}
	
	public static PageDTO mapPageHelper(Page page) {
		PageDTO pageDTO = ModelMapperConfigurations.map(page, PageDTO.class);
		pageDTO.setSite(ModelMapperConfigurations.map(page.getSite(), SiteDTO.class));
		pageDTO.getSite().setPageList(new HashSet<>());
		pageDTO.getSite().setUserList(
				(new HashSet<UserDTO>(ModelMapperConfigurations.mapAll(page.getSite().getUserList(), UserDTO.class))));
		return pageDTO;
	}

	public static void mapSiteHelper(SiteDTO siteDTO, Site site) {
		siteDTO.setUserList(new HashSet<UserDTO>(ModelMapperConfigurations.mapAll(site.getUserList(), UserDTO.class)));
		siteDTO.setPageList(new HashSet<PageDTO>(ModelMapperConfigurations.mapAll(site.getPageList(), PageDTO.class)));
		for (UserDTO user : siteDTO.getUserList()) {
			user.setSites(new HashSet<SiteDTO>());
		}
		for (PageDTO page : siteDTO.getPageList()) {
			page.setSite(null);
		}
	}

	public static UserDTO mapUserHelper(User user) {
		UserDTO userDTO = ModelMapperConfigurations.map(user, UserDTO.class);
		userDTO.setSites(new HashSet<SiteDTO>(ModelMapperConfigurations.mapAll(user.getSiteList(), SiteDTO.class)));
		for (SiteDTO site : userDTO.getSites()) {
			site.setUserList(new HashSet<UserDTO>());
		}
		for (SiteDTO site : userDTO.getSites()) {
			for (PageDTO page : site.getPageList()) {
				page.setSite(null);
			}
		}
		return userDTO;
	}

	public static <D, T> D map(final T entity, Class<D> outClass) {
		return modelMapper.map(entity, outClass);
	}

	public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
		return entityList.stream().map(entity -> map(entity, outCLass)).collect(Collectors.toList());
	}

	public static <S, D> D map(final S source, D destination) {
		modelMapper.map(source, destination);
		return destination;
	} 

}
