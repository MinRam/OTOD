package com.otod.server.otod.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.otod.server.otod.model.Product_img;

@Repository
public interface ProductImgRepository extends JpaRepository<Product_img, Integer>{

}
