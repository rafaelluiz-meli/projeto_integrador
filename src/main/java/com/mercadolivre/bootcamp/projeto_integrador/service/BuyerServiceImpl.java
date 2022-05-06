package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.buyer.NewBuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService{
    private final BuyerRepository buyerRepository;

    @Override
    public Buyer addBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @Override
    public List<Buyer> getAllBuyer() {
        return findAll();
    }

    @Override
    public Buyer updateBuyer(Buyer buyer) {
        Buyer updateBuyerById = findById(buyer.getBuyerId());

        updateBuyerById.setFullName(buyer.getFullName());

        return buyerRepository.save(updateBuyerById);
    }

    @Override
    public void deleteBuyer(Long buyerId) {
        Buyer foundedBuyerById = findById(buyerId);
        buyerRepository.delete(foundedBuyerById);
    }

    @Override
    public List<Buyer> findAll() {
        List<Buyer> buyerList = buyerRepository.findAll();
        if (buyerList.isEmpty()) throw new EmptyListException();
        return buyerList;
    }

    @Override
    public Buyer findById(Long buyerId) {
        return buyerRepository.findById(buyerId).orElseThrow(()
                -> new IdNotFoundException(buyerId));
    }
}
