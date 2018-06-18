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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="p_order")
public class P_Order {
/*CREATE TABLE `p_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_num` int(11) NOT NULL,
  `user_order_encoding` varchar(32) NOT NULL COMMENT '订单编码',
  `status` int(11) NOT NULL COMMENT '订单状态',
  `createtime` datetime NOT NULL,
  `phone` varchar(32) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `o_mu_bid` (`buyer_id`),
  KEY `o_p_pid` (`product_id`),
  CONSTRAINT `o_mu_bid` FOREIGN KEY (`buyer_id`) REFERENCES `market_user` (`market_user_id`),
  CONSTRAINT `o_p_pid` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	
	@ManyToOne
	@JoinColumn(name="buyer_id")
	@Cascade(value={CascadeType.MERGE})
	private Market_user buyer;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	@Cascade(value={CascadeType.MERGE})
	private Product product;
	
	@OneToMany(mappedBy="p_Order")
	@Cascade(value={CascadeType.REMOVE})
	private Set<Market_record>records = new HashSet<Market_record>();
	
	private int product_num;
	private String user_order_encoding;
	int status;//1：待付款，2：未发货，3：未收货，4：已收货
	Date createtime;
	String phone;
	String address;
	
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public Market_user getBuyer() {
		return buyer;
	}
	@JsonBackReference
	public void setBuyer(Market_user buyer) {
		this.buyer = buyer;
	}
	
	public Product getProduct() {
		return product;
	}
	@JsonBackReference
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getUser_order_encoding() {
		return user_order_encoding;
	}
	public void setUser_order_encoding(String user_order_encoding) {
		this.user_order_encoding = user_order_encoding;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Set<Market_record> getRecords() {
		return records;
	}
	public void setRecords(Set<Market_record> records) {
		this.records = records;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
