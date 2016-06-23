package com.java.model

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Buyers")
public class Buyer{
	
	@Id
	@Column(name="buyerId")
	@GeneratedValue
	private int buyerId;
	
	@ManyToOne
	@ForeignKey(name="FK_Apartment")
	private Apartment apartment;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phoneNumber")
	private String phoneNumber;

	@Column(name="email")
	private String email;
	
	@Column(name="flatNumber")
	private String flatNumber;
	
	Buyer(Apartment apartment, String name, String phoneNumber, String email, String flatNumber){
		
		this.apartment = apartment;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.flatNumber = flatNumber;
		
	}

	public int getId(){
		return id;
	}

	public void setID(int id){
		this.id = id;
	}

	public Apartment getApartment(){
		return apartment;
	}

	public void setApartment(Apartment apartment){
		this.apartment = apartment;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getFlatNumber(){
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber){
		this.flatNumber = flatNumber;
	}

}
