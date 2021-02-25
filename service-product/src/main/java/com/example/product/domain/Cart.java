package com.example.product.domain;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "cart", schema = "service_product")
public class Cart extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "customer_id", nullable = false)
  private Long customerId;
  @Column(name = "quantity", nullable = false)
  private Long quantity;
  @Column(name = "product_id", updatable = false)
  private Long productId;
  @ManyToOne
  @JoinColumn(name = "product_id", insertable = false, updatable = false)
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
    if (quantity > 0) {
      return quantity;
    }
    return 0L;
  }

  public void setQuantity(Long quantity) {
    this.quantity = Optional.ofNullable(quantity).orElse(0L);
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}