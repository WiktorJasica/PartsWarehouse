package pl.wiktorjasica.pwwa.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.wiktorjasica.pwwa.model.Part;
import pl.wiktorjasica.pwwa.model.Warehouse;

/**
 *  Part Data Access Object Interface
 * 
 */
public interface PartDao  extends CrudRepository <Part, Long>{
	
	/**
	 * This method searches parts in specified warehouse
	 * 
	 * @param warehouse
	 * 			warehouse which is searched for parts
	 * 
	 * @return List of parts for specified warehouse
	 */
	List <Part> findByWarehouse(Warehouse warehouse);
	
	/**
	 * This method searches Part object with specific name
	 * 
	 * @param name
	 * 			Name of a part 
	 * 
	 * @return Part object with specific name
	 */
	Part findByName(String name);
}
