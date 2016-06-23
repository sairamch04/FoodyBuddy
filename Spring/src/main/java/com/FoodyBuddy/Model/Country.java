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
	@Column(name="id")
	@GeneratedValue
	private int countryID;
	@Column(name="name")
	private String name;

	public Country(){}

	public Country(String name){
		this.name = name;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return countryName;
	}

	public void setCountryName(String name){
		this.countryName = name;
	}

}
