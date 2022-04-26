package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewProductDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;

import java.util.List;

public interface ProductService {

    Boolean availableStockQuantity(Integer orderProductQuantity);
    Boolean validateProductDueDate(String productId);

    Product create(NewProductDto newProductDto);
    Product update (Product receivedProduct);
    void delete(String id);
    Product findByProductId(String id);
    Product findByProductName(String productName);
    List<Product> findAllByProductName(String productName);
    List<Product> findAllByCategory(Category category);
    List<Product> findAll(String salesmanId);
    List<Product> findAll();
}
