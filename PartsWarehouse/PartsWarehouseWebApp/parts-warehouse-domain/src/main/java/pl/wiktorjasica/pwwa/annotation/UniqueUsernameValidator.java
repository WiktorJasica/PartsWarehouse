package pl.wiktorjasica.pwwa.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import pl.wiktorjasica.pwwa.dao.UserDao;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(userDao==null) {
			return true;
		}
		return userDao.findByName(username)==null;
	}

}
