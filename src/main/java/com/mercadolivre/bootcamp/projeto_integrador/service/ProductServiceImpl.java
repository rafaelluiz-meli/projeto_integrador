package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product find(String productName) {
        return productRepository.findByProductName(productName).orElse(null);
    }

    @Override
    public Product find(Category category) {
        return productRepository.findByCategory(category).orElse(null);
    }

    @Override
    public List<Product> findAll(String salesmanId) {
        return productRepository.findAllBySalesman_Id(salesmanId);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
