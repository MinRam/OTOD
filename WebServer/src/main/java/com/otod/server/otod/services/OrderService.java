package com.otod.server.otod.services;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otod.server.otod.model.Market_record;
import com.otod.server.otod.model.Market_user;
import com.otod.server.otod.model.P_Order;
import com.otod.server.otod.model.Product;
import com.otod.server.otod.pojos.P_OrderPojo;
import com.otod.server.otod.pojos.PayPojo;
import com.otod.server.otod.respository.MURepository;
import com.otod.server.otod.respository.P_OrderRepository;
import com.otod.server.otod.respository.ProductRepository;
import com.otod.server.otod.respository.RecordRepository;
/*
 * 市场订单*/

@Service("orderService")
public class OrderService {
	@Autowired
	P_OrderRepository orderRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	MURepository muRepository;
	@Autowired
	RecordRepository recordRepository;
	
	public String EncodingGenerater(){
		Random random  = new Random();
		int c2 = random.nextInt(8999)+1000;
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String c = df.format(day)+c2;
		return c;
	}
	
	
	@Transactional
	public void SaveByPojo(P_OrderPojo pojo , Market_user mUser){
		P_Order order = new P_Order();
		Market_user buyer = mUser;
		Product product = productRepository.findById(pojo.getProduct_id()).get();
		Date createtime = new Date();
		String address = pojo.getAddress();
		String receiver = pojo.getReceiver();
		String phone = pojo.getPhone();
		int product_num = pojo.getProduct_num();
		boolean flag = false;
		if(product.getProduct_stock() == product_num)
		{
			flag = true;
		}
		order.setAddress(address);
		order.setBuyer(buyer);
		order.setCreatetime(createtime);
		order.setPhone(phone);
		order.setProduct(product);
		order.setProduct_num(product_num);
		order.setStatus(1);
		order.setUser_order_encoding(EncodingGenerater());
		orderRepository.save(order);
		productRepository.updateById(product.getProduct_stock() - product_num, pojo.getProduct_id());
		Market_record record = new Market_record();
		record.setCreatetime(createtime);
		record.setMarket_user(buyer);
		record.setP_Order(order);
		record.setOperation(4);
		recordRepository.save(record);
		if(flag){
			productRepository.updateStatus(2, pojo.getProduct_id());
		}
	}
	//卖家发货 买家收货
	@Transactional
	public void Deliver(P_Order order){
		int status = 2;
		if (order.getStatus() == 2) {
			status = 3;
		}
		Date now = new Date();
		orderRepository.UpdateStatus(status, order.getOrder_id());
		int operation = 5;
		Market_user user = null;
		if (status == 2){
			user = order.getProduct().getSeller();
		} else {
			user = order.getBuyer();
			operation = 6;
		}
		Market_record record = new Market_record();
		record.setCreatetime(now);
		record.setMarket_user(user);
		record.setOperation(operation);
		record.setP_Order(order);
		recordRepository.save(record);
	}
	
	//取消订单是买卖双方都要取消 ， 买家提出 状态变成（协商取消中）(4),卖家也取消 状态变成（订单取消）（5）
	@Transactional
	public void CancelOrder(P_Order order){
		int status = 4;
		if(order.getStatus() == 4){
			status = 5;
		}
		Date now = new Date();
		orderRepository.UpdateStatus(status, order.getOrder_id());
		int operation = 10;
		Market_user user = null;
		if (status == 4) {
			user = order.getBuyer();
		} else {
			user = order.getProduct().getSeller();
			operation = 11;
		}
		Market_record record = new Market_record();
		record.setCreatetime(now);
		record.setMarket_user(user);
		record.setOperation(operation);
		record.setP_Order(order);
		recordRepository.save(record);
	}
	
	//买家退货 卖家确认退货 货物库存不会+1 卖家自己+
	@Transactional
	public void Return(P_Order order){
		int status = 6;
		if(order.getStatus() == 6){
			status = 7;
		} else if(order.getStatus() == 7){
			status = 8;
		}
		Date now = new Date();
		orderRepository.UpdateStatus(status, order.getOrder_id());
		int operation = 7;
		Market_user user = null;
		if(status == 6){
			user = order.getBuyer();
		}else if(status == 7){
			user = order.getProduct().getSeller();
			operation = 8;
		}else if(status == 8){
			user = order.getProduct().getSeller();
			operation = 9;
		}
		Market_record record = new Market_record();
		record.setCreatetime(now);
		record.setMarket_user(user);
		record.setOperation(operation);
		record.setP_Order(order);
		recordRepository.save(record);
	}
	
	//买家取消申请取消订单
	@Transactional
	public void CancelCancelOrder(P_Order order){
		int status = 1;
		Date now = new Date();
		orderRepository.UpdateStatus(status, order.getOrder_id());
		int operation = 12;
		Market_user user = order.getBuyer();
		Market_record record = new Market_record();
		record.setCreatetime(now);
		record.setMarket_user(user);
		record.setOperation(operation);
		record.setP_Order(order);
		recordRepository.save(record);
	}
	
	//买家取消申请退货
	@Transactional
	public void CancelReturn(P_Order order){
		int status = 2;
		Date now = new Date();
		orderRepository.UpdateStatus(status, order.getOrder_id());
		int operation = 13;
		Market_user user = order.getBuyer();
		Market_record record = new Market_record();
		record.setCreatetime(now);
		record.setMarket_user(user);
		record.setOperation(operation);
		record.setP_Order(order);
		recordRepository.save(record);
	}
	
	@Transactional
	public String DeleteOrder(int order_id) {
		orderRepository.deleteById(order_id);
		return "success";
	}

}
