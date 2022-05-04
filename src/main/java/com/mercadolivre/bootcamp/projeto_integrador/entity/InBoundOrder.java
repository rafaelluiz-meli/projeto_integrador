package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class InBoundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inBoundOrderNumber;
    private LocalDateTime inBoundOrderDate;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BatchStock batchStock;
    //Todo: JoinColumn @ManyToOne
    private Long representativeId;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
}
