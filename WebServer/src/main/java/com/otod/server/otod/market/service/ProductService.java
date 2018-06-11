package com.otod.server.otod.market.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otod.server.otod.market.domain.Catalog;
import com.otod.server.otod.market.domain.Market_record;
import com.otod.server.otod.market.domain.Market_user;
import com.otod.server.otod.market.domain.Product;
import com.otod.server.otod.market.domain.Product_img;
import com.otod.server.otod.market.pojo.ProductPojo;
import com.otod.server.otod.market.repository.CatalogRepository;
import com.otod.server.otod.market.repository.MURepository;
import com.otod.server.otod.market.repository.ProductImgRepository;
import com.otod.server.otod.market.repository.ProductRepository;
import com.otod.server.otod.market.repository.RecordRepository;


@Service("productService")
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductImgRepository imgRepository;
	
	@Autowired
	private CatalogRepository catalogRepository;
	
	@Autowired
	private MURepository muRepository;
	
	@Autowired
	private RecordRepository recordRepository;
	
	public Product FindById(int id)
	{
		return repository.findById(Integer.valueOf(id)).get();
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
	public void SaveByPojo(ProductPojo pojo)
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
		if(pojo.getProduct_img() == null)
		{
			product_img = null;
		}
		else {
			product_img = pojo.getProduct_img()[0];
		}

		Product product = new Product();
		
		Market_user seller = new Market_user();
		seller.setMarket_user_id(1);
		
		
		
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
				pImg.setImg_url(pojo.getProduct_img()[i]);
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
	public void Update(Product product){
		String product_name = product.getProduct_name();
		double product_price = product.getProduct_price();
		int product_stock = product.getProduct_stock();
		String product_description = product.getProduct_description();
		Date product_createtime = product.getProduct_createtime();
		int product_life = product.getProduct_life();
		int product_day_from = product.getProduct_day_from();
		int product_day_to = product.getProduct_day_to();
		int product_status = product.getProduct_status();
		String product_img_url = product.getProduct_img_url();
		int product_id = product.getProduct_id();
		repository.updateAllById(product_name, product_price, product_stock,
				product_description, product_createtime, product_life, 
				product_day_from, product_day_to, product_status, 
				product_img_url, product_id);
		Market_record record = new Market_record();
		record.setCreatetime(product_createtime);
		record.setMarket_user(product.getSeller());
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
		record.setOperation(status);
		record.setProduct(product);
		recordRepository.save(record);
	}
}
