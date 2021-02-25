package com.example.product.service;

import com.example.product.domain.Product;
import com.example.product.domain.QProduct;
import com.example.product.dto.PageDataDto;
import com.example.product.dto.PaginationDto;
import com.example.product.dto.ProductDto;
import com.example.product.dto.SearchRequestDto;
import com.example.product.dto.enumeration.SPMessage;
import com.example.product.exception.CustomException;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import utils.ExpressionBuilderUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Constants.DEFAULT_PAGE;
import static utils.Constants.MAX_PAGE;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private ProductMapper productMapper;

  @Transactional
  public ProductDto saveProduct(ProductDto productDto) {
    Product product = productMapper.productDtoToProduct(productDto);
    productRepository.save(product);
    return productMapper.productToProductDto(product);
  }

  public ProductDto findProduct(Long id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new CustomException(SPMessage.RESOURCE_NOT_FOUND));
    return productMapper.productToProductDto(product);
  }

  @Transactional
  public ProductDto updateProductPrice(Long id, BigDecimal newPrice) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new CustomException(SPMessage.RESOURCE_NOT_FOUND));
    product.setUnitPrice(newPrice);
    productRepository.save(product);
    return productMapper.productToProductDto(product);
  }

  public PageDataDto<ProductDto> search(SearchRequestDto searchDto) {
    Sort.Direction direction =
        Sort.Direction.fromOptionalString(searchDto.getSortBy()).orElse(Sort.Direction.DESC);
    PageRequest pageable = PageRequest.of(
        searchDto.getPage() < 0 ? DEFAULT_PAGE : searchDto.getPage() - 1,
        searchDto.getLimit() > MAX_PAGE ? MAX_PAGE : searchDto.getLimit(),
        Sort.by(direction, searchDto.getSortBy(), "id"));
    Predicate predicate = buildPredicate(searchDto);
    Page<Product> eventRequestPage = productRepository.findAll(predicate, pageable);

    List<ProductDto> eventRequestDtos = eventRequestPage.getContent().stream()
        .map(item -> productMapper.productToProductDto(item))
        .collect(Collectors.toList());
    PaginationDto paginationDto = new PaginationDto(eventRequestPage.getTotalPages(),
        eventRequestPage.getTotalElements(), eventRequestPage.getNumberOfElements());
    return new PageDataDto<>(eventRequestDtos, paginationDto);
  }

  private Predicate buildPredicate(SearchRequestDto searchDto) {
    QProduct qProduct = QProduct.product;
    BooleanExpression nameExpression = ExpressionBuilderUtils.likeExpression(
        qProduct.name, searchDto.getName());
    BooleanExpression brandExpression = ExpressionBuilderUtils.eqExpression(
        qProduct.brand, searchDto.getBrand());
    BooleanExpression colorExpression = ExpressionBuilderUtils.eqExpression(
        qProduct.color, searchDto.getColor());
    BooleanExpression unitPriceExpression = ExpressionBuilderUtils.betweenExpression(
        qProduct.unitPrice, searchDto.getUnitPriceFrom(), searchDto.getUnitPriceTo());
    return ExpressionUtils.allOf(nameExpression,
        brandExpression,
        colorExpression,
        unitPriceExpression);
  }
}
