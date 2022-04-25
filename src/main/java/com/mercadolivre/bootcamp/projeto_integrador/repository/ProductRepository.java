package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByProductName(String productName);
    Optional<Product> findByCategory(Category category);
    List<Product> findAllBySalesman_Id(String salesmanId);
}
