package com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order;

import com.mercadolivre.bootcamp.projeto_integrador.dto.buyer.BuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPurchaseOrderDTO {
    private BuyerDTO buyerDTO;
    private List<NewPurchaseOrderItemsDTO> purchaseOrderItemsListDTO;
}
