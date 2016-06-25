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
import org.hibernate.envers.Audited;


@Entity
@Audited
@Table(name="order_dish")
public class OrderDish{

	@Id
	@Column(name="id")
	@GeneratedValue
	private int id;

	@Column(name = "order_id", nullable = false)
	@ManyToOne
	@JoinColumn(name = "id")
	private Order  order;

	@Column(name = "dish_id", nullable = false)
	@ManyToOne
	@JoinColumn(name = "id")
	private Dish dish;

	@Column(name="quantity")
	private int quantity;

	@Column(name="net_dish_price")
	private int netDishPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at", nullable = false)
	private Date deletedAt;


	public OrderDish(int netDishPrice, int quantity, Order order, Dish dish) {
		this.netDishPrice = netDishPrice;
		this.quantity = quantity;
		this.order = order;
		this.dish = dish;
	}

	public int getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public Dish getDish() {
		return dish;
	}


	public void setDish(Dish dish) {
		this.dish = dish;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getNetDishPrice() {
		return netDishPrice;
	}


	public void setNetDishPrice(int netDishPrice) {
		this.netDishPrice = netDishPrice;
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
		result = prime * result + netDishPrice;
		result = prime * result + quantity;
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
		OrderDish other = (OrderDish) obj;
		if (netDishPrice != other.netDishPrice)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderDish [id=" + id + ", quantity=" + quantity + ", netDishPrice=" + netDishPrice + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}	


}