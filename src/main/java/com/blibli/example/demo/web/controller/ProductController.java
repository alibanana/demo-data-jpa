package com.blibli.example.demo.web.controller;


import com.blibli.example.demo.entity.Product;
import com.blibli.example.demo.entity.Shop;
import com.blibli.example.demo.service.ProductService;
import com.blibli.example.demo.web.model.Response;
import com.blibli.example.demo.web.model.product.CreateProductRequest;
import com.blibli.example.demo.web.model.product.ProductResponse;
import com.blibli.example.demo.web.model.product.UpdateProductRequest;
import com.blibli.example.demo.web.model.shop.ShopResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api
@RestController
public class ProductController {

  @Autowired
  ProductService productService;

  @ApiOperation("Create a new product.")
  @PostMapping(
      path = "/api/products",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<ProductResponse> create(@Valid @RequestBody CreateProductRequest request) {
    Product product = productService.create(request);
    return Response.<ProductResponse>builder()
        .status(HttpStatus.OK.value())
        .data(toResponse(product))
        .build();
  }

  @ApiOperation("Find product by id.")
  @GetMapping(
      path = "/api/products/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<ProductResponse> findById(@PathVariable String id) {
    Product product = productService.findById(id);
    return Response.<ProductResponse>builder()
        .status(HttpStatus.OK.value())
        .data(toResponse(product))
        .build();
  }

  @ApiOperation("Update product.")
  @PutMapping(
      path = "/api/products/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<ProductResponse> findById(@PathVariable String id,
      @Valid @RequestBody UpdateProductRequest request) {
    Product product = productService.update(id, request);
    return Response.<ProductResponse>builder()
        .status(HttpStatus.OK.value())
        .data(toResponse(product))
        .build();
  }

  @ApiOperation("Delete product by id.")
  @DeleteMapping(
      path = "/api/products/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<Boolean> deleteById(@PathVariable String id) {
    productService.deleteById(id);
    return Response.<Boolean>builder()
        .status(HttpStatus.OK.value())
        .data(true)
        .build();
  }

  private ProductResponse toResponse(Product product) {
    ProductResponse productResponse = ProductResponse.builder()
        .shop(toResponse(product.getShop()))
        .build();
    BeanUtils.copyProperties(product, productResponse);
    return productResponse;
  }

  private ShopResponse toResponse(Shop shop) {
    ShopResponse shopResponse = ShopResponse.builder().build();
    BeanUtils.copyProperties(shop, shopResponse);
    return shopResponse;
  }
}
