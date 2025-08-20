package com.user.service.UserService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Entity
@Table(name="micro_users")
public class User {
	@Id
	@Column(name="ID")
	private String userId;	
	@Column(name="NAME", length=100)
	private String name;	
	@Column(name="EMAIL", length=50)
	private String email;
	@Column(name="ABOUT", length=50)
	private String about;
	
	   @Transient    //it  means noneed to save in databae
	   private List<Rating> rating = new ArrayList<>();
	   
	   public User() {}
	   
	   // All-args constructor
	    public User(String userId, String name, String email, String about) {
	        this.userId = userId;
	        this.name = name;
	        this.email = email;
	        this.about = about;
	    }
	   
	   
	
	 
	

	// Automatically generate UUID if not set before saving
    @PrePersist
    public void generateId() {
        if (this.userId == null || this.userId.isEmpty()) {
            this.userId = UUID.randomUUID().toString();
        }
    }
    
    
    // manually added getters/setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }
    
 
	public List<Rating> getRating() {
		return rating;
	}


	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}


}
