package com.otod.server.otod.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otod.server.otod.market.domain.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer>{

}
