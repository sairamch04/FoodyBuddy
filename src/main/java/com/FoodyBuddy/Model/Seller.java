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

@Entity
@Audited
@Table(name="Seller")
public class Seller{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name="email")
	private String email;

	@Column(name="mobile")
	private String mobile;

	@Column(name="flat_no")
	private String flatNo;
	
    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
	
	@Column(name="is_active" , nullable = false , columnDefinition = "boolean default true")
	private Boolean isActive;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "deleted_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date deletedAt;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}


	public Apartment getApartment() {
		return apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Seller(String name, String email, String mobile, String flatNo, Apartment apartment) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.flatNo = flatNo;
		this.apartment = apartment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((flatNo == null) ? 0 : flatNo.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
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
		Seller other = (Seller) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (flatNo == null) {
			if (other.flatNo != null)
				return false;
		} else if (!flatNo.equals(other.flatNo))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
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
		return "Seller [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", flatNo=" + flatNo
				+ ", isActive=" + isActive + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt="
				+ deletedAt + "]";
	}
	
	
	
}