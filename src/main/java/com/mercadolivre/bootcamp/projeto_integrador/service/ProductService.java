package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.product.NewProductDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;

import java.util.List;

public interface ProductService {
    Boolean availableStockQuantity(Integer orderProductQuantity);
    Boolean validateProductDueDate(Long productId);
    Product create(NewProductDTO newProductDto);
    Product update (Product receivedProduct);
    void delete(Long id);
    Product findByProductId(Long id);
    Product findByProductName(String productName);
    List<Product> findAllByProductName(String productName);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllBySalesmanId(Long salesmanId);
    List<Product> findAll();
    Boolean isProductValid(Long productID);
}
