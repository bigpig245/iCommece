package com.example.product.mapper;

import com.example.product.domain.Cart;
import com.example.product.dto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {

  @Mapping(target = "productName", source = "product.name")
  @Mapping(target = "productBrand", source = "product.brand")
  @Mapping(target = "productColor", source = "product.color")
  @Mapping(target = "unitPrice", source = "product.unitPrice")
  CartDto cartToCartDto(Cart cart);

}
