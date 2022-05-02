package com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order;

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
    private Long buyer_id;
    private List<PurchaseOrderItems> purchaseOrderItemsList;
    private StatusOrder statusOrder;
    private LocalDate purchaseOrderDate;
    private BigDecimal totalValue;
}
