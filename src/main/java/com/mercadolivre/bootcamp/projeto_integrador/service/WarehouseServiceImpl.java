package com.mercadolivre.bootcamp.projeto_integrador.service;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * This class is the service implementation of warehouse entity.
 */

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;


    /**
     * Find a warehouse by Id.
     * @param warehouseId
     * @return a warehouse.
     * @exception IdNotFoundException if id doesn't found.
     */
    @Override
    public Warehouse findById(Long warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new IdNotFoundException(warehouseId);
        }else{
            return wh.get();
        }
    }

    /**
     * Get from database all warehouses.
     * @return a warehouse list.
     * @exception EmptyListException if warehouse list is empty.
     */
    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        if(warehouseList.isEmpty()) throw new EmptyListException();
        return warehouseList;
    }

    /**
     * Verify that warehouse is valid.
     * @return true if warehouse exists or false if not exists.
     * @exception IdNotFoundException if warehouse id is not exists.
     */
    @Override
    public boolean isValidWarehouse(Long warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new IdNotFoundException(warehouseId);
        }else{
            return true;
        }
    }

    /**
     * Created warehouse
     * @param wh
     * @return warehouse
     */
    @Override
    public Warehouse save(Warehouse wh) {
        return warehouseRepository.save(wh);
    }


    /**
     * Check if warehouse id exists with {@link #findById(Long) findById} method. <br>
     * If exists then remove the warehouse.
     * @param warehouseId
     * @return void.
     */
    @Override
    public void delete(Long warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        warehouseRepository.delete(wh.get());
    }

}
