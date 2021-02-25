package com.example.product.service;

import com.example.product.domain.Product;
import com.example.product.dto.PageDataDto;
import com.example.product.dto.ProductDto;
import com.example.product.dto.SearchRequestDto;
import com.example.product.exception.CustomException;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import com.querydsl.core.types.Predicate;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
  private final PodamFactory PODAM = new PodamFactoryImpl();
  @Mock
  private ProductRepository productRepository;
  @Mock
  private ProductMapper productMapper;

  @InjectMocks
  private ProductService productService;

  @Test
  public void shouldSaveProduct() {
    ProductDto productDto = PODAM.manufacturePojo(ProductDto.class);
    Product product = PODAM.manufacturePojo(Product.class);
    given(productMapper.productDtoToProduct(productDto)).willReturn(product);
    given(productMapper.productToProductDto(product)).willReturn(productDto);

    Assertions.assertThat(productService.saveProduct(productDto))
        .isEqualTo(productDto);
    verify(productRepository).save(product);
  }

  @Test
  public void shouldNotFindProduct() {
    Long id = 1L;
    Assertions.assertThatThrownBy(() -> productService.findProduct(id))
        .isInstanceOf(CustomException.class)
        .hasMessage("Product not found");
  }

  @Test
  public void shouldFindProduct() {
    Long id = 1L;

    Product product = PODAM.manufacturePojo(Product.class);
    ProductDto productDto = PODAM.manufacturePojo(ProductDto.class);
    given(productRepository.findById(id))
        .willReturn(Optional.of(product));
    given(productMapper.productToProductDto(product)).willReturn(productDto);
    Assertions.assertThat(productService.findProduct(id))
        .isEqualTo(productDto);
  }
  @Test
  public void shouldNotUpdateProductPrice() {
    Long id = 1L;
    BigDecimal newPrice = new BigDecimal("11.2");
    Assertions.assertThatThrownBy(() -> productService.updateProductPrice(id, newPrice))
        .isInstanceOf(CustomException.class)
        .hasMessage("Product not found");
  }

  @Test
  public void shouldUpdateProductPrice() {
    Long id = 1L;
    BigDecimal newPrice = new BigDecimal("11.2");

    Product product = PODAM.manufacturePojo(Product.class);
    given(productRepository.findById(id))
        .willReturn(Optional.of(product));
    productService.updateProductPrice(id, newPrice);
    verify(productRepository).save(product);
    Assertions.assertThat(product)
        .extracting("unitPrice")
        .isEqualTo(newPrice);
  }

  @Test
  public void shouldSearchProduct() {
    SearchRequestDto searchDto = new SearchRequestDto();
    searchDto.setPage(1);
    searchDto.setLimit(10);
    searchDto.setSortBy("lastModifiedDate");
    searchDto.setName("name");
    searchDto.setBrand("brand");
    searchDto.setUnitPriceTo(new BigDecimal("100"));

    Product product1 = PODAM.manufacturePojo(Product.class);
    Product product2 = PODAM.manufacturePojo(Product.class);
    ProductDto p1 = PODAM.manufacturePojo(ProductDto.class);
    ProductDto p2 = PODAM.manufacturePojo(ProductDto.class);
    given(productMapper.productToProductDto(product1)).willReturn(p1);
    given(productMapper.productToProductDto(product2)).willReturn(p2);
    Page<Product> page = new PageImpl<>(Arrays.asList(product1, product2));
    given(productRepository.findAll(any(Predicate.class), any(PageRequest.class)))
        .willReturn(page);
    PageDataDto<ProductDto> pageDataDto = productService.search(searchDto);
    Assertions.assertThat(pageDataDto.getPage())
        .extracting("totalPages", "totalRecords", "limit")
        .containsExactly(1, 2L, 2);
    Assertions.assertThat(pageDataDto.getContents()).hasSize(2)
        .extracting("id", "name", "brand", "color", "unitPrice")
        .containsExactly(
            Tuple.tuple(p1.getId(), p1.getName(), p1.getBrand(), p1.getColor(), p1.getUnitPrice()),
            Tuple.tuple(p2.getId(), p2.getName(), p2.getBrand(), p2.getColor(), p2.getUnitPrice()));
    ArgumentCaptor<Predicate> predicateCaptor = ArgumentCaptor.forClass(Predicate.class);
    ArgumentCaptor<PageRequest> pageRequestCaptor = ArgumentCaptor.forClass(PageRequest.class);
    verify(productRepository).findAll(predicateCaptor.capture(), pageRequestCaptor.capture());
    Assertions.assertThat(predicateCaptor.getValue().toString())
        .isEqualTo("containsIc(product.name,name) && eqIc(product.brand,brand) && product.unitPrice < 100");
    Assertions.assertThat(pageRequestCaptor.getValue().getPageNumber()).isEqualTo(0);
    Assertions.assertThat(pageRequestCaptor.getValue().getPageSize()).isEqualTo(10);

  }
}
