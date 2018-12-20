package pl.wiktorjasica.pwwa.services;



import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import pl.wiktorjasica.pwwa.dao.RoleDao;
import pl.wiktorjasica.pwwa.dao.UserDao;
import pl.wiktorjasica.pwwa.dao.WarehouseDao;
import pl.wiktorjasica.pwwa.model.Role;
import pl.wiktorjasica.pwwa.model.User;
import pl.wiktorjasica.pwwa.model.Warehouse;

/**
 * 	This service implements functions connected with user.
 *
 */
@Service
@Transactional
public class UserService extends AbstractService<Long, User> {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private WarehouseDao warehouseDao;	
	

	/**
	 * This method finds all users that are registered and all warehouses for each of them
	 * 
	 * @return Registered users and their warehouses
	 */
	public Iterable <User> findUsersWithWarehouses(){
		Iterable<User> users = getAll();
		for(User user :users) {
			List<Warehouse> warehouses = warehouseDao.findByUser(user);	
			user.setWarehouses(warehouses);
		}
		return users;
	}
	
	/**
	 * This method finds user by his name and associated with him warehouses
	 * 
	 * @param name
	 * 			name of user
	 * 
	 * @return List of user's warehouse
	 */
	public List<Warehouse> findUserWithWarehousesByName(String name) {
			User user = userDao.findByName(name);
			List<Warehouse> warehouses = warehouseDao.findByUser(user); 
		return warehouses;
	}


	/**
	 * This method creates User ,gives him Role and saves to repository
	 */
	@Override
	public User create(User user) {
		user.setEnabled(true);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleDao.findByName("ROLE_USER"));
		user.setRoles(roles);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return super.create(user);
	}
	
	
	/**
	 * This method returns repository for the User type of object (it's required for
	 * abstract class).
	 */
	@Override
	protected CrudRepository<User, Long> getRepository() {
		return userDao;
	}
	
}
