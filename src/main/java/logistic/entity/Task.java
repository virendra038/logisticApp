package logistic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Task {
	
	
	@JsonIgnore
	@ManyToOne
	private Client owner;
	@Id
	@GeneratedValue
	private Long taskId;
	
	
	
	
	public String pickup,duration,drop,exId;

	public Task(Client owner, String pickup, String duration,String drop,String exId) {
		
		this.pickup = pickup;
		this.duration = duration;
		this.owner = owner;
		this.drop = drop;
		this.exId = exId;
	}

	public Client getOwner()
	{
		return owner;
	}
	public Long getTaskId() {
		return taskId;
	}

	public String getPickup() {
		return pickup;
	}

	public String getDuration() {
		return duration;
	}
	public String getDrop(){
		return drop;
	}
	public String getExId(){
		return exId;
	}

	public Task(){
		
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setDrop(String drop) {
		this.drop = drop;
	}

	public void setExId(String exId) {
		this.exId = exId;
	}

	public void setOwner(Client owner) {
		this.owner = owner;
	}
	
}
