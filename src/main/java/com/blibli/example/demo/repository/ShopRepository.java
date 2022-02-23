package com.blibli.example.demo.repository;

import com.blibli.example.demo.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, String> {
}
