package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockServiceImpl;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BatchStockTest {

    @Mock
    private BatchStockRepository repository;

    @InjectMocks
    private BatchStockServiceImpl service;

    @Test
    public void shouldCreateBatchStock(){
        // Create BatchStock Entity Object
        BatchStock newBatchStock = BatchStock.builder().batchNumber(1L).build();
        // Where Mockito searching when create new BatchStock method is calling
        Mockito.when(repository.save(Mockito.any(BatchStock.class))).thenReturn(newBatchStock);

        BatchStock result = service.create(newBatchStock);

        assertEquals(newBatchStock, result);
        //Mockito.verify(Mockito.mock(BatchStockRepository.class), times(1)).save(newBatchStock);
    }
}
