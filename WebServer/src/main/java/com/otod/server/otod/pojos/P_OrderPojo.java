package com.otod.server.otod.pojos;

import com.otod.server.otod.model.Market_user;

public class P_OrderPojo {

	private int product_id;
	private int product_num;
	private String address;
	private String receiver;
	private String phone;
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getProduct_num() {
		return product_num;
	}
	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "P_OrderPojo [product_id=" + product_id + ", product_num=" + product_num + ", address=" + address
				+ ", receiver=" + receiver + ", phone=" + phone + "]";
	}
	
	
}
