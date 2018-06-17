package com.otod.server.otod.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="catalog")
public class Catalog {
/*CREATE TABLE `catalog` (
  `catalog_id` int(11) NOT NULL AUTO_INCREMENT,
  `catalog_name` varchar(32) NOT NULL COMMENT '分类名',
  PRIMARY KEY (`catalog_id`)
)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int catalog_id;
	private String catalog_name;
	
	@OneToMany(mappedBy="product_catalog")
	@Cascade(value={CascadeType.REMOVE})
	private Set<Product>product = new HashSet<Product>();
	
	
	public int getCatalog_id() {
		return catalog_id;
	}
	public void setCatalog_id(int catalog_id) {
		this.catalog_id = catalog_id;
	}
	public String getCatalog_name() {
		return catalog_name;
	}
	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}
	
}
