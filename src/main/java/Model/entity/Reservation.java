package Model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservation {
	
	public Reservation() {}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "ClientId")
	private int ClientId;
	@Column(name = "VoyageId")
	private int VoyageId;
	@Column(name = "date")
	private LocalDate date;
	public Reservation(int id, int ClientId, int VoyageId,LocalDate date){
		this.id= id;
		this.ClientId = ClientId;
		this.VoyageId = VoyageId;
		this.date = date;
	}
	
	
}
