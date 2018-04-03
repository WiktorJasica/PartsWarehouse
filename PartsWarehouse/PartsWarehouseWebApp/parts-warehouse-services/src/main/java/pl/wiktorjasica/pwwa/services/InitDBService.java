package pl.wiktorjasica.pwwa.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.wiktorjasica.pwwa.dao.PartDao;
import pl.wiktorjasica.pwwa.dao.RoleDao;
import pl.wiktorjasica.pwwa.dao.UserDao;
import pl.wiktorjasica.pwwa.dao.WarehouseDao;
import pl.wiktorjasica.pwwa.model.Part;
import pl.wiktorjasica.pwwa.model.Role;
import pl.wiktorjasica.pwwa.model.User;
import pl.wiktorjasica.pwwa.model.Warehouse;

@Transactional
@Service
public class InitDBService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private WarehouseDao warehouseDao;
	
	@Autowired
	private PartDao partDao;
	
	@PostConstruct
	public void init() {
		
		if(roleDao.findByName("ROLE_ADMIN")==null) {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleDao.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleDao.save(roleAdmin);
		
		User userAdmin = new User();
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		userAdmin.setEmail("admin@admin.poczta.pl");
		userAdmin.setEnabled(true);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleUser);
		roles.add(roleAdmin);
		userAdmin.setRoles(roles);
		userDao.save(userAdmin);
		
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setName("Baza_1");
		warehouse1.setUser(userAdmin);
		warehouseDao.save(warehouse1);
		
		Warehouse warehouse2 = new Warehouse();
		warehouse2.setName("Baza_2");
		warehouse2.setUser(userAdmin);
		warehouseDao.save(warehouse2);
			
		Part part1 = new Part();
		part1.setName("TermoPara");
		part1.setManufacturer("LimaTerm");
		part1.setPrice(4000);
		part1.setQuantity(3);
		part1.setWarehouse(warehouse1);
		partDao.save(part1);
		
		Part part2 = new Part();
		part2.setName("CeraBar");
		part2.setManufacturer("EndressHouser");
		part2.setPrice(16000);
		part2.setQuantity(3);
		part2.setWarehouse(warehouse1);
		partDao.save(part2);
		
		List<Part> parts = new ArrayList<Part>();
		parts.add(part1);
		parts.add(part2);
		warehouse1.setParts(parts);
		warehouseDao.save(warehouse1);
		}
	}
}
