package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PurchaseOrderServiceTest {

    @Mock
    PurchaseOrderRepository repository;

    @InjectMocks
    PurchaseOrderServiceImpl service;

    @Test
    @DisplayName("It should do create a new PurchaseOrder.")
    public void shouldCreatePurchaseOrder() {

    }

    @Test
    @DisplayName("It should do list all PurchaseOrders.")
    public void shouldFindAllPurchaseOrders() {

    }

    @Test
    @DisplayName("It should do find PurchaseOrder by id.")
    public void shouldFindPurchaseOrderById() {

    }

    @Test
    @DisplayName("It should not do find PurchaseOrder by id when it not exists.")
    public void shouldNotFindPurchaseOrderByIdWhenIdNotExists() {

    }

    @Test
    @DisplayName("It should do update a PurchaseOrder.")
    public void shouldUpdatePurchaseOrder() {

    }

    @Test
    @DisplayName("It should do delete a PurchaseOrder.")
    public void shouldDeletePurchaseOrder() {

    }
}