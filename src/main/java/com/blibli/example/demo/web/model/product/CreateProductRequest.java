package com.blibli.example.demo.web.model.product;

import com.blibli.example.demo.validation.ShopExists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

  @NotBlank
  @Length(min = 4, max = 255)
  String name;

  @Min(value = 10000)
  BigInteger price;

  @Min(value = 0)
  Integer stock;

  @NotNull
  @ShopExists
  String shopId;
}
