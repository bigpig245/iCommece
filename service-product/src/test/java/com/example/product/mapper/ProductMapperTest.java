package com.example.product.mapper;

import com.example.product.domain.Product;
import com.example.product.dto.ProductDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class ProductMapperTest {
  private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
  private final PodamFactory PODAM = new PodamFactoryImpl();

  @Test
  public void shouldNotConvertProductToProductDto() {
    Assertions.assertThat(productMapper.productToProductDto(null)).isNull();
  }

  @Test
  public void shouldConvertProductToProductDto() {
    Product product = PODAM.manufacturePojo(Product.class);
    ProductDto productDto = productMapper.productToProductDto(product);
    Assertions.assertThat(productDto).isEqualToComparingOnlyGivenFields(product, "id");
  }

  @Test
  public void shouldNotConvertProductDtoToProduct() {
    Assertions.assertThat(productMapper.productDtoToProduct(null)).isNull();
  }

  @Test
  public void shouldConvertProductDtoToProduct() {
    ProductDto productDto = PODAM.manufacturePojo(ProductDto.class);
    Product product = productMapper.productDtoToProduct(productDto);
    Assertions.assertThat(productDto).isEqualToComparingOnlyGivenFields(product);
  }
}
