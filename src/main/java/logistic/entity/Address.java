package logistic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue
	
	private Long id;
	public String sublocality,locality,flatno;
	

}
