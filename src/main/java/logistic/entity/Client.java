package logistic.entity;

import java.util.Set;
import java.util.HashSet;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;



import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {
	
	@OneToMany(mappedBy="client" ,orphanRemoval=true)
	private Set<User> users = new HashSet<>();
	
	@OneToMany(mappedBy="owner",orphanRemoval=true)
	private Set<Task> tasks = new HashSet<>();
	
	@Id
	@GeneratedValue
	private Long clientId;
	
	
	@JsonIgnore
	public String password;
	public String clientname;
    
    public Set<User> getUsers() 
    {
		return users;
	}
    
   
    public Long getClientId() {
		return clientId;
	}
	public String getPassword() {
		return password;
	}
	public String getClientname() {
		return clientname;
	}
	
	 public Client( String clientname,String password) {
		    this.clientname = clientname;
			this.password = password;
			
		}
	
	 
	 Client() { // jpa only
	    }


	public Set<Task> getTasks() {
		return tasks;
	}

	

}
