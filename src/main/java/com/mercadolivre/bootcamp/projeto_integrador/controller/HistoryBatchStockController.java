package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.entity.HistoryBatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.service.HistoryBatchStockService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
@AllArgsConstructor
public class HistoryBatchStockController {

    private final HistoryBatchStockService service;

    @GetMapping("/history")
    public ResponseEntity<List<HistoryBatchStock>> getHistoryByBatchStock(@RequestParam Long id) {
        return ResponseEntity.ok().body(service.listAllHistoryByBatchStock(id));
    }

}
