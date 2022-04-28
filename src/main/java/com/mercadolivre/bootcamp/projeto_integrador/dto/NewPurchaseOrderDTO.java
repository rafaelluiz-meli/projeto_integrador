package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPurchaseOrderDTO {
    private StatusOrder statusOrder;
    private Buyer buyerId;
    private LocalDate purchaseOrderDate;

    public static PurchaseOrder convert(NewPurchaseOrderDTO newPurchaseOrderDTO){
        return PurchaseOrder.builder()
                .statusOrder(newPurchaseOrderDTO.getStatusOrder())
                .buyerId(newPurchaseOrderDTO.getBuyerId())
                .purchaseOrderDate(newPurchaseOrderDTO.getPurchaseOrderDate())
                .build();
    }
}
