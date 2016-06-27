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
@Table(name="_order")
public class Order{
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;	
	
	@Column(name="name" , nullable = false)
	private String name;
	
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer  buyer;
	
	@Column(name ="status", nullable = false, columnDefinition ="int default 1")
	private int status;
	
	@Column(name = "net_order_amount")
	private int netOrderAmount;
	

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at",columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date updatedAt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at",columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + netOrderAmount;
		result = prime * result + status;
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
		Order other = (Order) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (netOrderAmount != other.netOrderAmount)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [name=" + name + ", status=" + status + ", netOrderAmount=" + netOrderAmount + "]";
	}
}
