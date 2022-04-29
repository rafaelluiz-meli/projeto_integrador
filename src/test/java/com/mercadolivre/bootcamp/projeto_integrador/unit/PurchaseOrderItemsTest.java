package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderItemsRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderItemsServiceImpl;
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
public class PurchaseOrderItemsTest {

    @Mock
    private PurchaseOrderItemsRepository purchaseOrderItemsRepository;

    @InjectMocks
    private PurchaseOrderItemsServiceImpl purchaseOrderItensService;

    @Test
    void shouldCreatePurchaseOrderItens() {
        //Arrange
        NewPurchaseOrderItemsDTO purchaseOrderItensDTO = NewPurchaseOrderItemsDTO.builder()
                .productId(1L)
                .quantity(10)
                .build();

        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder().build();

        //Act
        Mockito.when(purchaseOrderItemsRepository.save(any())).thenReturn(purchaseOrderItems);

        PurchaseOrderItems purchaseOrderItemsAdd =
                purchaseOrderItensService.addPurchaseOrderItens(purchaseOrderItensDTO);

        //Assert
        assertEquals(purchaseOrderItems, purchaseOrderItemsAdd);
    }

    @Test
    void shouldFindPurchaseOrderItensById() {
        //Arrange
        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder().PurchaseOrderItensId(1L).build();

        //Act
        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(Optional.of(purchaseOrderItems));
        PurchaseOrderItems foundedPurchaseOrderItems = purchaseOrderItensService.findById(1L);

        //Assert
        assertEquals(purchaseOrderItems, foundedPurchaseOrderItems);
    }

    @Test
    void shouldNotFindPurchaseOrderItensById() {
        //Act
        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> purchaseOrderItensService.findById(1L)
        );
    }

    @Test
    void shouldReturnAllPurchaseOrderItens() {
        //Arrange
        PurchaseOrderItems purchaseOrderItems1 = PurchaseOrderItems.builder().build();
        PurchaseOrderItems purchaseOrderItems2 = PurchaseOrderItems.builder().build();
        PurchaseOrderItems purchaseOrderItems3 = PurchaseOrderItems.builder().build();

        List<PurchaseOrderItems> purchaseOrderItemsList =
                Arrays.asList(purchaseOrderItems1, purchaseOrderItems2, purchaseOrderItems3);

        //Act
        Mockito.when(purchaseOrderItemsRepository.findAll()).thenReturn(purchaseOrderItemsList);

        List<PurchaseOrderItems> purchaseOrderItemsListTest =
                purchaseOrderItensService.getAllPurchaseOrderItens();

        //Assert
        assertEquals(purchaseOrderItemsList, purchaseOrderItemsListTest);
    }

    @Test
    void shouldNotReturnAllPurchaseOrderItens() {
        //Arrange
        List<PurchaseOrderItems> purchaseOrderItemsList = Arrays.asList();

        //Act
        Mockito.when(purchaseOrderItemsRepository.findAll()).thenReturn(purchaseOrderItemsList);

        //Assert
        assertThrows(EmptyListException.class, ()
                -> purchaseOrderItensService.getAllPurchaseOrderItens()
        );
    }

    @Test
    void shouldUpdatePurchaseOrderItens() {
        //Arrange
        NewPurchaseOrderItemsDTO purchaseOrderItensDTO = NewPurchaseOrderItemsDTO.builder()
                .productId(1L)
                .quantity(10)
                .build();

        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder().build();
        Optional<PurchaseOrderItems> purchaseOrderItensOptional = Optional.of(purchaseOrderItems);


        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(purchaseOrderItensOptional);
        Mockito.when(purchaseOrderItemsRepository.save(any())).thenReturn(purchaseOrderItems);
        PurchaseOrderItems updatedPurchaseOrderItems =
                purchaseOrderItensService.updatePurchaseOrderItens(purchaseOrderItems);

        //Assert
        Assertions.assertEquals(purchaseOrderItensOptional.get(), updatedPurchaseOrderItems);
    }

    @Test
    void shouldDeletePurchaseOrderItens() {
        //Arrange
        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder().PurchaseOrderItensId(1L).build();

        //Act
        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(Optional.of(purchaseOrderItems));
        doNothing().when(purchaseOrderItemsRepository).delete(any());

        //Assert
        assertDoesNotThrow(() -> {
            purchaseOrderItensService.deletePurchaseOrderItens(1L);
        });
    }

    @Test
    void shouldNotDeletePurchaseOrderItens() {
        //Act
        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> purchaseOrderItensService.deletePurchaseOrderItens(1L)
        );
    }
}
