package com.blibli.example.demo.web.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterProductRequest {

  String productName;
  String shopIdOrName;

  @Builder.Default
  Integer page = 0;

  @Builder.Default
  Integer size = 10;
}
