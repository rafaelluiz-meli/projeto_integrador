package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.exception.SalesmanDoesNotExistException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.SalesmanListIsEmptyException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepository salesmanRepository;

    @Override
    public Salesman createSalesman(Salesman salesman) {
        return salesmanRepository.save(salesman);
    }

    @Override
    public Salesman findSalesmanById(Long salesmanId) {
        return salesmanRepository.findById(salesmanId).
                orElseThrow(() -> new SalesmanDoesNotExistException(salesmanId));
    }

    @Override
    public List<Salesman> listSalesman() {
        List<Salesman> allTheSalesman = salesmanRepository.findAll();
        if (allTheSalesman.isEmpty()){
            throw new SalesmanListIsEmptyException();
        }
        return  salesmanRepository.findAll();
    }

    @Override
    public void removeSalesman(Long salesmanId) {
        salesmanRepository.delete(findSalesmanById(salesmanId));
    }

    @Override
    public Salesman updateSalesman(Salesman salesman){
        Salesman updatedSalesman = findSalesmanById(salesman.getId());
        updatedSalesman.setFullName(salesman.getFullName());
        return salesmanRepository.save(updatedSalesman);
        }
}
