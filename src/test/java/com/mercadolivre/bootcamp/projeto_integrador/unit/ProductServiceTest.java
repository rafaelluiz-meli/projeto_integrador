package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.repository.ProductRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    public void shouldFindProductByName() {
        // TODO: 25/04/22
    }

    public void shouldFindProductByCategory() {
        // TODO: 25/04/22  
    }
    
    public void shouldFindProductListBySalesmanId() {
        // TODO: 25/04/22  
    }
    
    public void shouldFindProductList() {
        // TODO: 25/04/22  
    }

}
