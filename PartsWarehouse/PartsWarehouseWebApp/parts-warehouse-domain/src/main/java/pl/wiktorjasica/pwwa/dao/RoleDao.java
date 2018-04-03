package pl.wiktorjasica.pwwa.dao;


import org.springframework.data.repository.CrudRepository;

import pl.wiktorjasica.pwwa.model.Role;

/**
 * Role Data Access Object Interface
 * 
 */
public interface RoleDao extends CrudRepository <Role, Long>{

	/**
	 * This method searches Role object with specific name
	 * 
	 * @param name
	 * 			name of the Role 
	 * 
	 * @return Role with a specific name 
	 */
	Role findByName(String name);

}
