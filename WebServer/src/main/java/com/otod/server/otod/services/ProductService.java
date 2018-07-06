package com.otod.server.otod.services;

import com.otod.server.otod.model.*;
import com.otod.server.otod.pojos.ProductPojo;
import com.otod.server.otod.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;


@Service("productService")
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductImgRepository imgRepository;
	
	@Autowired
	private CatalogRepository catalogRepository;
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private MURepository muRepository;
	
	
	public Product FindById(int id)
	{
		return repository.findById(Integer.valueOf(id)).get();
	}
	
	public static Date addDate(Date date,long day){
		 long time = date.getTime(); // 得到指定日期的毫秒数
		 day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		 time+=day; // 相加得到新的毫秒数
		 return new Date(time); // 将毫秒数转换成日期
	}
	
	@Transactional
	public List<Product> ManageList(List<Product> products){
		Date now = new Date();
		
		
		//有效期检测
		for(int i=0;i < products.size();i++)
		{
			if (addDate(products.get(i).getProduct_createtime(), products.get(i).getProduct_life()).after(now)) {
				continue;
			} else {
				repository.updateStatus(2, products.get(i).getProduct_id());
				products.remove(i);
			}
		}
		//展示时间检测
		int hour = Integer.parseInt(new SimpleDateFormat("HH").format(now));
		for (int i=0; i < products.size(); i++)
		{
			if (hour >= products.get(i).getProduct_day_from() 
				&& hour <= products.get(i).getProduct_day_to()){
				if (products.get(i).getProduct_status() == 4){
					repository.updateStatus(1, products.get(i).getProduct_id());
				}
				continue;
			} else {
				repository.updateStatus(4, products.get(i).getProduct_id());
				products.remove(i);
			}
		}
		//状态检测
		for (int i=0;i < products.size();i++)
		{
			if (products.get(i).getProduct_status() == 1){
				continue;
			} else {
				products.remove(i);
			}
		}
		
		return products;
	}
	
	public String EncodingGenerater(){
		Random random  = new Random();
		int c2 = random.nextInt(8999)+1000;
		Date day = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String c = df.format(day)+c2;
		return c;
	}
	
	@Transactional
	public void SaveByPojo(ProductPojo pojo,UserInfo userInfo)
	{
		Catalog catalog = catalogRepository.findById(Integer.parseInt(pojo.getProduct_catalog())).get();
		String product_encoding = EncodingGenerater();
		String product_name = pojo.getProduct_name();
		double product_price = pojo.getProduct_price();
		int product_stock = pojo.getProduct_stock();
		String product_description = pojo.getProduct_description();
		Date createtime = new Date();
		
		int product_life = pojo.getProduct_life();
		int product_day_from = pojo.getProduct_day_from();
		int product_day_to = pojo.getProduct_day_to();
		int status = 1;
		String product_img = null;
		if(pojo.getProduct_img().length == 0)
		{
			product_img = "http://localhost:8081/market/default.png";
		}
		else {
			product_img = "http://localhost:8081/" + pojo.getProduct_img()[0];
		}

		Product product = new Product();


		Market_user seller = muRepository.findByUserInfo(userInfo);

//		Market_user seller = userInfo.getmUser();
		

		product.setProduct_catalog(catalog);
		product.setSeller(seller);
		product.setProduct_encoding(product_encoding);
		product.setProduct_name(product_name);
		product.setProduct_price(product_price);
		product.setProduct_stock(product_stock);
		product.setProduct_description(product_description);
		product.setProduct_createtime(createtime);
		product.setProduct_life(product_life);
		product.setProduct_day_from(product_day_from);
		product.setProduct_day_to(product_day_to);
		product.setProduct_status(status);
		product.setProduct_img_url(product_img);
		repository.save(product);
		
		if(pojo.getProduct_img() != null)
		{
			for(int i=0;i<pojo.getProduct_img().length;i++)
			{
				Product_img pImg = new Product_img();
				pImg.setProduct(product);
				pImg.setImg_url("http://localhost:8081/" + pojo.getProduct_img()[i]);
				imgRepository.save(pImg);
			}
		}
		
		Market_record record = new Market_record();
		record.setCreatetime(createtime);
		record.setMarket_user(seller);
		record.setOperation(1);
		record.setProduct(product);
		recordRepository.save(record);
	}
	
	//更新商品信息 方法是创建一个product_id相同的实体 根据id去修改
	@Transactional
	public void UpdateByMap(Map<String, Object>map , Market_user mUser){
		int product_id = Integer.parseInt((String)map.get("product_id"));
		String product_name = (String)map.get("product_name");
		double product_price = Double.parseDouble((String)map.get("product_price"));
		int product_stock = Integer.parseInt((String)map.get("product_stock"));
		String product_description = (String)map.get("product_description");
		int product_life = Integer.parseInt((String)map.get("product_life"));
		int product_day_from = Integer.parseInt((String)map.get("product_day_from"));
		int product_day_to = Integer.parseInt((String)map.get("product_day_to"));
		int product_status = Integer.parseInt((String)map.get("product_status"));
		Date now = new Date();
		
		Product product = repository.findById(product_id).get();
		product.setProduct_name(product_name);
		product.setProduct_price(product_price);
		product.setProduct_stock(product_stock);
		product.setProduct_description(product_description);
		product.setProduct_life(product_life);
		product.setProduct_day_from(product_day_from);
		product.setProduct_day_to(product_day_to);
		product.setProduct_status(product_status);
		product.setProduct_createtime(now);
		repository.save(product);
		
		Market_record record = new Market_record();
		record.setCreatetime(now);
		record.setMarket_user(mUser);
		record.setOperation(3);
		record.setProduct(product);
		recordRepository.save(record);
	}

	@Transactional
	public void UpdateStatus(Product product,int status){
		int id = product.getProduct_id();
		repository.updateStatus(status, id);
		Date createtime = new Date();
		Market_record record = new Market_record();
		record.setCreatetime(createtime);
		record.setMarket_user(product.getSeller());
		record.setOperation(3);
		record.setProduct(product);
		recordRepository.save(record);
	}
	
	@Transactional
	public String DeleteProduct(int product_id) {
		Product product = repository.findById(product_id).get();
		Set<P_Order>orders = product.getOrders();
		Iterator<P_Order> iterator = orders.iterator();
		while(iterator.hasNext()){
			P_Order order = iterator.next();
			if (order.getStatus() != 3 && order.getStatus() != 5 && order.getStatus() != 8) {
				return "failure";
			}
		}
		repository.deleteById(product_id);
		return "success";
	}
	
}
