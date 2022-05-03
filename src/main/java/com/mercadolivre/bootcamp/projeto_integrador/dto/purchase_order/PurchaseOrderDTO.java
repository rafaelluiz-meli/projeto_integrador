package com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order;

import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.entity.StatusOrder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    private Long purchaseOrderNumber;
    private Long buyer_id;
    private List<PurchaseOrderItems> purchaseOrderItemsList;
    private StatusOrder statusOrder;
    private LocalDate purchaseOrderDate;
    private BigDecimal totalValue;

    public static PurchaseOrderDTO map(PurchaseOrder purchaseOrder) {
        return PurchaseOrderDTO.builder()
                .purchaseOrderNumber(purchaseOrder.getPurchaseOrderNumber())
                .buyer_id(purchaseOrder.getBuyer().getBuyerId())
                .purchaseOrderItemsList(purchaseOrder.getPurchaseOrderItemsList())
                .statusOrder(purchaseOrder.getStatusOrder())
                .purchaseOrderDate(purchaseOrder.getPurchaseOrderDate())
                .build();
    }
}
