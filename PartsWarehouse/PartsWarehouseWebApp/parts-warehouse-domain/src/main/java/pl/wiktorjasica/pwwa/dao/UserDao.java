package pl.wiktorjasica.pwwa.dao;

import org.springframework.data.repository.CrudRepository;

import pl.wiktorjasica.pwwa.model.User;

/**
 * User Data Access Object Interface
 * 
 */
public interface UserDao extends CrudRepository <User, Long> {
	
	/**
	 * This method searches User object with specific name
	 * 
	 * @param name
	 * 			Name of the user
	 * 
	 * @return User with specific name 
	 */
	User findByName(String name); 

}
