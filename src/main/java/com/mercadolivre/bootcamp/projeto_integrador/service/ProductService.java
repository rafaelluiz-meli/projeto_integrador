package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewProductDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(NewProductDto newProductDto);
    void delete(String id);

    Product find(String productName);
    List<Product> find(Category category);
    List<Product> findAll(String salesmanId);
    List<Product> findAll();
}
