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
@Table(name="Order")
public class Order{
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;
	
	
	@Column(name="name" , nullable = false)
	private String name;
	
	@Column(name = "buyer_id", nullable = false)
    @ManyToOne
    @JoinColumn(name = "id")
    private Buyer  buyer;
	
	@Column(name ="status")
	private String status;
	
	@Column(name = "net_order_amount")
	private int netOrderAmount;
	

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at", nullable = false)
    private Date deletedAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNetOrderAmount() {
		return netOrderAmount;
	}

	public void setNetOrderAmount(int netOrderAmount) {
		this.netOrderAmount = netOrderAmount;
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

	
}