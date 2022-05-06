package com.mercadolivre.bootcamp.projeto_integrador.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.bootcamp.projeto_integrador.dto.product.NewProductDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Tag("integration")
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String baseURI = "/api/v1/fresh-products/product";

    @Test
    @DisplayName("it should create new product")
    public void shouldCreateNewProduct() throws Exception {
        NewProductDTO request = this.createProductDTO();

        this.mvc.perform(
                MockMvcRequestBuilders.post(baseURI)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("it should list all products")
    public void shouldListAllProducts() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.get(baseURI))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("it should list single product by category")
    public void shouldListProduct() throws Exception {
        this.mvc.perform(
                MockMvcRequestBuilders.get(baseURI + "/list?category=FRESH"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private NewProductDTO createProductDTO() {
        Float temp = 1F;
        String name = "Product";
        BigDecimal volume = BigDecimal.valueOf(10);
        Long id = 1L;

        return NewProductDTO.builder()
                .category(Category.FRESH)
                .maxTemperature(temp)
                .minimumTemperature(temp)
                .productName(name)
                .volume(volume)
                .salesman_id(id)
                .build();
    }
}
