package com.mercadolivre.bootcamp.projeto_integrador.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchNumber;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private BigDecimal price;
    private LocalDate dueDate;
    private LocalDate manufacturingDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalDateTime manufacturingTime;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
