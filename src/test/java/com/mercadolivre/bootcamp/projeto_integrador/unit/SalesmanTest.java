package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.exception.SalesmanDoesNotExistException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SalesmanRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.SalesmanServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class SalesmanTest {

    @Mock
    private SalesmanRepository repository;

    @InjectMocks
    private SalesmanServiceImpl service;

    @Test
    @DisplayName("This test should create a salesman.")
    public void shouldCreateSalesman(){
        //Arrange
        Salesman salesman = Salesman.builder().build();

        //Act
        Mockito.when(repository.save(any())).thenReturn(salesman);
        Salesman result = service.createSalesman(salesman);

        //Assert
        assertEquals(salesman, result);
    }

    @Test
    @DisplayName("This test should find a salesman by his id.")
    public void shouldFindSalesmanById (){
        // arrange
        Salesman salesman = Salesman.builder()
                .id("1").build();
        // act
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(salesman));
        Salesman result = service.findSalesmanById("1");

        // assert
        assertEquals(salesman, result);
    }

    @Test
    @DisplayName("This test should not find a salesman by his id.")
    public void shouldNotFindSalesmanById (){
        // arrange

        // act
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        // assert
        assertThrows(SalesmanDoesNotExistException.class, ()->service.findSalesmanById("id"));
    }

    @Test
    @DisplayName("This test should list all the salesman.")
    public void shouldListSalesman(){
        // arrange
        Salesman salesman = Salesman.builder().build();
        List<Salesman> salesmanList = Arrays.asList(salesman, salesman, salesman);

        // act
        Mockito.when(repository.findAll()).thenReturn(salesmanList);
        List<Salesman> result = service.listSalesman();

        // assert
        assertEquals(salesmanList, result);
    }

    @Test
    @DisplayName("This test should remove a salesman by his id.")
    public void shouldRemoveSalesman(){
        // arrange
        Salesman salesman = Salesman.builder().id("id").build();

        // act
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(salesman));
        doNothing().when(repository).delete(any());

        // assert
        assertDoesNotThrow(()-> service.removeSalesman("id"));
    }

    @Test
    @DisplayName("This test should not remove a salesman by his id.")
    public void shouldNotRemoveSalesman(){
        // arrange

        // act
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        // assert
        assertThrows(SalesmanDoesNotExistException.class, ()->service.findSalesmanById("id"));
    }

    @Test
    @DisplayName("This test should update a salesman.")
    public void shouldUpdateSalesman(){
        // arrange
        Salesman salesman = Salesman.builder().build();

        // act
        Mockito.when(repository.save(any())).thenReturn(salesman);
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(salesman));
        Salesman result = service.updateSalesman("id", salesman);

        // assert
        assertEquals(salesman, result);
    }

}
