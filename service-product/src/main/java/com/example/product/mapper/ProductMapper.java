package com.example.product.mapper;

import com.example.product.domain.Product;
import com.example.product.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.nio.CharBuffer;

@Mapper(componentModel = "spring",
    imports = {CharBuffer.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

  ProductDto productToProductDto(Product product);

  @Mapping(target = "id", ignore = true)
  Product productDtoToProduct(ProductDto productDto);
}
