package pl.wiktorjasica.pwwa.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.wiktorjasica.pwwa.dao.RoleDao;
import pl.wiktorjasica.pwwa.dao.UserDao;
import pl.wiktorjasica.pwwa.dao.WarehouseDao;
import pl.wiktorjasica.pwwa.model.User;
import pl.wiktorjasica.pwwa.model.Warehouse;

@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceTest {
	
	private final static long ID = 1;
	
	private User dummyUser;
			
	@Mock
	private UserDao userDao;
	
	@Mock
	private RoleDao roleDao;
	
	@Mock
	private WarehouseDao warehouseDao;
	
	@InjectMocks
	private UserService userService; 
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		dummyUser = new User();
	}
	

	@Test
	void testFindUsersWithWarehouses_ReturnWalueIsTheSameAsExpected() {
		Iterable<User>expectedUsersList = initUsersIterableNull();
		Iterable<User>resultUsersList = initUsersIterableNull();
		when(userDao.findAll()).thenReturn(resultUsersList);
		when(warehouseDao.findByUser(any(User.class))).thenReturn(null);
		Iterable<User> result = userService.findUsersWithWarehouses();
		assertEquals(expectedUsersList,result);
	}
	
	public Iterable<User> initUsersIterableNull(){
		
		User user1 = new User((long) 1,"Wiktor","wiktor@op.pl","wiktor",true, null);
		User user2 = new User((long) 2,"Tomek","tomek@op.pl","tomek",true, null);
		User user3 = new User((long) 3,"Jacek","jacek@op.pl","jacek",true, null);
		return Arrays.asList(user1,user2,user3);
	}
	
	public List<Warehouse> initDummyWarehouseDatabase(){
		Warehouse warehouse1 = new Warehouse();
		Warehouse warehouse2 = new Warehouse();
		return Arrays.asList(warehouse1,warehouse2);
	}

	@AfterEach
	public void cleanup() {
		dummyUser=null;
	}
}
