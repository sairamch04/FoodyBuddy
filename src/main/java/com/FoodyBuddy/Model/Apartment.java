package com.FoodyBuddy.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "Apartment")
public class Apartment {
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "locality_id")
	@ManyToOne
	@JoinColumn(name = "id")
	private Locality locality;
	
	@Column(name = "block_number")
	private int blockNumber;
	
	public Apartment(String name, Locality locality, int blockNumber) {
		
		this.name = name;
		this.locality = locality;
		this.blockNumber = blockNumber;
		
	}
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public Locality getLocalty(){
		return this.locality;
	}
	
	public void setLocality(Locality locality){
		this.locality = locality;
	}
	
	public int getBlockNumber(){
		return blockNumber;
	}
	
	public void setBlockNumber(int blockNumber){
		this.blockNumber = blockNumber;
	}
	
	@Override
	public String toString() {
		return "Apartment [id=" + id + ", name=" + name + ", locality="
				+ locality + ", blockNumber=" + blockNumber + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blockNumber;
		result = prime * result
				+ ((locality == null) ? 0 : locality.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apartment other = (Apartment) obj;
		if (blockNumber != other.blockNumber)
			return false;
		if (id != other.id)
			return false;
		if (locality == null) {
			if (other.locality != null)
				return false;
		} else if (!locality.equals(other.locality))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
