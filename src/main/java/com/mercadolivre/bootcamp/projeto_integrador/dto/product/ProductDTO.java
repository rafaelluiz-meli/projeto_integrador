package com.mercadolivre.bootcamp.projeto_integrador.dto.product;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private BigDecimal volume;
    private Float minimumTemperature;
    private Float maxTemperature;
    private Category category;
    private Long salesman_id;

    public static ProductDTO convert(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .volume(product.getVolume())
                .minimumTemperature(product.getMinimumTemperature())
                .maxTemperature(product.getMaxTemperature())
                .category(product.getCategory())
                .salesman_id(product.getSalesman().getId())
                .build();
    }

    public static List<ProductDTO> convert(List<Product> productList) {
        return productList.stream().map(ProductDTO::convert).collect(Collectors.toList());
    }
}
