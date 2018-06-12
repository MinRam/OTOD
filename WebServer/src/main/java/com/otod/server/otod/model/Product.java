package com.otod.server.otod.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="product")
public class Product {
/*`product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '内部编号',
  `seller_id` int(11) NOT NULL,
  `product_encoding` varchar(64) NOT NULL COMMENT '商品编号',
  `product_name` varchar(64) NOT NULL,
  `product_price` double NOT NULL COMMENT '商品单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(255) DEFAULT NULL,
  `product_createtime` datetime NOT NULL COMMENT '创建时间',
  `product_life` int(11) NOT NULL COMMENT '有效期',
  `product_day_from` int(11) NOT NULL COMMENT '方便交易时间开始',
  `product_day_to` int(11) NOT NULL,
  `product_status` int(11) NOT NULL COMMENT '商品状态',
  `product_img_url` varchar(64) DEFAULT NULL,
  `product_catalog` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `p_mu_sid` (`seller_id`),
  KEY `p_c_pcid` (`product_catalog`),
  CONSTRAINT `p_c_pcid` FOREIGN KEY (`product_catalog`) REFERENCES `catalog` (`catalog_id`),
  CONSTRAINT `p_mu_sid` FOREIGN KEY (`seller_id`) REFERENCES `market_user` (`market_user_id`)
)
) */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	
	@ManyToOne
	@JoinColumn(name="seller_id")
	private Market_user seller;
	
	
	private String product_encoding;
	private String product_name;
	private double product_price;
	private int product_stock;
	private String product_description;
	private Date product_createtime;
	private int product_life;
	private int product_day_from;//一天展示开始时间
	private int product_day_to;//一天展示结束时间
	private int product_status;//1:上架，2：下架，3：正在交易
	private String product_img_url;
	
	@ManyToOne
	@JoinColumn(name="product_catalog")
	private Catalog product_catalog;
	
	@OneToMany(mappedBy="product")
	private Set<P_Order>orders = new HashSet<P_Order>();
	
	@OneToMany(mappedBy="product")
	private Set<Product_img>product_imgs = new HashSet<Product_img>();
	
	@OneToMany(mappedBy="product")
	private Set<Market_record>records = new HashSet<Market_record>();

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	@JsonBackReference
	public Market_user getSeller() {
		return seller;
	}
	@JsonBackReference
	public void setSeller(Market_user seller) {
		this.seller = seller;
	}
	public String getProduct_encoding() {
		return product_encoding;
	}

	public void setProduct_encoding(String product_encoding) {
		this.product_encoding = product_encoding;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getProduct_price() {
		return product_price;
	}

	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	

	public Date getProduct_createtime() {
		return product_createtime;
	}

	public void setProduct_createtime(Date product_createtime) {
		this.product_createtime = product_createtime;
	}

	public int getProduct_life() {
		return product_life;
	}

	public void setProduct_life(int product_life) {
		this.product_life = product_life;
	}

	public int getProduct_day_from() {
		return product_day_from;
	}

	public void setProduct_day_from(int product_day_from) {
		this.product_day_from = product_day_from;
	}

	public int getProduct_day_to() {
		return product_day_to;
	}

	public void setProduct_day_to(int product_day_to) {
		this.product_day_to = product_day_to;
	}

	

	public int getProduct_status() {
		return product_status;
	}

	public void setProduct_status(int product_status) {
		this.product_status = product_status;
	}


	public Set<P_Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<P_Order> orders) {
		this.orders = orders;
	}

	public Set<Product_img> getProduct_imgs() {
		return product_imgs;
	}

	public void setProduct_imgs(Set<Product_img> product_imgs) {
		this.product_imgs = product_imgs;
	}
	
	public String getProduct_img_url() {
		return product_img_url;
	}

	public void setProduct_img_url(String product_img_url) {
		this.product_img_url = product_img_url;
	}
	@JsonBackReference
	public Catalog getProduct_catalog() {
		return product_catalog;
	}
	@JsonBackReference
	public void setProduct_catalog(Catalog product_catalog) {
		this.product_catalog = product_catalog;
	}

	public Set<Market_record> getRecords() {
		return records;
	}

	public void setRecords(Set<Market_record> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", seller=" + seller + ", product_encoding=" + product_encoding
				+ ", product_name=" + product_name + ", product_price=" + product_price + ", product_stock="
				+ product_stock + ", product_description=" + product_description + ", product_createtime="
				+ product_createtime + ", product_life=" + product_life + ", product_day_from=" + product_day_from
				+ ", product_day_to=" + product_day_to + ", product_status=" + product_status + ", product_img_url="
				+ product_img_url + ", product_catalog=" + product_catalog + ", orders=" + orders + ", product_imgs="
				+ product_imgs + ", records=" + records + "]";
	}

	

	


	

	
}
