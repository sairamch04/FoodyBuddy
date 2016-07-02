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

/***
 * 
 * Model for Locality table
 *
 */
@Entity
@Audited
@Table(name="Locality")
public class Locality {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="pincode")
	private String pincode;
	
	@ManyToOne
	@JoinColumn(name="city_id", nullable=false)
	private City city;
	
	public Locality() {}
	
	/***
	 * Locality Constructor
	 * 
	 * @param name
	 * @param pincode
	 * @param city
	 */
	public Locality(String name, String pincode, City city){
		this.name = name;
		this.pincode = pincode;
		this.city = city;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Locality [id=" + id + ", name=" + name + ", pincode=" + pincode
                + ", city=" + city + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
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
        Locality other = (Locality) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (pincode == null) {
            if (other.pincode != null)
                return false;
        } else if (!pincode.equals(other.pincode))
            return false;
        return true;
    }
		
}
