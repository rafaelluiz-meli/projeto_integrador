package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewBuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.exception.buyerExceptions.BuyerIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService{
    private final BuyerRepository buyerRepository;

    @Override
    public Buyer addBuyer(NewBuyerDTO newBuyerDTO) {
        Buyer buyer = Buyer.builder()
                .fullName(newBuyerDTO.getFullName())
                .build();

        return buyerRepository.save(buyer);
    }

    @Override
    public List<Buyer> getAllBuyer() {
        return buyerRepository.findAll();
    }

    @Override
    public Buyer updateBuyer(String buyerId) {
        Buyer foundedBuyerById = buyerRepository.findById(buyerId).orElseThrow(()
                -> new BuyerIdNotFoundException(buyerId));
        return foundedBuyerById;
    }

    @Override
    public void deleteBuyer(String buyerId) {
        Buyer foundedBuyerById = buyerRepository.findById(buyerId).orElseThrow(()
                -> new BuyerIdNotFoundException(buyerId));
        buyerRepository.delete(foundedBuyerById);
    }
}
