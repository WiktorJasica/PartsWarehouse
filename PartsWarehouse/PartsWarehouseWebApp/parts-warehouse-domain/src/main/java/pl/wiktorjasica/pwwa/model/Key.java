package pl.wiktorjasica.pwwa.model;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

/**
 *	Represents a keyword by which parts are searched in warehouse   
 *
 */
@Component
public class Key {
	
	@Size(min=1,message="Name must be at least 1 character")
	private String key;

	// Getters and Setters
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	//hashCode & equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
		
}
