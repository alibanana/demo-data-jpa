package com.blibli.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shops",
    indexes = {
        @Index(name = "shops_name_index",
            columnList = "shop_name")
    })
public class Shop {

  @Id
  @Column(name = "shop_id")
  String id;

  @Column(name = "shop_name")
  String name;

  @Version
  @Column
  Long version;
}
