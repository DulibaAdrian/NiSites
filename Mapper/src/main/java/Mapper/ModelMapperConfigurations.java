package Mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import business.DTO.PageDTO;
import business.DTO.SiteDTO;
import business.DTO.UserDTO;
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

	public static void mapPageHelper(PageDTO pageDTO, Page page) {
		pageDTO.setSite(ModelMapperConfigurations.map(page.getSite(), SiteDTO.class));
		pageDTO.getSite().setPageList(new HashSet<>());
		pageDTO.getSite().setUserList(
				(new HashSet<UserDTO>(ModelMapperConfigurations.mapAll(page.getSite().getUserList(), UserDTO.class))));

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

	public static void mapUserHelper(UserDTO userDTO, User user) {
		userDTO.setSites(new HashSet<SiteDTO>(ModelMapperConfigurations.mapAll(user.getSiteList(), SiteDTO.class)));
		for (SiteDTO site : userDTO.getSites()) {
			site.setUserList(new HashSet<UserDTO>());
		}
		for (SiteDTO site : userDTO.getSites()) {
			for (PageDTO page : site.getPageList()) {
				page.setSite(null);
			}
		}
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