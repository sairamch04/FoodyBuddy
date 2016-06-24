package com.FoodyBuddy.Model;
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

@Entity
@Table(name="Seller")
public class Seller{
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name="email")
	private String email;

	@Column(name="mobile")
	private String mobile;

	@Column(name="flat_no")
	private String flatNo;
	
	@Column(name = "appartment_id", nullable = false)
    @ManyToOne
    @JoinColumn(name = "id")
    private Appartment  appartment;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date created_at;
	
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

	public Appartment getAppartment() {
		return appartment;
	}

	public void setAppartmentId(Appartment appartment) {
		this.appartment = appartment;
	}

	public Seller(String name, String email, String mobile, String flatNo, Appartment appartment) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.flatNo = flatNo;
		this.appartment = appartment;
	}	

	
}