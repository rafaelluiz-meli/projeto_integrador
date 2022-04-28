package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewProductDto;
import com.mercadolivre.bootcamp.projeto_integrador.dto.ProductDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.factory.ProductFactory;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductService;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/fresh-products")
public class ProductController {
    private final ProductService productService;
    private final ProductFactory productFactory;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody NewProductDto productDto) {
        ProductDto createdProduct = ProductDto.convert(productFactory.createProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productList = ProductDto.convert(productService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@RequestParam Category category) {
        List<ProductDto> productList = ProductDto.convert(productService.findAllByCategory(category));
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

}
