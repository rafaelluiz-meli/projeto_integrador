package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class HistoryBatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "batch_stock_batch_number")
    private BatchStock batchStock;
    private Long inBoundOrderId;
    @Enumerated(EnumType.STRING)
    private HistoryType historyType;
    private Long purchaseOrderId;
    private Long productId;
    private Integer quantity;
    private LocalDateTime dateHistory;

}
