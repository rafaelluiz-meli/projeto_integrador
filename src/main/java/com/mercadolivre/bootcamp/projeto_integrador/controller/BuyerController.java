package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.buyerDTO.NewBuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.service.BuyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fresh-products")
public class BuyerController {
    private final BuyerService buyerService;

    @PostMapping("/buyer")
    public ResponseEntity<Buyer> createBuyer(@RequestBody NewBuyerDTO newBuyerDTO) {
        Buyer buyer = buyerService.addBuyer(newBuyerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(buyer);
    }

    @GetMapping("/buyer/list")
    public ResponseEntity<List<NewBuyerDTO>> listAllBuyers() {
        List<Buyer> buyerList = buyerService.findAll();
        return ResponseEntity.ok(NewBuyerDTO.map(buyerList));
    }

    @GetMapping("/buyer")
    public ResponseEntity<NewBuyerDTO> listAllBuyerById(@RequestParam Long buyerId) {
        Buyer buyerById = buyerService.findById(buyerId);
        return ResponseEntity.ok(NewBuyerDTO.map(buyerById));
    }
}