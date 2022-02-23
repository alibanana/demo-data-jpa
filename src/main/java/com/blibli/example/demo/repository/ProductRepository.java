package com.blibli.example.demo.repository;

import com.blibli.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ProductRepository extends JpaRepository<Product, String>, ProductCustomRepository {

  Product findByName(String name);
  Product findByIdAndNameAndPrice(String id, String name, BigInteger price);
}
