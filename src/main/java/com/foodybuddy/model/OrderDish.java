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


/**
 * The Class OrderDish.
 */
@Entity
@Audited
@Table(name="order_dish")
public class OrderDish{

	/** The id. */
	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;

	/** The order. */
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	/** The dish. */
	@ManyToOne
	@JoinColumn(name = "dish_id", nullable = false)
	private Dish dish;

	/** The quantity. */
	@Column(name="quantity", nullable = false)
	private int quantity;

	/** The net dish price. */
	@Column(name="net_dish_price", nullable = false)
	private int netDishPrice;

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


	/**
	 * Instantiates a new order dish.
	 *
	 * @param netDishPrice the net dish price
	 * @param quantity the quantity
	 * @param order the order
	 * @param dish the dish
	 */
	public OrderDish(int netDishPrice, int quantity, Order order, Dish dish) {
		this.netDishPrice = netDishPrice;
		this.quantity = quantity;
		this.order = order;
		this.dish = dish;
	}

	/**
	 * Instantiates a new order dish.
	 */
	public OrderDish() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}


	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}


	/**
	 * Gets the dish.
	 *
	 * @return the dish
	 */
	public Dish getDish() {
		return dish;
	}


	/**
	 * Sets the dish.
	 *
	 * @param dish the new dish
	 */
	public void setDish(Dish dish) {
		this.dish = dish;
	}


	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}


	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	/**
	 * Gets the net dish price.
	 *
	 * @return the net dish price
	 */
	public int getNetDishPrice() {
		return netDishPrice;
	}


	/**
	 * Sets the net dish price.
	 *
	 * @param netDishPrice the new net dish price
	 */
	public void setNetDishPrice(int netDishPrice) {
		this.netDishPrice = netDishPrice;
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
	 * @param createdAt the new created at
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
	public Date getDeletedAt() {
		return deletedAt;
	}


	/**
	 * Sets the deleted at.
	 *
	 * @param deletedAt the new deleted at
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + netDishPrice;
		result = prime * result + quantity;
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
		OrderDish other = (OrderDish) obj;
		if (netDishPrice != other.netDishPrice)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDish [id=" + id + ", quantity=" + quantity + ", netDishPrice=" + netDishPrice + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}	


}
