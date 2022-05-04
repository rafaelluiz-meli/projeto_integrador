package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.*;
import com.mercadolivre.bootcamp.projeto_integrador.exception.product.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockServiceImpl;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BatchStockServiceTest {
    @Mock
    private BatchStockRepository batchStockRepository;

    @InjectMocks
    private BatchStockServiceImpl batchStockService;

    Long id = 1L;
    Long productId = 1L;
    BatchStock batchStock1 = BatchStock.builder().batchNumber(1L).product(Product.builder().id(productId).build()).build();
    BatchStock batchStock2 = BatchStock.builder().batchNumber(2L).product(Product.builder().id(productId).build()).build();
    BatchStock batchStock3 = BatchStock.builder().batchNumber(3L).product(Product.builder().id(productId).build()).build();
    List<BatchStock> batchStockList = Arrays.asList(batchStock1,batchStock2,batchStock3);


    @Test
    @DisplayName("it should calculate totalVolume of given BatchStock")
    public void shouldCalculateTotalVolume() {
        // Arrange
        Integer currentQuantity = 5;
        BigDecimal volume = BigDecimal.valueOf(10);
        BigDecimal expectedVolume = volume.multiply(BigDecimal.valueOf(currentQuantity));
        Product product = Product.builder().volume(volume).build();
        BatchStock batchStock = BatchStock.builder().currentQuantity(currentQuantity).product(product).build();

        // Exec
        BigDecimal result = batchStockService.calculateTotalVolume(batchStock);

        // Assert
        assertEquals(expectedVolume, result);
    }

    @Test
    @DisplayName("It should do create a new BatchStock.")
    public void shouldCreateBatchStock(){

        Mockito.when(batchStockRepository.save(Mockito.any(BatchStock.class))).thenReturn(batchStock1);

        BatchStock result = batchStockService.create(batchStock1);

        assertEquals(batchStock1, result);
    }

    @Test
    @DisplayName("It should do list all BatchStocks.")
    public void shouldListAllBatchStocks(){

        Mockito.when(batchStockRepository.findAll()).thenReturn(batchStockList);

        List<BatchStock> result = batchStockService.findAll();

        assertEquals(batchStockList, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("It should do delete BatchStock by id.")
    public void shouldDeleteBatchStockById(){

        Mockito.when(batchStockRepository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));
        doNothing().when(batchStockRepository).delete(any());

        assertDoesNotThrow(()-> batchStockService.remove(id));
    }

    @Test
    @DisplayName("It should not do delete BatchStock by id when it not exists.")
    public void shouldNotDeleteBatchStockByIdWhenIdNotExist(){
        assertThrows(IdNotFoundException.class,()-> batchStockService.remove(anyLong()));
    }

    @Test
    public void shouldUpdateBatchStock(){

        BatchStock updatedBatchStock = BatchStock.builder()
                .batchNumber(1L)
                .currentQuantity(20)
                .build();

        Mockito.when(batchStockRepository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));
        Mockito.when(batchStockRepository.save(Mockito.any(BatchStock.class))).thenReturn(updatedBatchStock);

        BatchStock result = batchStockService.update(updatedBatchStock);

        assertEquals(updatedBatchStock, result);
    }

    @Test
    @DisplayName("It should do find BatchStock by id.")
    public void shouldFindBatchStockById(){

        Mockito.when(batchStockRepository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));

        BatchStock result = batchStockService.findById(id);

        assertEquals(batchStock1, result);
    }

    @Test
    @DisplayName("It should not do find BatchStock by id when it not exists.")
    public void shouldNotFindBatchStockByIdWhenIdNotExists(){
        assertThrows(IdNotFoundException.class,()-> batchStockService.findById(anyLong()));
    }

    @Test
    @DisplayName("It should do list all BatchStocks by product id.")
    public void shouldListBatchStockByProductId(){

        Mockito.when(batchStockRepository.findAllByProduct_Id(anyLong())).thenReturn(batchStockList);

        List<BatchStock> result = batchStockService.findAllByProductId(productId);

        assertEquals(batchStockList, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("It should not do list all BatchStocks by product id when it not exists.")
    public void shouldNotListBatchStockByProductIdWhenProductIdNotExists(){
        assertThrows(InvalidProductException.class, () -> batchStockService.findAllByProductId(anyLong()));
    }

}
