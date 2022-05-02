package com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.buyer.BuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItens;
import com.mercadolivre.bootcamp.projeto_integrador.entity.StatusOrder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPurchaseOrderDTO {
    private BuyerDTO buyerDTO;
    private List<PurchaseOrderItens> purchaseOrderItemsList;
    private StatusOrder statusOrder = StatusOrder.CART;
    private LocalDate purchaseOrderDate;

    public PurchaseOrder map(){
        return PurchaseOrder.builder()
                .purchaseOrderItemsList(this.purchaseOrderItemsList)
                .build();
    }
}
