package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewProductDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.exception.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Boolean availableStockQuantity(Integer orderProductQuantity) {
        // TODO: 25/04/22 Verify BatchStock by product id and return quantity available;
        return null;
    }

    @Override
    public Boolean validateProductDueDate(String productId) {
        // TODO: 26/04/22 validate product due date after batchstock endpoints are complete 
        return null;
    }

    // METHOD TO CREATE PRODUCT
    @Override
    public Product create(NewProductDto newProductDto) {

        Product product = Product.builder()
                                .productName(newProductDto.getProductName())
                                .volume(newProductDto.getVolume())
                                .minimumTemperature(newProductDto.getMinimumTemperature())
                                .maxTemperature(newProductDto.getMaxTemperature())
                                .category(newProductDto.getCategory())
                                // TODO: 25/04/22  ADD SALESMAN TO THIS METHOD
                                //  .salesman()
                                .build();

        return productRepository.save(product);
    }

    // Method to update product

    @Override
    public Product update(Product receivedProduct) {

        Product currentProduct = productRepository.findById(receivedProduct.getId()).orElseThrow(() -> new InvalidProductException(receivedProduct.getId()));

        currentProduct.setProductName(receivedProduct.getProductName());
        currentProduct.setCategory(receivedProduct.getCategory());
        currentProduct.setVolume(receivedProduct.getVolume());
        currentProduct.setMinimumTemperature(receivedProduct.getMinimumTemperature());
        currentProduct.setMaxTemperature(receivedProduct.getMaxTemperature());

        return productRepository.save(currentProduct);
    }


    // METHOD TO DELETE PRODUCT
    @Override
    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new InvalidProductException(id));
        productRepository.delete(product);
    }

    // METHODS TO FIND PRODUCT OR LIST OF PRODUCTS
    @Override
    public Product find(String productName) {
        return productRepository.findByProductName(productName).orElse(null);
    }

    @Override
    public List<Product> find(Category category) {
        return productRepository.findAllByCategory(category);
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
