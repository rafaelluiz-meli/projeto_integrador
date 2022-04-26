package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewInBoundOrderDTO {
    private LocalDate inBoundOrderDate;
    private String representativeId;

    public static InBoundOrder convert(NewInBoundOrderDTO newInBoundOrderDTO){
        return InBoundOrder.builder()
                .inBoundOrderDate(newInBoundOrderDTO.getInBoundOrderDate())
                .representativeId(newInBoundOrderDTO.getRepresentativeId()).build();
    }
}
