package com.otod.server.otod.pojos;

import java.util.Date;

public class PayPojo {

	int order_id;
	int product_id;
	String product_name;
	String product_encoding;
	double product_price;
	int product_num;
	String order_encoding;
	String product_img_url;
	Date createtime;
	int orderstatus;
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_encoding() {
		return product_encoding;
	}
	public void setProduct_encoding(String product_encoding) {
		this.product_encoding = product_encoding;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getOrder_encoding() {
		return order_encoding;
	}
	public void setOrder_encoding(String order_encoding) {
		this.order_encoding = order_encoding;
	}
	public String getProduct_img_url() {
		return product_img_url;
	}
	public void setProduct_img_url(String product_img_url) {
		this.product_img_url = product_img_url;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
