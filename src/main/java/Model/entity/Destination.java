package Model.entity;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;


@Entity
public class Destination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "description",length = 1000)
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "Size")
	private int Size;
	
	@Column(name = "capital")
	private String capital;
	
	@Column(name = "population")
	private String population;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "currency")
	private String currency;
	
	@ElementCollection(fetch= FetchType.EAGER)
	   @CollectionTable(
		    name = "destination_attractions",
		    joinColumns = @JoinColumn(name = "destination_id")
		 )
	@Column(name = "attractions")
	private List<String> attractions;
	
	@ElementCollection(fetch = FetchType.EAGER)
	   @CollectionTable(
		    name = "destination_activities",
		    joinColumns = @JoinColumn(name = "destination_id")
		 )
	
	@Column(name = "activities")
	private List<String> activities;
	
	
    public Destination() {
    }

    public Destination(int id, String title, String description, String image,int Size, String capital, String population,String language, String currency, List<String> attractions, List<String> activities) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.Size = Size;
		this.capital = capital;
		this.population = population;
		this.language = language;
		this.currency = currency;
		this.attractions = attractions;
		this.activities = activities;
		
	}
    public Destination(String title, String description, String image,int Size, String capital, String population,String language, String currency, List<String> attractions, List<String> activities) {
  		this.title = title;
  		this.description = description;
  		this.image = image;
  		this.Size = Size;
  		this.capital = capital;
  		this.population = population;
  		this.language = language;
  		this.currency = currency;
  		this.attractions = attractions;
  		this.activities = activities;
  	}
  

	public int getId() {
		return id;
	}
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setSize(int size) {
		this.Size = size;
	}
	public int getSize() {
		return Size;
	}
	
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public List<String> getAttractions() {
		return attractions;
	}
	public void setAttractions(List<String> attractions) {
		this.attractions = attractions;
	}
	public List<String> getActivities() {
		return activities;
	}
	public void setActivities(List<String> activities) {
		this.activities = activities;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
