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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    final Optional<Warehouse> optionalWarehouse = Optional.of(warehouse);


    @Test
    void deveAcharWarehouse(){
        //Arrange
        WarehouseRepository wareHouseRepository = Mockito.mock(WarehouseRepository.class);
        Warehouse warehouse = Warehouse.builder().warehouseId(warehouseId).build();
        WarehouseServiceImpl warehouseService = new WarehouseServiceImpl(wareHouseRepository);
        warehouseService.save(warehouse);

        //Act
        Mockito.when(wareHouseRepository.findById(anyString())).thenReturn(optionalWarehouse);
        Warehouse wh = warehouseService.findById(warehouseId);

        //Assert
        assertEquals(wh.getWarehouseId(),"teste");




    }
}


