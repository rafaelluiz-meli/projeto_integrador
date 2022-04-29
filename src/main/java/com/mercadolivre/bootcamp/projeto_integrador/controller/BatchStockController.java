package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock.NewBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock.UpdateBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class BatchStockController {
    @Autowired
    private BatchStockService service;

    @PostMapping("/batchstock")
    public ResponseEntity<NewBatchStockDTO> newBatchStock(@RequestBody NewBatchStockDTO batchStockDTO){
        BatchStock batchStock = batchStockDTO.map();
        service.create(batchStock);
        NewBatchStockDTO d = NewBatchStockDTO.map(batchStock);
        return new ResponseEntity(d, HttpStatus.CREATED);
    }

    @PutMapping("/batchstock")
    public ResponseEntity<UpdateBatchStockDTO> updateBatchStock(@RequestBody UpdateBatchStockDTO updateBatchStockDTO){
        BatchStock batchStock = updateBatchStockDTO.map();
        BatchStock updatedBatchStock = service.update(batchStock);
        return ResponseEntity.ok(UpdateBatchStockDTO.map(updatedBatchStock));
    }

    @GetMapping("/batchstock/list")
    public ResponseEntity<List<NewBatchStockDTO>> listAllBatchStocks(){
        List<BatchStock> batchStockList = service.findAll();
        List<NewBatchStockDTO> result = batchStockList.stream().map(NewBatchStockDTO::map).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public ResponseEntity<List<NewBatchStockDTO>> getAllBatchStockByProductId(@RequestParam Long productId,
                                                                     @RequestParam(required = false) String orderBy)
    {
        List<BatchStock> batchStockList = service.findAllByProductId(productId);
        List<NewBatchStockDTO> responseBody = batchStockList
                .stream().map(NewBatchStockDTO::map).collect(Collectors.toList());;
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{batchStockNumber}")
    public ResponseEntity<Long> deleteBatchStock(@PathVariable(value = "batchStockNumber") Long id){
        service.remove(id);
        return ResponseEntity.ok(id);
    }
}
