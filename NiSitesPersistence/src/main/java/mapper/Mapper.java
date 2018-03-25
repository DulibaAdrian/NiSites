package mapper;

import entities.domain.User;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class Mapper {

	static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	public static MapperFacade getUserMapping() {
		Mapper.mapperFactory.classMap(User.class, User.class).field("name", "name").field("password", "password")
				.field("email", "email").byDefault().register();
		return mapperFactory.getMapperFacade();
	}

}
