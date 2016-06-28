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

// TODO: Auto-generated Javadoc
/**
 * The Class Dish.
 */
@Entity
@Audited
@Table(name="Dish")
public class Dish{
	
	/** The id. */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/** The name. */
	@Column(name="name" , nullable=false)
	private String name;

	/** The description. */
	@Column(name="description")
	private String description;
	
    /** The seller. */
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller  seller;

	/** The price. */
	@Column(name="price",nullable=false)
	private Integer price;

	/** The order by. */
	@Column(name="order_by",nullable=false)
	private Date orderBy;
	
	/** The dish available start. */
	@Column(name="dish_available_start",nullable=false)
	private Date dishAvailableStart;

	/** The dish available end. */
	@Column(name="dish_available_end",nullable=false)
	private Date dishAvailableEnd;
	
	/** The is veg. */
	@Column(name="is_veg",nullable = false )
	private Boolean isVeg;

	/** The quantity available. */
	@Column(name="quantity_available",nullable=false)
	private Integer quantityAvailable;
	

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
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the seller.
	 *
	 * @return the seller
	 */
	public Seller getSeller() {
		return seller;
	}

	/**
	 * Sets the seller.
	 *
	 * @param seller the new seller
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public Date getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(Date orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * Gets the dish available start.
	 *
	 * @return the dish available start
	 */
	public Date getDishAvailableStart() {
		return dishAvailableStart;
	}

	/**
	 * Sets the dish available start.
	 *
	 * @param dishAvailableStart the new dish available start
	 */
	public void setDishAvailableStart(Date dishAvailableStart) {
		this.dishAvailableStart = dishAvailableStart;
	}

	/**
	 * Gets the dish available end.
	 *
	 * @return the dish available end
	 */
	public Date getDishAvailableEnd() {
		return dishAvailableEnd;
	}

	/**
	 * Sets the dish available end.
	 *
	 * @param dishAvailableEnd the new dish available end
	 */
	public void setDishAvailableEnd(Date dishAvailableEnd) {
		this.dishAvailableEnd = dishAvailableEnd;
	}

	/**
	 * Gets the checks if is veg.
	 *
	 * @return the checks if is veg
	 */
	public Boolean getIsVeg() {
		return isVeg;
	}

	/**
	 * Sets the checks if is veg.
	 *
	 * @param isVeg the new checks if is veg
	 */
	public void setIsVeg(Boolean isVeg) {
		this.isVeg = isVeg;
	}

	/**
	 * Gets the quantity available.
	 *
	 * @return the quantity available
	 */
	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	/**
	 * Sets the quantity available.
	 *
	 * @param quantityAvailable the new quantity available
	 */
	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	
	/**
	 * Instantiates a new dish.
	 */
	public Dish(){
	}
	
	/**
	 * Instantiates a new dish.
	 *
	 * @param name the name
	 * @param description the description
	 * @param seller the seller
	 * @param price the price
	 * @param orderBy the order by
	 * @param dishAvailableStart the dish available start
	 * @param dishAvailableEnd the dish available end
	 * @param isVeg the is veg
	 * @param quantityAvailable the quantity available
	 */
	public Dish(String name, String description, Seller seller, Integer price, Date orderBy, Date dishAvailableStart,
			Date dishAvailableEnd, Boolean isVeg, Integer quantityAvailable) {
		super();
		this.name = name;
		this.description = description;
		this.seller = seller;
		this.price = price;
		this.orderBy = orderBy;
		this.dishAvailableStart = dishAvailableStart;
		this.dishAvailableEnd = dishAvailableEnd;
		this.isVeg = isVeg;
		this.quantityAvailable = quantityAvailable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dishAvailableEnd == null) ? 0 : dishAvailableEnd.hashCode());
		result = prime * result + ((dishAvailableStart == null) ? 0 : dishAvailableStart.hashCode());
		result = prime * result + ((isVeg == null) ? 0 : isVeg.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
		result = prime * result + price;
		result = prime * result + quantityAvailable;
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
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
		if (price != other.price)
			return false;
		if (quantityAvailable != other.quantityAvailable)
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", description=" + description + ", seller=" + seller + ", price="
				+ price + ", orderBy=" + orderBy + ", dishAvailableStart=" + dishAvailableStart + ", dishAvailableEnd="
				+ dishAvailableEnd + ", isVeg=" + isVeg + ", quantityAvailable=" + quantityAvailable + "]";
	}
	
}