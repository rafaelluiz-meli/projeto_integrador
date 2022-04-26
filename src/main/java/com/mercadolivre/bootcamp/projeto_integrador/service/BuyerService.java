package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewBuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;

import java.util.List;

public interface BuyerService {
    Buyer addBuyer(NewBuyerDTO newBuyerDTO);
    List<Buyer> getAllBuyer();
    Buyer updateBuyer(String buyerId);
    void deleteBuyer(String buyerId);
}
