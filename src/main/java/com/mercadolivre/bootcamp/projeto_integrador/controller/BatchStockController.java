package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock.*;
import com.mercadolivre.bootcamp.projeto_integrador.dto.section.SectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse.ResponseWarehouseDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fresh-products")
@AllArgsConstructor
public class BatchStockController {
    @Autowired
    private final BatchStockService batchStockService;

    // START DUE-DATE ENDPOINTS

    /**
     * @param numberOfDays Number of days until product is due
     * @param sectionId Id of the section to be evaluated
     * @return 200 OK
     */
    @GetMapping("/batchstock/due-date/")
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
    @GetMapping("/batchstock/due-date/list")
    public ResponseEntity<List<ResponseBatchStockDTO>> getByDueDateAndCategory(@RequestParam int numberOfDays, @RequestParam Category category) {
        List<BatchStock> batchStockList = batchStockService.findAllByDueDateAndProductCategory(numberOfDays, category);
        List<ResponseBatchStockDTO> responseBatchStockDTOList = ResponseBatchStockDTO.map(batchStockList);
        return ResponseEntity.ok().body(responseBatchStockDTOList);
    }
    // END DUE-DATE ENDPOINTS

    @PostMapping("/batchstock")
    public ResponseEntity<NewBatchStockDTO> newBatchStock(@RequestBody NewBatchStockDTO batchStockDTO){
        BatchStock createdBatchStock = batchStockService.create(batchStockDTO.map());
        NewBatchStockDTO responseCreatedBatchStock = NewBatchStockDTO.map(createdBatchStock);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCreatedBatchStock);
    }

    @PutMapping("/batchstock")
    public ResponseEntity<UpdateBatchStockDTO> updateBatchStock(@RequestBody UpdateBatchStockDTO updateBatchStockDTO){
        BatchStock batchStock = updateBatchStockDTO.map();
        BatchStock updatedBatchStock = batchStockService.update(batchStock);
        return ResponseEntity.ok(UpdateBatchStockDTO.map(updatedBatchStock));
    }

    @GetMapping("/batchstock/list")
    public ResponseEntity<List<NewBatchStockDTO>> listAllBatchStocks(){
        List<BatchStock> batchStockList = batchStockService.findAll();
        List<NewBatchStockDTO> result = batchStockList.stream().map(NewBatchStockDTO::map).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    //Section e Warehouse do productId
    //Lista de section a partir do productId (Ok)
    //Criar BatchstockWithSectionDTO
    //Lista batchstock a partir da Section e productId
    //Atribuir a lista de BatchstockDTO para a lista BatchstockWithSectionDTO
    public List<Section> getAllBatchStockByProductId(@RequestParam Long productId,
                                                                              @RequestParam(required = false,
                                                                                      defaultValue = "L")
                                                                              String orderBy)
    {
        List<Section> batchStockList = batchStockService.findSectionListByProductId(productId);
//        batchStockList = batchStockService.orderBatchStockList(orderBy, batchStockList);
//        List<BatchStockDTO> convertKleber = batchStockList
//                .stream().map(BatchStockDTO::convert).collect(Collectors.toList());

//        BatchStockWithSectionDTO responseBody = BatchStockWithSectionDTO.builder().sectionDTO(SectionDTO.builder().sectionId(
//                batchStockList.get(0).getSection()
//        ).build())

        return batchStockList;
    }

    @GetMapping("/warehouse")
    public ResponseEntity<ResponseWarehouseDTO> getProductInAllWarehouse(@RequestParam Long productId) {
        ResponseWarehouseDTO result = ResponseWarehouseDTO.convert(productId, batchStockService.groupByWarehouse(productId));

        return ResponseEntity.status(HttpStatus.OK).body(result);
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
