package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.repository.WarehouseRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {
    @Mock
    WarehouseRepository wareHouseRepository;

    @InjectMocks
    WarehouseServiceImpl warehouseService;

    //Arrange
    String warehouseId = "teste";
    Warehouse warehouse = Warehouse.builder().warehouseId(warehouseId).build();
    public LinkedList<Warehouse> warehouseList = new LinkedList<>();


    //creating optionals
    final Optional<Warehouse> optionalWarehouse = Optional.of(warehouse);
    final Optional<List<Warehouse>> optionalListWarehouse = Optional.of(warehouseList);


    @Test
    void shouldFindAllWarehouses(){
        //Prepare
        warehouseList.add(warehouse);

        //Act
        Mockito.when(wareHouseRepository.findAll()).thenReturn(warehouseList);
        List<Warehouse> whList = warehouseService.findAll();

        //Assert
        assertEquals(whList,warehouseList);
    }

    @Test
    void shouldFindWarehouseById(){
        //Act
        Mockito.when(wareHouseRepository.findById(anyString())).thenReturn(optionalWarehouse);
        Warehouse wh = warehouseService.findById(warehouseId);

        //assert
        assertEquals(wh.getWarehouseId(),warehouseId);

    }

    @Test
    void shouldValidWarehouse(){

        //Act
        Mockito.when(wareHouseRepository.findById(anyString())).thenReturn(optionalWarehouse);
        boolean test = warehouseService.isValidWarehouse(warehouseId);

        assertTrue(test);
    }

    @Test
    void shouldSaveWarehouse(){
        //Act
        Mockito.when(wareHouseRepository.save(any())).thenReturn(warehouse);
        Warehouse wh = warehouseService.save(warehouse);

        //assert
        //Check if it has ben saved and has ID
        assertTrue(warehouse.getWarehouseId() != null);
    }

}




