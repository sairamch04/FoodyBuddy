package com.FoodyBuddy.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Country")
public class Country{
	
	@Id
	@Column(name="CountryId")
	@GeneratedValue
	private int countryID;
	@Column(name="countryName")
	private String countryName;

	Country(){}

	public int getCountryId(){
		return countryId;
	}

	public String getCountryName(){
		return countryName;
	}

	public void setCountryName(String name){
		this.countryName = name;
	}

}
