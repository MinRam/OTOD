package com.otod.server.otod.model;



import java.util.Date;

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
@Table(name="market_record")
public class Market_record {
/*CREATE TABLE `market_record` (
  `market_record_id` int(11) NOT NULL,
  `operation` int(11) NOT NULL COMMENT '操作类型',
  `market_user_id` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `p_order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`market_record_id`),
  KEY `mr_mu_muid` (`market_user_id`),
  KEY `mr_p_pid` (`product_id`),
  KEY `mr_po_poid` (`p_order_id`),
  CONSTRAINT `mr_mu_muid` FOREIGN KEY (`market_user_id`) REFERENCES `market_user` (`market_user_id`),
  CONSTRAINT `mr_p_pid` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `mr_po_poid` FOREIGN KEY (`p_order_id`) REFERENCES `p_order` (`order_id`)
)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int market_record_id;
	private int operation;
	@ManyToOne
	@JoinColumn(name="market_user_id")
	@Cascade(value={CascadeType.MERGE})
	Market_user market_user;
	
	Date createtime;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@Cascade(value={CascadeType.MERGE})
	Product product;
	
	@ManyToOne
	@JoinColumn(name="p_order_id")
	@Cascade(value={CascadeType.MERGE})
	P_Order p_Order;

	public int getMarket_record_id() {
		return market_record_id;
	}

	public void setMarket_record_id(int market_record_id) {
		this.market_record_id = market_record_id;
	}

	public int getOperation() {
		return operation;
	}

	public void setOperation(int operation) {
		this.operation = operation;
	}
	@JsonBackReference
	public Market_user getMarket_user() {
		return market_user;
	}
	@JsonBackReference
	public void setMarket_user(Market_user market_user) {
		this.market_user = market_user;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	@JsonBackReference
	public Product getProduct() {
		return product;
	}
	@JsonBackReference
	public void setProduct(Product product) {
		this.product = product;
	}
	@JsonBackReference
	public P_Order getP_Order() {
		return p_Order;
	}
	@JsonBackReference
	public void setP_Order(P_Order p_Order) {
		this.p_Order = p_Order;
	}
	
	
	
}
