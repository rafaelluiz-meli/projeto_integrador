package com.mercadolivre.bootcamp.projeto_integrador.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock.ResponseInBoundOrderBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.RequestInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.section.SectionDTO;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class InboundOrderControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    private final String baseURI = "/api/v1/fresh-products/inboundorder";

    @Test
    @DisplayName("it should create new inbound order")
    public void shouldCreateNewInboundOrder() throws Exception {
        // Arrange
        RequestInBoundOrderDTO request = this.createInboundOrder();

        this.mvc.perform(
                MockMvcRequestBuilders
                    .post(baseURI)
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("it should update existing inbound order")
    public void shouldUpdateExistingInboundOrder() throws Exception {
        RequestInBoundOrderDTO request = this.createInboundOrder();
        this.mvc.perform(
                MockMvcRequestBuilders
                    .put(baseURI + "/" + request.getOrderNumber())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private RequestInBoundOrderDTO createInboundOrder() {
        Long id = 1L;
        int quantity = 5;
        BigDecimal price = BigDecimal.valueOf(100);
        LocalDate localDate = LocalDate.now().plusDays(10);
        LocalDateTime localDateTime = LocalDateTime.now();

        SectionDTO section = SectionDTO.builder().warehouseId(id).sectionId(id).build();
        ResponseInBoundOrderBatchStockDTO batchStock = ResponseInBoundOrderBatchStockDTO.builder()
                .quantity(quantity)
                .price(price)
                .productId(id)
                .dueDate(localDate)
                .manufacturingDate(localDate)
                .manufacturingTime(localDateTime)
                .build();

        return RequestInBoundOrderDTO.builder()
                .orderNumber(id)
                .representativeId(id)
                .section(section)
                .batchStock(batchStock)
                .build();
    };
}
