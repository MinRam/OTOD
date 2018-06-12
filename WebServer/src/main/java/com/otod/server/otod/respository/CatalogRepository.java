package com.otod.server.otod.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otod.server.otod.model.Catalog;

public interface CatalogRepository extends JpaRepository<Catalog, Integer>{

}
