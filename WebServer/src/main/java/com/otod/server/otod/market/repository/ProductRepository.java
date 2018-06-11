package com.otod.server.otod.market.repository;

import java.util.Date;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.otod.server.otod.market.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product>{

	@Query("select p from Product p where p.product_name like %?1%")
	List<Product> Findbykey(String key);
	
	@Query("select p from Product p where p.product_name like %:name%")
	Page<Product> findByName(@Param("name") String name,Pageable pageable);
	
	//更改库存
	@Modifying   
	@Query("update Product p set p.product_stock = :value where p.product_id = :id") //value:更改后的值 ,id:更改的product的id
	int updateById(@Param("value") int value,@Param("id") int id);
	
	//更改商品信息(product_name,product_price,product_stock,product_description,product_createtime,product_life
	//product_day_from,product_day_to,product_status,product_img_url)
	@Modifying
	@Query("update Product p set p.product_name = :product_name , p.product_price = :product_price , "
			+ "p.product_stock = :product_stock , p.product_description = :product_description , "
			+ "p.product_createtime = :product_createtime , p.product_life = :product_life , "
			+ "p.product_day_from = :product_day_from , p.product_day_to = :product_day_to , "
			+ "p.product_status = :product_status , p.product_img_url = :product_img_url where p.product_id = :product_id")
	int updateAllById(@Param("product_name")String product_name,@Param("product_price")double product_price,
			@Param("product_stock")int product_stock,@Param("product_description")String product_description,
			@Param("product_createtime")Date product_createtime,@Param("product_life")int product_life,
			@Param("product_day_from")int product_day_from,@Param("product_day_to")int product_day_to,
			@Param("product_status")int product_status,@Param("product_img_url")String product_img_url,
			@Param("product_id")int product_id);
	
	@Modifying
	@Query("update Product p set p.product_status = :status where p.product_id = :id")
	int updateStatus(@Param("status") int status,@Param("id") int id);
}
