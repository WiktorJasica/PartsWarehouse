package pl.wiktorjasica.pwwa.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.wiktorjasica.pwwa.model.User;
import pl.wiktorjasica.pwwa.model.Warehouse;

/**
 * Warehouse Data Access Object Interface
 * 
 */
public interface WarehouseDao  extends CrudRepository <Warehouse, Long> {
	
	/**
	 * This method searches warehouses for specific user 
	 * 
	 * @param user
	 * 			User object for which we are searching warehouses
	 * 
	 * @return List of Warehouses for specific user
	 */
	List<Warehouse> findByUser(User user);

}
