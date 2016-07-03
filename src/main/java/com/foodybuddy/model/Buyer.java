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
 * The Class Buyer.
 */
@Entity
@Audited
@Table(name="Buyer")
public class Buyer{
	
	/** The id. */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	/** The apartment. */
	@ManyToOne
	@JoinColumn(name="apartment_id", nullable=false)
	private Apartment apartment;
	
	/** The last modified by id. */
	@Column(name="last_modified_by_id")
	private Integer lastModifiedById;
	
	/** The name. */
	@Column(name="name", nullable=false)
	private String name;
	
	/** The mobile number. */
	@Column(name="mobile_no", nullable=false)
	private String mobileNumber;
	
	/** The email. */
	@Column(name="email", nullable=false)
	private String email;
	
	/** The flat number. */
	@Column(name="flat_no")
	private String flatNumber;
	
	/** The created at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createdAt;
	
	/** The updated at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date updatedAt;
	
	/** The deleted at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date deletedAt;

	/** The is active. */
	@Column(name="is_active" , nullable = false , columnDefinition = "boolean default true")
    private Boolean isActive;
	
	/**
	 * Instantiates a new buyer.
	 */
	public Buyer() {}
	
	/**
	 * Instantiates a new buyer.
	 *
	 * @param apartment the apartment
	 * @param lastModifiedById the last modified by id
	 * @param name the name
	 * @param mobileNumber the mobile number
	 * @param email the email
	 * @param flatNumber the flat number
	 * @param isActive the is active
	 */
	public Buyer(Apartment apartment, Integer lastModifiedById, String name, String mobileNumber, String email, String flatNumber, Boolean isActive){		
		this.apartment = apartment;
		this.lastModifiedById = lastModifiedById;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.flatNumber = flatNumber;
		this.isActive = isActive;
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
	 * Gets the apartment.
	 *
	 * @return the apartment
	 */
	public Apartment getApartment(){
		return apartment;
	}

	/**
	 * Sets the apartment.
	 *
	 * @param apartment the new apartment
	 */
	public void setApartment(Apartment apartment){
		this.apartment = apartment;
	}
	
	/**
	 * Gets the last modified by id.
	 *
	 * @return the last modified by id
	 */
	public Integer getLastModifiedById(){
		return lastModifiedById;
	}
	
	/**
	 * Sets the last modified by id.
	 *
	 * @param lastModifiedById the new last modified by id
	 */
	public void setLastModifiedById(Integer lastModifiedById){
		this.lastModifiedById = lastModifiedById;
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
	 * Gets the mobile number.
	 *
	 * @return the mobile number
	 */
	public String getMobileNumber(){
		return mobileNumber;
	}

	/**
	 * Sets the mobile number.
	 *
	 * @param mobileNumber the new mobile number
	 */
	public void setMobileNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * Gets the flat number.
	 *
	 * @return the flat number
	 */
	public String getFlatNumber(){
		return flatNumber;
	}

	/**
	 * Sets the flat number.
	 *
	 * @param flatNumber the new flat number
	 */
	public void setFlatNumber(String flatNumber){
		this.flatNumber = flatNumber;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt(){
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updatedAt the new updated at
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	/**
	 * Gets the deleted at.
	 *
	 * @return the deleted at
	 */
	public Date getDeletedAt(){
		return deletedAt;
	}

	/**
	 * Sets the deleted at.
	 *
	 * @param deletedAt the new deleted at
	 */
	public void setDeletedAt(Date deletedAt){
		this.deletedAt = deletedAt;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Buyer [id=" + id + ", apartment=" + apartment
                + ", lastModifiedById=" + lastModifiedById + ", name=" + name
                + ", mobileNumber=" + mobileNumber + ", email=" + email
                + ", flatNumber=" + flatNumber + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt
                + ", isActive=" + isActive + "]";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((apartment == null) ? 0 : apartment.hashCode());
        result = prime * result
                + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result
                + ((deletedAt == null) ? 0 : deletedAt.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result
                + ((flatNumber == null) ? 0 : flatNumber.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((isActive == null) ? 0 : isActive.hashCode());
        result = prime
                * result
                + ((lastModifiedById == null) ? 0 : lastModifiedById.hashCode());
        result = prime * result
                + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
                + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
        Buyer other = (Buyer) obj;
        if (apartment == null) {
            if (other.apartment != null)
                return false;
        } else if (!apartment.equals(other.apartment))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        } else if (!createdAt.equals(other.createdAt))
            return false;
        if (deletedAt == null) {
            if (other.deletedAt != null)
                return false;
        } else if (!deletedAt.equals(other.deletedAt))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (flatNumber == null) {
            if (other.flatNumber != null)
                return false;
        } else if (!flatNumber.equals(other.flatNumber))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isActive == null) {
            if (other.isActive != null)
                return false;
        } else if (!isActive.equals(other.isActive))
            return false;
        if (lastModifiedById == null) {
            if (other.lastModifiedById != null)
                return false;
        } else if (!lastModifiedById.equals(other.lastModifiedById))
            return false;
        if (mobileNumber == null) {
            if (other.mobileNumber != null)
                return false;
        } else if (!mobileNumber.equals(other.mobileNumber))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        } else if (!updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }

    /**
     * Gets the checks if is active.
     *
     * @return the checks if is active
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Sets the checks if is active.
     *
     * @param isActive the new checks if is active
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
	
}
