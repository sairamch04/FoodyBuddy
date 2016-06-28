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
 * The Class Order.
 */
@Entity
@Audited
@Table(name = "orders")
public class Order {

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** The buyer. */
	@ManyToOne
	@JoinColumn(name = "buyer_id", nullable = false)
	private Buyer buyer;

	/** The status. */
	@Column(name = "status", nullable = false)
	private int status;

	/** The net order amount. */
	@Column(name = "net_order_amount", nullable = false)
	private int netOrderAmount;

	/** The created at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	/** The updated at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	/** The deleted at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at")
	private Date deletedAt;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the buyer.
	 *
	 * @return the buyer
	 */
	public Buyer getBuyer() {
		return buyer;
	}

	/**
	 * Sets the buyer.
	 *
	 * @param buyer
	 *            the new buyer
	 */
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Gets the net order amount.
	 *
	 * @return the net order amount
	 */
	public int getNetOrderAmount() {
		return netOrderAmount;
	}

	/**
	 * Sets the net order amount.
	 *
	 * @param netOrderAmount
	 *            the new net order amount
	 */
	public void setNetOrderAmount(int netOrderAmount) {
		this.netOrderAmount = netOrderAmount;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt
	 *            the new created at
	 */
	public void setCreatedAt(Date createdAt) {
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
	 * @param updatedAt
	 *            the new updated at
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * Gets the deleted at.
	 *
	 * @return the deleted at
	 */
	public Date getDeletedAt() {
		return deletedAt;
	}

	/**
	 * Sets the deleted at.
	 *
	 * @param deletedAt
	 *            the new deleted at
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + netOrderAmount;
		result = prime * result + status;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Order other = (Order) obj;
		if (netOrderAmount != other.netOrderAmount)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + "status=" + status + ", netOrderAmount=" + netOrderAmount + "]";
	}
}
