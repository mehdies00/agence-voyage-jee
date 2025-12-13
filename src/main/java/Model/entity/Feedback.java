package Model.entity;
import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "userId")
	private int userId;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "date")
	private LocalDate date;
	
    public Feedback() {
    }
	public Feedback(int id,int userId, String description, LocalDate date) {
		this.id = id;
		this.userId =  userId;
		this.description = description;
		this.date = date;
	}
	public Feedback(int userId, String description, LocalDate date) {
		this.userId =  userId;
		this.description = description;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	
	public int getUserId() {
		return  userId;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
}
