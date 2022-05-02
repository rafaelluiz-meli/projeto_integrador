package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock.NewBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock.ResponseBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock.UpdateBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BatchStockController.baseUri)
@AllArgsConstructor
public class BatchStockController {

    public static final String baseUri = "/api/v1/fresh-products/batchstock";
    private final BatchStockService batchStockService;

    // START DUE-DATE ENDPOINTS

    /**
     * @param numberOfDays Number of days until product is due
     * @param sectionId Id of the section to be evaluated
     * @return 200 OK
     */
    @GetMapping("/due-date/")
    public ResponseEntity<List<ResponseBatchStockDTO>> getByDueDateAndSectionId(@RequestParam int numberOfDays, @RequestParam Long sectionId) {
        List<BatchStock> batchStockList = batchStockService.findAllBySectionIdAndDueDate(numberOfDays, sectionId);
        List<ResponseBatchStockDTO> responseBatchStockDTOList = ResponseBatchStockDTO.map(batchStockList);
        return ResponseEntity.ok().body(responseBatchStockDTOList);
    }

    /**
     * @param numberOfDays Number of days until product is due
     * @param category Product category FROZEN_FOOD, FRESH or REFRIGERATED
     * @return 200 OK
     */
    @GetMapping("/due-date/list")
    public ResponseEntity<List<ResponseBatchStockDTO>> getByDueDateAndCategory(@RequestParam int numberOfDays, @RequestParam Category category) {
        List<BatchStock> batchStockList = batchStockService.findAllByDueDateAndProductCategory(numberOfDays, category);
        List<ResponseBatchStockDTO> responseBatchStockDTOList = ResponseBatchStockDTO.map(batchStockList);
        return ResponseEntity.ok().body(responseBatchStockDTOList);
    }
    // END DUE-DATE ENDPOINTS

    @PostMapping
    public ResponseEntity<NewBatchStockDTO> newBatchStock(@RequestBody NewBatchStockDTO batchStockDTO){
        BatchStock createdBatchStock = batchStockService.create(batchStockDTO.map());
        NewBatchStockDTO responseCreatedBatchStock = NewBatchStockDTO.map(createdBatchStock);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCreatedBatchStock);
    }

    @PutMapping
    public ResponseEntity<UpdateBatchStockDTO> updateBatchStock(@RequestBody UpdateBatchStockDTO updateBatchStockDTO){
        BatchStock batchStock = updateBatchStockDTO.map();
        BatchStock updatedBatchStock = batchStockService.update(batchStock);
        return ResponseEntity.ok(UpdateBatchStockDTO.map(updatedBatchStock));
    }

    @GetMapping
    public ResponseEntity<List<NewBatchStockDTO>> listAllBatchStocks(){
        List<BatchStock> batchStockList = batchStockService.findAll();
        List<NewBatchStockDTO> result = batchStockList.stream().map(NewBatchStockDTO::map).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("{batchNumber}")
    public ResponseEntity<NewBatchStockDTO> getBatchStockById(@RequestParam(value = "batchNumber") Long id){
        BatchStock batchStock = batchStockService.findById(id);
        NewBatchStockDTO result = NewBatchStockDTO.map(batchStock);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{batchStockNumber}")
    public ResponseEntity<Long> deleteBatchStock(@PathVariable(value = "batchStockNumber") Long id){
        batchStockService.remove(id);
        return ResponseEntity.ok(id);
    }
}
