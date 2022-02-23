package com.blibli.example.demo.service;

import com.blibli.example.demo.entity.Product;
import com.blibli.example.demo.web.model.product.CreateProductRequest;
import com.blibli.example.demo.web.model.product.FilterProductRequest;
import com.blibli.example.demo.web.model.product.UpdateProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {

  Product create(CreateProductRequest request);

  Product findById(String id);

  Product update(String id, UpdateProductRequest request);

  void deleteById(String id);

  Page<Product> filter(FilterProductRequest request);
}
