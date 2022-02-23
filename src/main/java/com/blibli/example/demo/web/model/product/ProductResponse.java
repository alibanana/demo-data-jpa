package com.blibli.example.demo.web.model.product;

import com.blibli.example.demo.web.model.shop.ShopResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

  String id;

  String name;

  BigInteger price;

  Integer stock;

  ShopResponse shop;
}
