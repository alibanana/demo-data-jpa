package com.blibli.example.demo.validation.validator;

import com.blibli.example.demo.repository.ProductRepository;
import com.blibli.example.demo.validation.ProductExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductExistsByIdValidator implements ConstraintValidator<ProductExists, String> {

  @Autowired
  ProductRepository productRepository;

  @Override
  public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
    return productRepository.existsById(id);
  }
}
