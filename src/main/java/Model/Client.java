package Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
	@Column(name = "name")
    private String name;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "phoneNumber")
    private String phoneNumber ;
	
	@Column(name = "gender")
    private String gender ;
	
    
    public Client() {}

    
    public Client(int id, String name, String email, String password, String phoneNumber,String gender) {
		this.id = id;
		this.name= name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender ;	
	}
    public Client(String name, String email, String password, String phoneNumber,String gender) {
		this.name= name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.gender = gender ;	
	}



	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
		
		
	public String getName() {return name;}
	public void setName(String name) {this.name = name;	}

		

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
		
	
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
		
	

	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

		
	
	public String getGender() {return gender;}
	public void setGender(String gender) {this.gender = gender;}
		
	
	}
    
    

