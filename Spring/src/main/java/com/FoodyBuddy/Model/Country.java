package com.FoodyBuddy.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


	@Entity
	@Table(name="Country")
	public class Country
	{
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="id", length=30)
		private int id;
		
		@Column(name="name", length=30)
		private String name;
		

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public Country(){
		}
		
		public Country(String name) {
			super();
			this.name = name;
		}
	}