package com.otod.server.otod.respository;

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

import com.otod.server.otod.model.Market_user;
import com.otod.server.otod.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>,JpaSpecificationExecutor<Product>{
	
	@Query("select p from Product p where p.product_name like %:name%")
	Page<Product> findByName(@Param("name") String name,Pageable pageable);
	
	@Query("select p from Product p where p.product_name like %:name% order by p.product_createtime DESC")
	List<Product> FindByName(@Param("name") String name);
	
	//更改库存
	@Modifying   
	@Query("update Product p set p.product_stock = :value where p.product_id = :id") //value:更改后的值 ,id:更改的product的id
	int updateById(@Param("value") int value,@Param("id") int id);
	
	
	@Modifying
	@Query("update Product p set p.product_status = :status where p.product_id = :id")
	int updateStatus(@Param("status") int status,@Param("id") int id);
	
	@Query("select p from Product p where p.seller.market_user_id = :seller_id")
	Page<Product> findBySeller(@Param("seller_id")int seller_id,Pageable pageable);
	
}
