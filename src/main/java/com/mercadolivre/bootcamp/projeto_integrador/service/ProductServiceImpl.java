package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.product.NewProductDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.exception.product.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is the service implementation of Product entity.
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Create a new Product.
     * @param newProductDto
     * @return the created Product.
     */
    @Override
    public Product create(NewProductDTO newProductDto) {

        Salesman salesmanId = Salesman.builder().id(newProductDto.getSalesman_id()).build();

        Product product = Product.builder()
                                .productName(newProductDto.getProductName())
                                .volume(newProductDto.getVolume())
                                .minimumTemperature(newProductDto.getMinimumTemperature())
                                .maxTemperature(newProductDto.getMaxTemperature())
                                .category(newProductDto.getCategory())
                                .salesman(salesmanId)
                                .build();

        return productRepository.save(product);
    }

    /**
     * Check if Product id exists with productRepository.findById method. <br>
     * If exists then update InBoundOrder attributes.
     * @param receivedProduct
     * @return a updated Product.
     * @exception InvalidProductException if id is not valid.
     */
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


    /**
     * Check if Product id exists with {@link #findByProductId(Long) findById} method. <br>
     * If exists then remove the Product.
     * @param id
     * @return void.
     *
     */
    @Override
    public void delete(Long id) {
        Product product = findByProductId(id);
        productRepository.delete(product);
    }

    /**
     * Find a Product by Id.
     * @param id
     * @return a Product.
     * @exception IdNotFoundException if id doesn't found.
     */
    @Override
    public Product findByProductId(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    /**
     * Find a Product by name.
     * @param productName
     * @return a Product.
     * @exception InvalidProductException if product name doesn't found.
     */
    @Override
    public Product findByProductName(String productName) {
        return productRepository.findByProductName(productName).orElseThrow(() -> new InvalidProductException(productName));
    }

    /**
     * Get a ProductList by name.
     * @param productName
     * @return a Product List.
     * @exception InvalidProductException if list is empty.
     */
    @Override
    public List<Product> findAllByProductName(String productName) {
        List<Product> productList = productRepository.findAllByProductName(productName);
        if (productList.isEmpty()) throw new InvalidProductException(productName);

        return productList;
    }

    /**
     * Get a ProductList by Category.
     * @param category
     * @return a Product List.
     * @exception InvalidProductException if list is empty.
     */
    @Override
    public List<Product> findAllByCategory(Category category) {
        List<Product> productList = productRepository.findAllByCategory(category);
        if (productList.isEmpty()) throw new InvalidProductException(category.toString());
        return productList;
    }

    /**
     * FGet a ProductList by SlesmanId.
     * @param salesmanId
     * @return a Product List.
     * @exception InvalidProductException if list is empty.
     */
    @Override
    public List<Product> findAllBySalesmanId(Long salesmanId) {
        List<Product> productList = productRepository.findAllBySalesman_Id(salesmanId);
        if (productList.isEmpty()) throw new IdNotFoundException(salesmanId);
        return productList;
    }

    /**
     * Get all products from database.
     * @return a Product List.
     * @exception EmptyListException if list is empty.
     */
    @Override
    public List<Product> findAll() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()) throw new EmptyListException();
        return productList;
    }

    /**
     * Check if product exists by productId.
     * @param productID
     * @return a boolean.
     */
    @Override
    public Boolean isProductValid(Long productID) {
        Optional<Product> isIdValid = productRepository.findById(productID);
        return isIdValid.isPresent();
    }
}
