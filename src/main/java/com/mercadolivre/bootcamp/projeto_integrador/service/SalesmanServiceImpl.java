package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSalesmanDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesmanServiceImpl implements SalesmanService {
    @Autowired
    private SalesmanRepository salesmanRepository;

    @Override
    public Salesman createSalesman(NewSalesmanDto newSalesmanDto) {
        Salesman salesman = Salesman.builder()
                .fullName(newSalesmanDto.getFullName())
                .build();
        return salesmanRepository.save(salesman);
    }

    @Override
    public Salesman findSalesman(String salesmanId) {
        // INSERIR EXCECAO
        return salesmanRepository.findById(salesmanId).orElse(new Salesman());
    }

    @Override
    public List<Salesman> listSalesman() {
        return salesmanRepository.findAll();
    }

    @Override
    public void removeSalesman(String salesmanId) {
        // VERIFICAR SE ESTÁ CORRETO
        salesmanRepository.delete(findSalesman(salesmanId));
    }

    public Salesman updateSalesman(String salesmanId, Salesman salesman){
        Optional<Salesman> getSalesmanId = salesmanRepository.findById(salesmanId);
        Salesman newSalesman;

        if (getSalesmanId.isPresent()) {
            newSalesman = new Salesman();
            newSalesman.setFullName(salesman.getFullName());
            return salesmanRepository.save(newSalesman);
        }
        // FAZER TRATATIVA DE ERRO
        return new Salesman();
    }
}
