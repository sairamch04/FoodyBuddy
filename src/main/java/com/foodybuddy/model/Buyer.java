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
 * 
 * Model for Buyer Table
 *
 */
@Entity
@Audited
@Table(name="Buyer")
public class Buyer{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name="apartment_id", nullable=false)
	private Apartment apartment;
	
	@Column(name="last_modified_by_id")
	private Integer lastModifiedById;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="mobile_no", nullable=false)
	private String mobileNumber;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="flat_no")
	private String flatNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
	private Date deletedAt;

	@Column(name="is_active" , nullable = false , columnDefinition = "boolean default true")
    private Boolean isActive;
	
	public Buyer() {}
	
	/***
	 * Buyer Constructor
	 * 
	 * @param apartment
	 * @param lastModifiedById
	 * @param name
	 * @param mobileNumber
	 * @param email
	 * @param flatNumber
	 * @param createdAt
	 * @param updatedAt
	 * @param deletedAt
	 * @param isActive
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

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Apartment getApartment(){
		return apartment;
	}

	public void setApartment(Apartment apartment){
		this.apartment = apartment;
	}
	
	public Integer getLastModifiedById(){
		return lastModifiedById;
	}
	
	public void setLastModifiedById(Integer lastModifiedById){
		this.lastModifiedById = lastModifiedById;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPhoneNumber(){
		return mobileNumber;
	}

	public void setPhoneNumber(String mobileNumber){
		this.mobileNumber = mobileNumber;
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

	public Date getCreatedAt(){
		return createdAt;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getDeletedAt(){
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt){
		this.deletedAt = deletedAt;
	}

    @Override
    public String toString() {
        return "Buyer [id=" + id + ", apartment=" + apartment
                + ", lastModifiedById=" + lastModifiedById + ", name=" + name
                + ", mobileNumber=" + mobileNumber + ", email=" + email
                + ", flatNumber=" + flatNumber + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt
                + ", isActive=" + isActive + "]";
    }

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
	
}
