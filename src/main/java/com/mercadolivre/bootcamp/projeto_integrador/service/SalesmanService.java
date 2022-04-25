package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSalesmanDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;

import java.util.List;

public interface SalesmanService {
        Salesman createSalesman(NewSalesmanDto newSalesmanDto);
        Salesman findSalesman(String salesmanId);
        List<Salesman> listSalesman();
        public Salesman updateSalesman(String salesmanId, Salesman salesman);
        void removeSalesman(String salesmanId);
}
