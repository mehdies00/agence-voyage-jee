package Model.entity;

import java.time.LocalDate;

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

    private int destinationId;
    private int locationId;
    private float budget;
    
     private LocalDate depart;

     @Column(name = "return_date") 
    private LocalDate returnDate;

    private int type;
    private String travelers;

     public Voyage(int destinationId, int locationId, float budget, LocalDate depart, LocalDate returnDate, int type, String travelers) {
        this.destinationId = destinationId;
        this.locationId = locationId;
        this.budget = budget;
        this.depart = depart;
        this.returnDate = returnDate;
        this.type = type;
        this.travelers = travelers;
    }

    public Voyage() {}

	public int getId() {
		return id;
	}

	 

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public LocalDate getDepart() {
		return depart;
	}

	public void setDepart(LocalDate depart) {
		this.depart = depart;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTravelers() {
		return travelers;
	}

	public void setTravelers(String travelers) {
		this.travelers = travelers;
	}
    
 }