package com.blibli.example.demo.validation.validator;

import com.blibli.example.demo.repository.ShopRepository;
import com.blibli.example.demo.validation.ShopExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ShopExistsByIdValidator implements ConstraintValidator<ShopExists, String> {

  @Autowired
  ShopRepository shopRepository;

  @Override
  public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
    return shopRepository.existsById(id);
  }
}
