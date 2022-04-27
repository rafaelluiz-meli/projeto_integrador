package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.exception.representativeException.RepresentativeNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.RepresentativeRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.RepresentativeServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RepresentativeServiceTest {

    @Mock
    private RepresentativeRepository representativeRepository;

    @InjectMocks
    RepresentativeServiceImpl representativeService;

    @Test
    @DisplayName("It should get all representatives")
    public void shoudGetAllRepresentatives() {
        //Arrange tests
        Representative representative = Representative.builder().build();
        List<Representative> representativeList = Arrays.asList(representative, representative);

        //Act
        Mockito.when(representativeRepository.findAll()).thenReturn(representativeList);
        List<Representative> result = representativeService.getAllRepresentatives();

        //Assert
        assertEquals(representativeList, result);


    }

    @Test
    @DisplayName("It should get representatives by id")
    public void shouldGetRepresentativesById() {
        //Arrange tests
        Representative representative = Representative.builder().build();

        //Act
        Mockito.when(representativeRepository.findById(any())).thenReturn(Optional.of(representative));
        Representative result = representativeService.getRepresentativeById("R01");

        //Assert
        assertEquals(representative, result);

    }

    @Test
    @DisplayName("It should return an exception when representatives by id is not found")
    public void shouldReturnExceptionWhenRepresentativesByIdNotFound() {
        //Arrange tests

        //Act
        Mockito.when(representativeRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(RepresentativeNotFoundException.class, () -> representativeService.getRepresentativeById("A"));

    }

    @Test
    @DisplayName("It should create a representative")
    public void shouldCreateRepresentative() {
        //Arrange tests
        Representative representative = Representative.builder().build();

        //Act
        Mockito.when(representativeRepository.save(any())).thenReturn(representative);
        Representative result = representativeService.createRepresentative(representative);

        //Assert
        assertEquals(representative, result);

    }

    @Test
    @DisplayName("It should update a representative")
    public void shouldUpdateRepresentative() {
        //Arrange tests
        Representative representative = Representative.builder().build();

        //Act
        Mockito.when(representativeRepository.save(any())).thenReturn(representative);
        Mockito.when(representativeRepository.findById(any())).thenReturn(Optional.of(representative));
        Representative result = representativeService.updateRepresentative("R01", representative);

        //Assert
        assertEquals(representative, result);
    }

    @Test
    @DisplayName("It should delete a representative")
    public void shouldDeleteRepresentative() {
        //Arrange tests
        Representative representative = Representative.builder().build();

        //Act
        Mockito.when(representativeRepository.findById(any())).thenReturn(Optional.of(representative));
        doNothing().when(representativeRepository).delete(any());

        //Assert
        assertDoesNotThrow(() -> representativeService.deleteRepresentative("R02"));

    }

    @Test
    @DisplayName("It should not delete a representative")
    public void shouldNotDeleteRepresentative() {
        //Arrange tests
        Representative representative = Representative.builder().build();

        //Act
        Mockito.when(representativeRepository.findById(any())).thenReturn(Optional.empty());

        //Assert
        assertThrows(RepresentativeNotFoundException.class, () -> representativeService.deleteRepresentative("A"));
    }

    @Test
    @DisplayName("It should inform if a representative is associated with section")
    public void shouldInformIfRepresentativeIsAssociatedWithSection() {
        //Arrange tests
        Representative representative = Representative.builder().sectionId("1").build();

        //Act
        Mockito.when(representativeRepository.findById(any())).thenReturn(Optional.of(representative));
        Boolean result = representativeService.isRepresentativeAssociatedWithSection("R01", "1");

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("It should inform if a representative is not associated with section")
    public void shouldInformIfRepresentativeIsNotAssociatedWithSection() {
        //Arrange tests
        Representative representative = Representative.builder().sectionId("1").build();

        //Act
        Mockito.when(representativeRepository.findById(any())).thenReturn(Optional.of(representative));
        Boolean result = representativeService.isRepresentativeAssociatedWithSection("R01", "2");

        //Assert
        assertFalse(result);
    }


}
