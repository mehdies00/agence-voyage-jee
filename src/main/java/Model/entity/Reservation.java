package Model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "ClientId")
	private int ClientId;
	@Column(name = "VoyageId")
	private int VoyageId;
	
	public Reservation(int id, int ClientId, int VoyageId){
		this.id= id;
		this.ClientId = ClientId;
		this.VoyageId = VoyageId;
	}
	
}
