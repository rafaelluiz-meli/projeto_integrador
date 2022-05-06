package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * This class is the service implementation of salesman entity.
 */
@AllArgsConstructor
@Service
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepository salesmanRepository;


    /**
     * Is persistence in database by repository
     * @param salesman
     * @return a salesman object
     */
    @Override
    public Salesman createSalesman(Salesman salesman) {
        return salesmanRepository.save(salesman);
    }

    /**
     * Get section list filtered by salesmanId
     * @param salesmanId
     * @return a salesman object
     * @exception IdNotFoundException if salesman id not exists.
     */
    @Override
    public Salesman findSalesmanById(Long salesmanId) {
        return salesmanRepository.findById(salesmanId).
                orElseThrow(() -> new IdNotFoundException(salesmanId));
    }

    /**
     * Get salesman list
     * @return a salesman list
     * @exception EmptyListException if salesman list is empty.
     */
    @Override
    public List<Salesman> listSalesman() {
        List<Salesman> salesmanList = salesmanRepository.findAll();
        if (salesmanList.isEmpty()) throw new EmptyListException();
        return salesmanList;
    }

    /**
     * Check if SALESMAN id exists with {@link #findSalesmanById(Long)}  findSalesmanById} method. <br>
     * If exists then remove the section.
     * @param salesmanId
     * @return void.
     */
    @Override
    public void removeSalesman(Long salesmanId) {
        salesmanRepository.delete(findSalesmanById(salesmanId));
    }

    /**
     * Check if salesman id exists with {@link #findSalesmanById(Long)}  findSalesmanById} method. <br>
     * If exists then update salesman attributes.
     * @param salesman
     * @return a updated salesman
     *
     */
    @Override
    public Salesman updateSalesman(Salesman salesman) {
        Salesman updatedSalesman = findSalesmanById(salesman.getId());
        updatedSalesman.setFullName(salesman.getFullName());
        return salesmanRepository.save(updatedSalesman);
    }
}
