package com.foodybuddy.model;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "Buyer")
public class Buyer{
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	private int id;
	
//	@Column(name = "apartment_id", nullable = false)
//	@ManyToOne
//	@JoinColumn(name = "id")
//	private Apartment apartment;
	
	@Column(name = "last_modified_by_id")
	private int lastModifiedById;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "mobile_no")
	private String mobileNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "flat_no")
	private String flatNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at")
	private Date deletedAt;

	@Column(name="is_active" , columnDefinition = "boolean default true")
    	private Boolean isActive;
	
	public Buyer(){}
	
//	public Buyer(Apartment apartment, int lastModifiedById, String name, String mobileNumber, String email, String flatNumber, Date createdAt, Date updatedAt, Date deletedAt, Boolean isActive){		
//		this.apartment = apartment;
//		this.lastModifiedById = lastModifiedById;
//		this.name = name;
//		this.mobileNumber = mobileNumber;
//		this.email = email;
//		this.flatNumber = flatNumber;
//		this.createdAt = createdAt;
//		this.updatedAt = updatedAt;
//		this.deletedAt = deletedAt;
//		this.isActive = isActive;
//	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

//	public Apartment getApartment(){
//		return apartment;
//	}
//
//	public void setApartment(Apartment apartment){
//		this.apartment = apartment;
//	}
//	
	public int getLastModifiedById(){
		return lastModifiedById;
	}
	
	public void setLastModifiedById(int lastModifiedById){
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((flatNumber == null) ? 0 : flatNumber.hashCode());
        result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
        result = prime * result + ((mobileNumber == null) ? 0 : mobileNumber.hashCode());
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
        Buyer other = (Buyer) obj;
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
        if (isActive == null) {
            if (other.isActive != null)
                return false;
        } else if (!isActive.equals(other.isActive))
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
        return true;
    }
    
    @Override
    public String toString() {
        return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", flatNumber=" + flatNumber
                + ", isActive=" + isActive + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt="
                + deletedAt + "lastModifiedById" + lastModifiedById + "]";
    }
}