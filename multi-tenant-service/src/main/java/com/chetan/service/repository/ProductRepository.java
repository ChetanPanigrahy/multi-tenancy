package com.chetan.service.repository;

import com.chetan.service.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}