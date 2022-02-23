package com.blibli.example.demo.repository.impl;

import com.blibli.example.demo.entity.Product;
import com.blibli.example.demo.repository.ProductCustomRepository;
import com.blibli.example.demo.web.model.product.FilterProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCustomRepositoryImpl implements ProductCustomRepository {

  private final EntityManager entityManager;

  public ProductCustomRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * SELECT p.*
   * FROM products p
   * JOIN shops s on s.id = p.shop_id
   * WHERE
   *  LOWER(p.product_name) LIKE '%{productName}%' // if productName given
   *  AND
   *  (
   *    s.id like '{shopIdOrName}' // if shopIdOrName given
   *    OR
   *    LOWER(s.shop_name) LIKE '%{shopIdOrName}%' // if shopIdOrName given
   *  )
   */
  @Override
  public Page<Product> findByFilter(FilterProductRequest request) {
    String contentQueryString = "SELECT p FROM Product p JOIN Shop s on s.id = p.shop";
    String countQueryString = "SELECT COUNT(1) FROM Product p JOIN Shop s on s.id = p.shop";

    List<String> criteriaList = new ArrayList<>();
    Map<String, Object> parameters = new HashMap<>();

    if (StringUtils.hasText(request.getProductName())) {
      criteriaList.add("LOWER(p.name) LIKE :productName");
      parameters.put("productName", String.format("%%%s%%", request.getProductName().toLowerCase()));
    }

    if (StringUtils.hasText(request.getShopIdOrName())) {
      criteriaList.add("(LOWER(s.id) LIKE :shopId OR LOWER(s.name) LIKE :shopName)");
      parameters.put("shopId", request.getShopIdOrName().toLowerCase());
      parameters.put("shopName", String.format("%%%s%%", request.getShopIdOrName().toLowerCase()));
    }

    if (!criteriaList.isEmpty()) {
      String whereClause = " WHERE " + String.join(" AND ", criteriaList);
      contentQueryString += whereClause;
      countQueryString += whereClause;
    }

    Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
    TypedQuery<Product> contentQuery = entityManager.createQuery(contentQueryString, Product.class)
        .setFirstResult((int) pageable.getOffset())
        .setMaxResults(pageable.getPageSize());
    TypedQuery<Long> countQuery = entityManager.createQuery(countQueryString, Long.class);

    parameters.forEach((key, value) -> {
      contentQuery.setParameter(key, value);
      countQuery.setParameter(key, value);
    });

    long total = countQuery.getSingleResult();
    List<Product> products = contentQuery.getResultList();
    return new PageImpl<>(products, pageable, total);
  }
}
