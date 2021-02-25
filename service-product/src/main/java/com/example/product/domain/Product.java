package com.example.product.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product", schema = "service_product")
public class Product extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "brand", nullable = false)
  private String brand;
  @Column(name = "color", nullable = false)
  private String color;
  @Column(name = "unit_price", nullable = false)
  private BigDecimal unitPrice;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String branch) {
    this.brand = branch;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }
}