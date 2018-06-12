package com.otod.server.otod.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="market_user")
public class Market_user {
/*CREATE TABLE `market_user` (
  `market_user_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`market_user_id`)
)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int market_user_id;
	
	
	@OneToMany(mappedBy="buyer")
	private Set<P_Order> orders = new HashSet<P_Order>();
	
	@OneToMany(mappedBy="seller")
	private Set<Product>products = new HashSet<Product>();
	
	@OneToMany(mappedBy="market_user")
	private Set<Market_record>records = new HashSet<Market_record>();
	
	public int getMarket_user_id() {
		return market_user_id;
	}

	public void setMarket_user_id(int market_user_id) {
		this.market_user_id = market_user_id;
	}


	public Set<P_Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<P_Order> orders) {
		this.orders = orders;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<Market_record> getRecords() {
		return records;
	}

	public void setRecords(Set<Market_record> records) {
		this.records = records;
	}
	
	
	
}
