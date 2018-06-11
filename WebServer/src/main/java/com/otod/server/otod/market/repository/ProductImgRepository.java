package com.otod.server.otod.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.otod.server.otod.market.domain.Product_img;

@Repository
public interface ProductImgRepository extends JpaRepository<Product_img, Integer>{

}
