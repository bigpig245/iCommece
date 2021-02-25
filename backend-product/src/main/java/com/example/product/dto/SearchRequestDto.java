package com.example.product.dto;

import java.math.BigDecimal;

public class SearchRequestDto {
  private String name;
  private String brand;
  private String color;
  private BigDecimal unitPriceFrom;
  private BigDecimal unitPriceTo;
  private String sortBy;
  private String sortOrder;
  private Integer page;
  private Integer limit;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public BigDecimal getUnitPriceFrom() {
    return unitPriceFrom;
  }

  public void setUnitPriceFrom(BigDecimal unitPriceFrom) {
    this.unitPriceFrom = unitPriceFrom;
  }

  public BigDecimal getUnitPriceTo() {
    return unitPriceTo;
  }

  public void setUnitPriceTo(BigDecimal unitPriceTo) {
    this.unitPriceTo = unitPriceTo;
  }

  public String getSortBy() {
    return sortBy;
  }

  public void setSortBy(String sortBy) {
    this.sortBy = sortBy;
  }

  public String getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }
}
