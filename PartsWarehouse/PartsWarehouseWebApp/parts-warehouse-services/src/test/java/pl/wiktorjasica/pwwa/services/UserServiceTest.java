package pl.wiktorjasica.pwwa.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class UserServiceTest {
	
	

	@Test
	void test() {
		List<?> mockList = mock(ArrayList.class);
		when(mockList.size()).thenReturn(5);
		Assertions.assertEquals(5, mockList.size());
	
	}

}
