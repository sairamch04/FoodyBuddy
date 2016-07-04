package com.foodybuddy.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;

/**
 * The Class Apartment.
 */
@Entity
@Audited
@Table(name="Apartment")
public class Apartment {
	
	/** The id. */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/** The name. */
	@Column(name="name", nullable=false)
	private String name;
	
	/** The locality. */
	@ManyToOne
	@JoinColumn(name="locality_id", nullable=false)
	private Locality locality;
	
	/** The block number. */
	@Column(name="block_number", nullable=false)
	private Integer blockNumber;
	
	/** The is active. */
	@Column(name = "is_active")
	private Boolean isActive;
	
	/** The created at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;
	
	

	/** The modified at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	/** The deleted at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at")
	private Date deletedAt;
	
	/** The modified by id. */
	@Column(name = "modified_by_id")
	private Integer modifiedById;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Integer getModifiedById() {
		return modifiedById;
	}

	public void setModifiedById(Integer modifiedById) {
		this.modifiedById = modifiedById;
	}

	/**
	 * Instantiates a new apartment.
	 */
	public Apartment() {}
	
	/**
	 * Instantiates a new apartment.
	 *
	 * @param name the name
	 * @param locality the locality
	 * @param blockNumber the block number
	 */
	public Apartment(String name, Locality locality, Integer blockNumber) {
		this.name = name;
		this.locality = locality;
		this.blockNumber = blockNumber;
	}

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId(){
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id){
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Gets the locality.
	 *
	 * @return the locality
	 */
	public Locality getLocality(){
		return this.locality;
	}
	
	/**
	 * Sets the locality.
	 *
	 * @param locality the new locality
	 */
	public void setLocality(Locality locality){
		this.locality = locality;
	}
	
	/**
	 * Gets the block number.
	 *
	 * @return the block number
	 */
	public Integer getBlockNumber(){
		return blockNumber;
	}
	
	/**
	 * Sets the block number.
	 *
	 * @param blockNumber the new block number
	 */
	public void setBlockNumber(Integer blockNumber){
		this.blockNumber = blockNumber;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Apartment [id=" + id + ", name=" + name + ", locality="
                + locality + ", blockNumber=" + blockNumber + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
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
