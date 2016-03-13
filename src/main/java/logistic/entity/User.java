package logistic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	
	 @JsonIgnore
     @ManyToOne
     private Client client;
	 
	 @Id
	 @GeneratedValue
	 private Long userId;
	 
	
	 public String username,firstname,lastname,contact,location;
	 
	 public String getUsername()
	 {
		 return username;
	 }
	 public Client getClient() 
	 {
	    	return client;
	 }
	
	 public Long getUserId() 
	 {
		return userId;
	 }
    public String getFirstname()
	 {
			return firstname;
	 }
	public String getLastname()
	{
			return lastname;
		}
	public String getContact() 
	{
			return contact;
	}
	public String getLocation()
	{
			return location;
	}

    public User(Client client,String username ,String firstname,String lastname,String contact,String location) {
	        this.username = username;
	        this.firstname = firstname;
	        this.lastname = lastname;
	        this.contact = contact;
	        this.location = location;
	        this.client = client;
	 }
	    
	    User() { // jpa only
	    }
		public void setUsername(String username) {
			this.username = username;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public void setLocation(String location) {
			this.location = location;
		}

}
