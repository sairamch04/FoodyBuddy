package com.foodybuddy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * 
 * Model for Apartment table
 *
 */
@Entity
@Audited
@Table(name="Apartment")
public class Apartment {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="locality_id", nullable=false)
	private Locality locality;
	
	@Column(name="block_number", nullable=false)
	private Integer blockNumber;
	
	public Apartment() {}
	
	/**
	 *Constructor 
	 *
	 * @param name
	 * @param locality
	 * @param blockNumber
	 */
	public Apartment(String name, Locality locality, Integer blockNumber) {
		this.name = name;
		this.locality = locality;
		this.blockNumber = blockNumber;
	}

    public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
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
	
	public Integer getBlockNumber(){
		return blockNumber;
	}
	
	public void setBlockNumber(Integer blockNumber){
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
        result = prime * result
                + ((blockNumber == null) ? 0 : blockNumber.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (blockNumber == null) {
            if (other.blockNumber != null)
                return false;
        } else if (!blockNumber.equals(other.blockNumber))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
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
