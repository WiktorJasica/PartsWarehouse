package pl.wiktorjasica.pwwa.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.wiktorjasica.pwwa.dao.PartDao;
import pl.wiktorjasica.pwwa.dao.UserDao;
import pl.wiktorjasica.pwwa.dao.WarehouseDao;
import pl.wiktorjasica.pwwa.model.Part;
import pl.wiktorjasica.pwwa.model.User;
import pl.wiktorjasica.pwwa.model.Warehouse;

/**
 *	This service implements functions connected with warehouse. 
 *
 */
@Service
@Transactional
public class WarehouseService extends AbstractService <Long, Warehouse> {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WarehouseDao warehouseDao;
	
	@Autowired
	private PartDao partDao;
	
		
	/**
	 * This method finds warehouse by given id
	 * 
	 * @param id
	 * 			unique id of Warehouse to find 
	 * @return
	 * 			Warehouse object with list of associated parts
	 */
	public Warehouse findWarehouseWithParts(Long id) {
		Warehouse warehouse = getById(id);
		List<Part> warehousePartsList = partDao.findByWarehouse(warehouse);
		warehouse.setParts(warehousePartsList);
		return warehouse;
	}
	
	
	/**
	 * This method creates new warehouse with parameters taken from completed form and saves it to repository
	 * 
	 * @param warehouse
	 * 			warehouse object bind with completed form 
	 * @param name
	 * 			name of actually logged-in user
	 * 
	 * @return Warehouse object added to repository 
	 */
	public Warehouse createWarehouse(Warehouse warehouse, String name) {
		User user = userDao.findByName(name);
		warehouse.setUser(user);
		return super.create(warehouse);
	}

	
	/**
	 * This method deletes specific warehouse  
	 * You can delete warehouse only if you are loged-in as a specific user or you have role admin
	 * 
	 * @param warehouse to delete
	 */
	@PreAuthorize("#warehouse.user.name == authentication.name or hasRole('ROLE_ADMIN')")		 
	public void delete(@P("warehouse")Warehouse warehouse) {
		warehouseDao.delete(warehouse);
	}
	
	/**
	 * This method returns repository for the Warehouse type of object (it's required for
	 * abstract class).
	 */
	@Override
	protected CrudRepository <Warehouse, Long> getRepository() {
		return warehouseDao;
	}

}
