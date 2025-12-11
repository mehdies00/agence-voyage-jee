package Model.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Voyage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "budget")
	private float budget;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "type")
	private String type;
	
	public Voyage(int id, String destination, float budget, String type) {
		this.id = id;
		this.destination = destination;
		this.budget = budget;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	public float getBudget() {
		return budget;
	}
	public void setBudget(float budget) {
		this.budget = budget;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
