package com.otod.server.otod.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="product_img")
public class Product_img {
/*CREATE TABLE `product_img` (
  `product_img_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  PRIMARY KEY (`product_img_id`),
  KEY `product_img_1` (`product_id`),
  CONSTRAINT `product_img_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_img_id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@Cascade(value={CascadeType.MERGE})
	private Product product;
	
	private String img_url;

	public int getProduct_img_id() {
		return product_img_id;
	}

	public void setProduct_img_id(int product_img_id) {
		this.product_img_id = product_img_id;
	}
	@JsonBackReference
	public Product getProduct() {
		return product;
	}
	@JsonBackReference
	public void setProduct(Product product) {
		this.product = product;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
}
