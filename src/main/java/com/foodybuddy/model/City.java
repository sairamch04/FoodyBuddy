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
import javax.validation.constraints.NotNull;
import org.hibernate.envers.Audited;


/**
 * The Class City.
 */
@Entity
@Audited
@Table(name = "City")
public class City {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	/** The name. */
	@Column(name = "name", nullable = false)
	private String name;
	
	
	/** The state. */
	@NotNull
	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	
	/** The created at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	/** The modified at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	/** The deleted at. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at")
	private Date deletedAt;
	
	/** The modified by id. */
	@Column(name = "modified_by_id")
	private Integer modifiedById;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id){
		this.id = id;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public State getState(){
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(State state){
		this.state = state;
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
	 * Gets the modified at.
	 *
	 * @return the modified at
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * Sets the modified at.
	 *
	 * @param modifiedAt the new modified at
	 */
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
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

	/**
	 * Gets the modified by id.
	 *
	 * @return the modified by id
	 */
	public Integer getModifiedById() {
		return modifiedById;
	}

	/**
	 * Sets the modified by id.
	 *
	 * @param modifiedById the new modified by id
	 */
	public void setModifiedById(Integer modifiedById) {
		this.modifiedById = modifiedById;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", state=" + state + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		City other = (City) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
}