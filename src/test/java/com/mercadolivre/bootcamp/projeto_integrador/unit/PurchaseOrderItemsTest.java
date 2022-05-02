package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderItemsRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderItemsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
    private PurchaseOrderItemsServiceImpl purchaseOrderItemsService;

    @Test
    @DisplayName("It should create new purchase order items.")
    void shouldCreatePurchaseOrderItems() {
        //Arrange
        NewPurchaseOrderItemsDTO purchaseOrderItemsDTO = NewPurchaseOrderItemsDTO.builder()
                .productId(1L)
                .quantity(10)
                .build();

        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder().build();

        //Act
        Mockito.when(purchaseOrderItemsRepository.save(any())).thenReturn(purchaseOrderItems);

        PurchaseOrderItems purchaseOrderItemsAdd =
                purchaseOrderItemsService.addPurchaseOrderItems(purchaseOrderItemsDTO);

        //Assert
        assertEquals(purchaseOrderItems, purchaseOrderItemsAdd);
    }

    @Test
    @DisplayName("It should find purchase order items by id.")
    void shouldFindPurchaseOrderItemsById() {
        //Arrange
        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder().PurchaseOrderItemsId(1L).build();

        //Act
        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(Optional.of(purchaseOrderItems));
        PurchaseOrderItems foundedPurchaseOrderItems = purchaseOrderItemsService.findById(1L);

        //Assert
        assertEquals(purchaseOrderItems, foundedPurchaseOrderItems);
    }

    @Test
    @DisplayName("It should not find purchase order items by id.")
    void shouldNotFindPurchaseOrderItemsById() {
        //Act
        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> purchaseOrderItemsService.findById(1L)
        );
    }

    @Test
    @DisplayName("It should list all purchase order items.")
    void shouldReturnAllPurchaseOrderItems() {
        //Arrange
        PurchaseOrderItems purchaseOrderItems1 = PurchaseOrderItems.builder().build();
        PurchaseOrderItems purchaseOrderItems2 = PurchaseOrderItems.builder().build();
        PurchaseOrderItems purchaseOrderItems3 = PurchaseOrderItems.builder().build();

        List<PurchaseOrderItems> purchaseOrderItemsList =
                Arrays.asList(purchaseOrderItems1, purchaseOrderItems2, purchaseOrderItems3);

        //Act
        Mockito.when(purchaseOrderItemsRepository.findAll()).thenReturn(purchaseOrderItemsList);

        List<PurchaseOrderItems> purchaseOrderItemsListTest =
                purchaseOrderItemsService.getAllPurchaseOrderItems();

        //Assert
        assertEquals(purchaseOrderItemsList, purchaseOrderItemsListTest);
    }

    @Test
    @DisplayName("It should not list all purchase order items")
    void shouldNotReturnAllPurchaseOrderItems() {
        //Arrange
        List<PurchaseOrderItems> purchaseOrderItemsList = Arrays.asList();

        //Act
        Mockito.when(purchaseOrderItemsRepository.findAll()).thenReturn(purchaseOrderItemsList);

        //Assert
        assertThrows(EmptyListException.class, ()
                -> purchaseOrderItemsService.getAllPurchaseOrderItems()
        );
    }

    @Test
    @DisplayName("It should update purchase order items.")
    void shouldUpdatePurchaseOrderItems() {
        //Arrange
        NewPurchaseOrderItemsDTO purchaseOrderItemsDTO = NewPurchaseOrderItemsDTO.builder()
                .productId(1L)
                .quantity(10)
                .build();

        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder().build();
        Optional<PurchaseOrderItems> purchaseOrderItemsOptional = Optional.of(purchaseOrderItems);


        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(purchaseOrderItemsOptional);
        Mockito.when(purchaseOrderItemsRepository.save(any())).thenReturn(purchaseOrderItems);
        PurchaseOrderItems updatedPurchaseOrderItems =
                purchaseOrderItemsService.updatePurchaseOrderItems(purchaseOrderItems);

        //Assert
        Assertions.assertEquals(purchaseOrderItemsOptional.get(), updatedPurchaseOrderItems);
    }

    @Test
    @DisplayName("It should delete purchase order items by id.")
    void shouldDeletePurchaseOrderItems() {
        //Arrange
        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder().PurchaseOrderItemsId(1L).build();

        //Act
        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(Optional.of(purchaseOrderItems));
        doNothing().when(purchaseOrderItemsRepository).delete(any());

        //Assert
        assertDoesNotThrow(() -> {
            purchaseOrderItemsService.deletePurchaseOrderItems(1L);
        });
    }

    @Test
    @DisplayName("It should not delete purchase order items by id.")
    void shouldNotDeletePurchaseOrderItems() {
        //Act
        Mockito.when(purchaseOrderItemsRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> purchaseOrderItemsService.deletePurchaseOrderItems(1L)
        );
    }
}
