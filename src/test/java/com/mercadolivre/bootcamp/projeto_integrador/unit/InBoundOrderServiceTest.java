package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.dto.InboundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.section.SectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.InBoundOrderRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.InBoundOrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class InBoundOrderServiceTest {
    @Mock
    private InBoundOrderRepository inBoundOrderRepository;

    @InjectMocks
    private InBoundOrderServiceImpl inBoundOrderService;

    Section section = Section.builder()
            .sectionId(5L)
            .warehouseId(5L).build();

    BatchStock batchStock = BatchStock.builder().batchNumber(1L).product(Product.builder().id(1L).build()).build();

    InboundOrderDTO inBoundOrderDTO = InboundOrderDTO.builder()
            .representativeId(1L)
            .section(section)
            .batchStock(batchStock)
            .build();

    @Test
    void shouldCreateInBoundOrder() {
        //Arrange
        InBoundOrder inBoundOrder = InBoundOrder.builder().build();

        //Act
        Mockito.when(inBoundOrderRepository.save(any())).thenReturn(inBoundOrder);
        InBoundOrder inBoundOrderAdd =
                inBoundOrderService.addInBoundOrder(inBoundOrderDTO);

        //Assert
        assertEquals(inBoundOrder, inBoundOrderAdd);
    }

    @Test
    void shouldFindInBoundOrderById() {
        //Arrange
        InBoundOrder inBoundOrder = InBoundOrder.builder().inBoundOrderNumber(1L).build();

        //Act
        Mockito.when(inBoundOrderRepository.findById(any())).thenReturn(Optional.of(inBoundOrder));
        InBoundOrder foundedInBoundOrder = inBoundOrderService.findById(1L);

        //Assert
        assertEquals(inBoundOrder, foundedInBoundOrder);
    }

    @Test
    void shouldNotFindInBoundOrderById() {
        //Act
        Mockito.when(inBoundOrderRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> inBoundOrderService.findById(1L)
        );
    }

    @Test
    void shouldReturnAllInBoundOrder() {
        //Arrange
        InBoundOrder inBoundOrder1 = InBoundOrder.builder().build();
        InBoundOrder inBoundOrder2 = InBoundOrder.builder().build();
        InBoundOrder inBoundOrder3 = InBoundOrder.builder().build();

        List<InBoundOrder> inBoundOrderList =
                Arrays.asList(inBoundOrder1, inBoundOrder2, inBoundOrder3);

        //Act
        Mockito.when(inBoundOrderRepository.findAll()).thenReturn(inBoundOrderList);

        List<InBoundOrder> inBoundOrderListTest =
                inBoundOrderService.getAllInBoundOrder();

        //Assert
        assertEquals(inBoundOrderList, inBoundOrderListTest);
    }

    @Test
    void shouldNotReturnAllInBoundOrder() {
        //Arrange
        List<InBoundOrder> InBoundOrderList = Collections.emptyList();

        //Act
        Mockito.when(inBoundOrderRepository.findAll()).thenReturn(InBoundOrderList);

        //Assert
        assertThrows(EmptyListException.class, ()
                -> inBoundOrderService.getAllInBoundOrder()
        );
    }

    @Test
    void shouldUpdateInBoundOrder() {
        //Arrange
        InBoundOrder inBoundOrder = InBoundOrder.builder().build();
        Optional<InBoundOrder> inBoundOrderOptional = Optional.of(inBoundOrder);


        Mockito.when(inBoundOrderRepository.findById(any())).thenReturn(inBoundOrderOptional);
        Mockito.when(inBoundOrderRepository.save(any())).thenReturn(inBoundOrder);
        InBoundOrder updatedInBoundOrder =
                inBoundOrderService.updateInBoundOrder(inBoundOrder);

        //Assert
        Assertions.assertEquals(inBoundOrderOptional.get(),updatedInBoundOrder);
    }

    @Test
    void shouldDeleteInBoundOrder() {
        //Arrange
        InBoundOrder inBoundOrder = InBoundOrder.builder().inBoundOrderNumber(1L).build();

        //Act
        Mockito.when(inBoundOrderRepository.findById(any())).thenReturn(Optional.of(inBoundOrder));
        doNothing().when(inBoundOrderRepository).delete(any());

        //Assert
        assertDoesNotThrow(() -> {
            inBoundOrderService.deleteInBoundOrder(1L);
        });
    }

    @Test
    void shouldNotDeleteInBoundOrder() {
        //Act
        Mockito.when(inBoundOrderRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(IdNotFoundException.class, ()
                -> inBoundOrderService.deleteInBoundOrder(1L)
        );
    }
}
