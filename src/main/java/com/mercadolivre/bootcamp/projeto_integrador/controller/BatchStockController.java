package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.UpdateBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(BatchStockController.baseUri)
public class BatchStockController {

    public static final String baseUri = "api/v1/fresh-products/batchstock";

    @Autowired
    private BatchStockService service;

    @PostMapping
    public ResponseEntity<NewBatchStockDTO> newBatchStock(@RequestBody NewBatchStockDTO batchStockDTO, UriComponentsBuilder uriBuilder){
        BatchStock batchStock = batchStockDTO.map();
        service.create(batchStock);

        URI uri = uriBuilder
                    .path(BatchStockController.baseUri.concat("/{id}"))
                .buildAndExpand(batchStock.getBatchNumber())
                .toUri();

        NewBatchStockDTO d = NewBatchStockDTO.map(batchStock);
        return ResponseEntity.created(uri).body(d);
    }

    @PutMapping
    public ResponseEntity<UpdateBatchStockDTO> updateBatchStock(@RequestBody UpdateBatchStockDTO updateBatchStockDTO){
        BatchStock batchStock = updateBatchStockDTO.map();
        BatchStock updatedBatchStock = service.update(batchStock);
        return ResponseEntity.ok(UpdateBatchStockDTO.map(updatedBatchStock));
    }

    @GetMapping
    public ResponseEntity<List<NewBatchStockDTO>> listAllBatchStocks(){
        List<BatchStock> batchStockList = service.list();
        return ResponseEntity.ok(NewBatchStockDTO.map(batchStockList));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<NewBatchStockDTO> getBatchStockById(@RequestParam(value = "batchNumber") Long id){
        BatchStock batchStock = service.findById(id);
        NewBatchStockDTO result = NewBatchStockDTO.map(batchStock);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{batchStockNumber}")
    public ResponseEntity<Long> deleteBatchStock(@PathVariable(value = "batchStockNumber") Long id){
        service.remove(id);
        return ResponseEntity.ok(id);
    }
}
