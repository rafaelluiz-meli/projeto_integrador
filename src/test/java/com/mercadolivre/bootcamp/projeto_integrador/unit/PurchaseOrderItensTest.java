package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItens;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderItensRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderItensServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class PurchaseOrderItensTest {

    @Mock
    private PurchaseOrderItensRepository purchaseOrderItensRepository;

    @InjectMocks
    private PurchaseOrderItensServiceImpl purchaseOrderItensService;

    @Test
    void shouldCreatePurchaseOrderItens() {
        //Arrange
        NewPurchaseOrderItemsDTO purchaseOrderItensDTO = NewPurchaseOrderItemsDTO.builder()
                .purchaseOrderNumber(1L)
                .productId(1L)
                .quantity(10)
                .build();

        PurchaseOrderItens purchaseOrderItens = PurchaseOrderItens.builder().build();

        //Act
        Mockito.when(purchaseOrderItensRepository.save(any())).thenReturn(purchaseOrderItens);

        PurchaseOrderItens purchaseOrderItensAdd =
                purchaseOrderItensService.addPurchaseOrderItens(purchaseOrderItensDTO);

        //Assert
        assertEquals(purchaseOrderItens, purchaseOrderItensAdd);
    }

    @Test
    void shouldFindPurchaseOrderItensById() {
        //Arrange
        PurchaseOrderItens purchaseOrderItens = PurchaseOrderItens.builder().PurchaseOrderItensId(1L).build();

        //Act
        Mockito.when(purchaseOrderItensRepository.findById(any())).thenReturn(Optional.of(purchaseOrderItens));
        PurchaseOrderItens foundedPurchaseOrderItens = purchaseOrderItensService.findById(1L);

        //Assert
        assertEquals(purchaseOrderItens, foundedPurchaseOrderItens);
    }

    @Test
    void shouldNotFindPurchaseOrderItensById() {
        //Act
        Mockito.when(purchaseOrderItensRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> purchaseOrderItensService.findById(1L)
        );
    }

    @Test
    void shouldReturnAllPurchaseOrderItens() {
        //Arrange
        PurchaseOrderItens purchaseOrderItens1 = PurchaseOrderItens.builder().build();
        PurchaseOrderItens purchaseOrderItens2 = PurchaseOrderItens.builder().build();
        PurchaseOrderItens purchaseOrderItens3 = PurchaseOrderItens.builder().build();

        List<PurchaseOrderItens> purchaseOrderItensList =
                Arrays.asList(purchaseOrderItens1, purchaseOrderItens2, purchaseOrderItens3);

        //Act
        Mockito.when(purchaseOrderItensRepository.findAll()).thenReturn(purchaseOrderItensList);

        List<PurchaseOrderItens> purchaseOrderItensListTest =
                purchaseOrderItensService.getAllPurchaseOrderItens();

        //Assert
        assertEquals(purchaseOrderItensList, purchaseOrderItensListTest);
    }

    @Test
    void shouldNotReturnAllPurchaseOrderItens() {
        //Arrange
        List<PurchaseOrderItens> purchaseOrderItensList = Arrays.asList();

        //Act
        Mockito.when(purchaseOrderItensRepository.findAll()).thenReturn(purchaseOrderItensList);

        //Assert
        assertThrows(EmptyListException.class, ()
                -> purchaseOrderItensService.getAllPurchaseOrderItens()
        );
    }

    @Test
    void shouldUpdatePurchaseOrderItens() {
        //Arrange
        NewPurchaseOrderItemsDTO purchaseOrderItensDTO = NewPurchaseOrderItemsDTO.builder()
                .purchaseOrderNumber(1L)
                .productId(1L)
                .quantity(10)
                .build();

        PurchaseOrderItens purchaseOrderItens = PurchaseOrderItens.builder().build();
        Optional<PurchaseOrderItens> purchaseOrderItensOptional = Optional.of(purchaseOrderItens);


        Mockito.when(purchaseOrderItensRepository.findById(any())).thenReturn(purchaseOrderItensOptional);
        Mockito.when(purchaseOrderItensRepository.save(any())).thenReturn(purchaseOrderItens);
        PurchaseOrderItens updatedPurchaseOrderItens =
                purchaseOrderItensService.updatePurchaseOrderItens(purchaseOrderItens);

        //Assert
        Assertions.assertEquals(purchaseOrderItensOptional.get(),updatedPurchaseOrderItens);
    }

    @Test
    void shouldDeletePurchaseOrderItens() {
        //Arrange
        PurchaseOrderItens purchaseOrderItens = PurchaseOrderItens.builder().PurchaseOrderItensId(1L).build();

        //Act
        Mockito.when(purchaseOrderItensRepository.findById(any())).thenReturn(Optional.of(purchaseOrderItens));
        doNothing().when(purchaseOrderItensRepository).delete(any());

        //Assert
        assertDoesNotThrow(() -> {
            purchaseOrderItensService.deletePurchaseOrderItens(1L);
        });
    }

    @Test
    void shouldNotDeletePurchaseOrderItens() {
        //Act
        Mockito.when(purchaseOrderItensRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> purchaseOrderItensService.deletePurchaseOrderItens(1L)
        );
    }
}
