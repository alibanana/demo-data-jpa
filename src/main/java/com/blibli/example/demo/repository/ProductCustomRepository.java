package com.blibli.example.demo.repository;

import com.blibli.example.demo.entity.Product;
import com.blibli.example.demo.web.model.product.FilterProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

public interface ProductCustomRepository {

  Page<Product> findByFilter(FilterProductRequest request);
}
