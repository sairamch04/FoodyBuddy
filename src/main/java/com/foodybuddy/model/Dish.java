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
 * The Class Dish.
 */
@Entity
@Audited
@Table(name = "Dish")
public class Dish {

	/** The id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The name. */
	@Column(name = "name", nullable = false)
	private String name;

	/** The description. */
	@Column(name = "description")
	private String description;

	/** The seller. */
	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private Seller seller;

	/** The price. */
	@Column(name = "price", nullable = false)
	private Integer price;

	/** The order by. */
	@Column(name = "order_by", nullable = false)
	private Date orderBy;

	/** The dish available start. */
	@Column(name = "dish_available_start", nullable = false)
	private Date dishAvailableStart;

	/** The dish available end. */
	@Column(name = "dish_available_end", nullable = false)
	private Date dishAvailableEnd;

	/** The is veg. */
	@Column(name = "is_veg", nullable = false)
	private Boolean isVeg;

	/** The is active. */
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

	/** The quantity available. */
	@Column(name = "quantity_available", nullable = false)
	private Integer quantityAvailable;

	/** The created at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createdAt;

	/** The updated at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date updatedAt;

	/** The deleted at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date deletedAt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Date orderBy) {
		this.orderBy = orderBy;
	}

	public Date getDishAvailableStart() {
		return dishAvailableStart;
	}

	public void setDishAvailableStart(Date dishAvailableStart) {
		this.dishAvailableStart = dishAvailableStart;
	}

	public Date getDishAvailableEnd() {
		return dishAvailableEnd;
	}

	public void setDishAvailableEnd(Date dishAvailableEnd) {
		this.dishAvailableEnd = dishAvailableEnd;
	}

	public Boolean getIsVeg() {
		return isVeg;
	}

	public void setIsVeg(Boolean isVeg) {
		this.isVeg = isVeg;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public Dish(){
	}
	
	public Dish(String name, String description, Seller seller, Integer price, Date orderBy, Date dishAvailableStart,
			Date dishAvailableEnd, Boolean isVeg, Boolean isActive, Integer quantityAvailable) {
		super();
		this.name = name;
		this.description = description;
		this.seller = seller;
		this.price = price;
		this.orderBy = orderBy;
		this.dishAvailableStart = dishAvailableStart;
		this.dishAvailableEnd = dishAvailableEnd;
		this.isVeg = isVeg;
		this.isActive = isActive;
		this.quantityAvailable = quantityAvailable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dishAvailableEnd == null) ? 0 : dishAvailableEnd.hashCode());
		result = prime * result + ((dishAvailableStart == null) ? 0 : dishAvailableStart.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((isVeg == null) ? 0 : isVeg.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quantityAvailable == null) ? 0 : quantityAvailable.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
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
		Dish other = (Dish) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dishAvailableEnd == null) {
			if (other.dishAvailableEnd != null)
				return false;
		} else if (!dishAvailableEnd.equals(other.dishAvailableEnd))
			return false;
		if (dishAvailableStart == null) {
			if (other.dishAvailableStart != null)
				return false;
		} else if (!dishAvailableStart.equals(other.dishAvailableStart))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (isVeg == null) {
			if (other.isVeg != null)
				return false;
		} else if (!isVeg.equals(other.isVeg))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orderBy == null) {
			if (other.orderBy != null)
				return false;
		} else if (!orderBy.equals(other.orderBy))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantityAvailable == null) {
			if (other.quantityAvailable != null)
				return false;
		} else if (!quantityAvailable.equals(other.quantityAvailable))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", description=" + description + ", seller=" + seller + ", price="
				+ price + ", orderBy=" + orderBy + ", dishAvailableStart=" + dishAvailableStart + ", dishAvailableEnd="
				+ dishAvailableEnd + ", isVeg=" + isVeg + ", isActive=" + isActive + ", quantityAvailable="
				+ quantityAvailable + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt="
				+ deletedAt + "]";
	}

}
