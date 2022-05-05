package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.product.NewProductDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductServiceImpl;
import com.mercadolivre.bootcamp.projeto_integrador.service.SalesmanServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductFactory {

    final private ProductServiceImpl productService;
    final private SalesmanServiceImpl salesmanService;

    public Product createProduct(NewProductDTO productDto) {
        salesmanService.findSalesmanById(productDto.getSalesman_id());
        return productService.create(productDto);
    }


}
