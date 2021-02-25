package com.example.product.domain;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_detail", schema = "service_product")
public class OrderDetail extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "customer_id", nullable = false)
  private Long customerId;
  @Column(name = "quantity", nullable = false)
  private Long quantity;
  @Column(name = "unit_price", nullable = false)
  private BigDecimal unitPrice;
  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  @PodamExclude
  private Order order;
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  @PodamExclude
  private Product product;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}