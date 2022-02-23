package com.blibli.example.demo.repository;

import com.blibli.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

  Product findByName(String name);
}
