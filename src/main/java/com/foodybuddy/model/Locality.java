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

// TODO: Auto-generated Javadoc
/**
 * The Class Locality.
 */
@Entity
@Audited
@Table(name="Locality")
public class Locality {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	/** The name. */
	@Column(name="name", nullable=false)
	private String name;
	
	/** The pincode. */
	@Column(name="pincode")
	private String pincode;
	
	/** The city. */
	@ManyToOne
	@JoinColumn(name="city_id", nullable=false)
	private City city;
	
	/**
	 * Instantiates a new locality.
	 */
	public Locality() {}
	
	/**
	 * Instantiates a new locality.
	 *
	 * @param name the name
	 * @param pincode the pincode
	 * @param city the city
	 */
	public Locality(String name, String pincode, City city){
		this.name = name;
		this.pincode = pincode;
		this.city = city;
	}

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the pincode.
     *
     * @return the pincode
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * Sets the pincode.
     *
     * @param pincode the new pincode
     */
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Locality [id=" + id + ", name=" + name + ", pincode=" + pincode
                + ", city=" + city + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
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

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
