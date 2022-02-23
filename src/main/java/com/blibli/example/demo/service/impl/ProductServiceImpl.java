package com.blibli.example.demo.service.impl;

import com.blibli.example.demo.entity.Product;
import com.blibli.example.demo.entity.Shop;
import com.blibli.example.demo.repository.ProductRepository;
import com.blibli.example.demo.repository.ShopRepository;
import com.blibli.example.demo.service.ProductService;
import com.blibli.example.demo.web.model.product.CreateProductRequest;
import com.blibli.example.demo.web.model.product.UpdateProductRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ShopRepository shopRepository;

  @Override
  public Product create(CreateProductRequest request) {
    Shop shop = shopRepository.getById(request.getShopId());
    Product product = Product.builder()
        .shop(shop)
        .build();
    BeanUtils.copyProperties(request, product);
    return productRepository.save(product);
  }

  @Override
  public Product findById(String id) {
    return productRepository.getById(id);
  }

  @Override
  public Product update(String id, UpdateProductRequest request) {
    Product product = productRepository.getById(id);
    BeanUtils.copyProperties(request, product);
    if (!request.getShopId().equals(product.getShop().getId())) {
      Shop shop = shopRepository.getById(request.getShopId());
      product.setShop(shop);
    }
    return productRepository.save(product);
  }

  @Override
  public void deleteById(String id) {
    productRepository.deleteById(id);
  }
}
