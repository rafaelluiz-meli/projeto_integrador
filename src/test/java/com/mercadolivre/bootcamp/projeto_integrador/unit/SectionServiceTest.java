package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.*;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SectionRepository;
import com.mercadolivre.bootcamp.projeto_integrador.repository.WarehouseRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.SectionServiceImpl;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private SectionServiceImpl sectionService;

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @Test
    @DisplayName("It should do create a new Section.")
    void shouldSaveNewSection() {
        //Arrange
        Section section = Section.builder().build();

        //Act

        Mockito.when(sectionRepository.save(any())).thenReturn(section);

        Section sectionAdd = sectionService.addSection(section);

        //Assert
        Assertions.assertEquals(section, sectionAdd);

    }

    @Test
    @DisplayName("It should return all Sections.")
    void shouldReturnAllSections() {
        //Arrange
        Section section = Section.builder().build();
        Section section1 = Section.builder().build();
        Section section2 = Section.builder().build();

        List<Section> listSections = Arrays.asList(section,section2,section1);

        //Act
        Mockito.when(sectionRepository.findAll()).thenReturn(listSections);

        List<Section> listSectionsTest = sectionService.getAllSection();

        //Assert
        Assertions.assertEquals(listSections, listSectionsTest);

    }

    @Test
    @DisplayName("It should return a Section by id.")
    void shouldReturnSectionById() {

        //Arrange
        Section section = Section.builder()
                .sectionId(1l)
                .capacity(new BigDecimal(100))
                .category(Category.REFRIGERATED)
                .currentTemperature(15)
                .warehouseId(1L)
                .listInBoundOrder(new ArrayList<>()).build();

        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        Section foundSection = sectionService.getSectionById(any());

        //Assert
        Assertions.assertEquals(sectionOptional.get(), foundSection);

    }


    @Test
    @DisplayName("It should update a Section.")
    void shouldUpdateSection(){
        //Arrange
        NewSectionDTO sectionDTO = NewSectionDTO.builder()
                .capacity(new BigDecimal(100))
                .currentTemperature(10)
                .category(Category.REFRIGERATED)
                .warehouseId(1L).build();

        Section section = Section.builder().build();
        Optional<Section> sectionOptional = Optional.of(section);


        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        Mockito.when(sectionRepository.save(any())).thenReturn(section);
        Section sectionUpdated = sectionService.updateSection(section);

        //Assert

        Assertions.assertEquals(sectionOptional.get(), sectionUpdated);

    }


    @Test
    @DisplayName("It should not return a Section by id.")
    void shouldReturnErrorWhenFindById() {

        //Arrange
        Optional<Section> sectionOptionalNull = Optional.empty();

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptionalNull);

        //Assert
        Assertions.assertThrows(IdNotFoundException.class,
                () -> {
                    sectionService.getSectionById(1000000L);
                });
    }
    @Test
    @DisplayName("It should return true when the section is valid.")
    void shouldReturnTrueWhenCallMethodIsValidSection() {

        //Arrange
        Section section = Section.builder().build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        boolean isValid = sectionService.isSectionValid(5L);

        //Assert
        Assertions.assertTrue(isValid);

    }

    @Test
    @DisplayName("It should throws a exception when we try to update a section and not found the id.")
    void shouldReturnErrorWhenUpdateSectionAndNotFoundId() {

        //Arrange
        Section section = Section.builder().build();
        Optional<Section> sectionOptionalNull = Optional.empty();

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptionalNull);

        //Assert
        Assertions.assertThrows(IdNotFoundException.class,
                () -> {
                    sectionService.updateSection(section);
                });
    }

    @Test
    @DisplayName("It should return true when a available section has capacity.")
    void shouldReturnTrueWhenAvailableSectionHasCapacity(){
        //Arrange
        Section section = Section.builder().capacity(new BigDecimal(10000)).build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        Boolean isCapacity = sectionService.availableSectionCapacity(new BigDecimal(200), any());

        //Asserts
        Assertions.assertTrue(isCapacity);

    }

    @Test
    @DisplayName("It should return true if the warehouse is valid.")
    void shouldReturnTrueIfTheWarehouseIsValid(){
        //Arrange
        Warehouse warehouse = Warehouse.builder().warehouseId(1L).build();

        //Act
        Mockito.when(warehouseRepository.findById(any())).thenReturn(Optional.of(warehouse));
        Boolean isValid = sectionService.isWarehouseValid(1L);

        //Asserts
        Assertions.assertTrue(isValid);
    }

    @Test
    @DisplayName("it should return all sections if warehouse id exists.")
    public void shouldReturnAllSectionsByWarehouseId(){
        //arrange
        Section section = Section.builder().warehouseId(1L).build();
        List<Section> sectionList = Arrays.asList(section, section, section, section);
        Warehouse warehouse = Warehouse.builder().warehouseId(1L).build();

        // act
        Mockito.when(sectionRepository.findAllByWarehouseId(any())).thenReturn(sectionList);
        Mockito.when(warehouseRepository.findById(any())).thenReturn(Optional.of(warehouse));
        Warehouse id = warehouseService.findById(warehouse.getWarehouseId());
        List<Section> result = sectionService.getAllSectionByWarehouseId(id.getWarehouseId());

        // assert
        assertEquals(sectionList, result);
    }

    @Test
    @DisplayName("It should return false when a available section has no capacity.")
    void shouldReturnFalseWhenAvailableSectionHasNotCapacity(){
        //Arrange
        Section section = Section.builder().capacity(new BigDecimal(10000)).build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        Boolean isCapacity = sectionService.availableSectionCapacity(new BigDecimal(12000), any());

        //Asserts
        Assertions.assertFalse(isCapacity);

    }

    @Test
    @DisplayName("It should delete a section.")
    public void shouldDeleteSection() {
        // Arrange
        Section section = Section.builder().sectionId(100l).build();

        // Act
        doNothing().when(sectionRepository).deleteById(any());

        // Assert
        assertDoesNotThrow(() -> {
            sectionService.deleteSection(100L);
        });
    }

    @Test
    @DisplayName("It should return true when a corresponds to a product type.")
    public void shouldReturnTrueWhenSectionCorrespondsProductType(){

        //Arrange
        Product product01 = Product.builder().category(Category.REFRIGERATED).build();
        BatchStock batchStock1 = BatchStock.builder().product(product01).build();
        Section section = Section.builder().category(Category.REFRIGERATED).build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        boolean correspondsProductType = sectionService.sectionCorrespondsProductType(any(), batchStock1.getProduct().getCategory());

        //Asserts
        Assertions.assertTrue(correspondsProductType);

    }

    @Test
    @DisplayName("It should return false when a section does not corresponds to a product type.")
    public void shouldReturnFalseWhenSectionDoesntCorrespondsProductType(){
        //Arrange
        Product product01 = Product.builder().category(Category.REFRIGERATED).build();
        BatchStock batchStock1 = BatchStock.builder().product(product01).build();
        Section section = Section.builder().category(Category.FRESH).build();
        Optional<Section> sectionOptional = Optional.of(section);

        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptional);
        boolean correspondsProductType = sectionService.sectionCorrespondsProductType(any(), batchStock1.getProduct().getCategory());

        //Asserts
        Assertions.assertFalse(correspondsProductType);
    }

    @Test
    @DisplayName("It should throws a exception when verify a section corresponds type and not found the id.")
    void shouldReturnErrorWhenVerifySectionCorrespondsProductTypeAndNotFoundId() {

        //Arrange
        Product product01 = Product.builder().category(Category.REFRIGERATED).build();
        BatchStock batchStock1 = BatchStock.builder().product(product01).build();
        Optional<Section> sectionOptionalNull = Optional.empty();


        //Act
        Mockito.when(sectionRepository.findById(any())).thenReturn(sectionOptionalNull);

        //Assert
        Assertions.assertThrows(IdNotFoundException.class,
                () -> {
                    sectionService.sectionCorrespondsProductType(any(), batchStock1.getProduct().getCategory());
                });
    }



}
