package com.mercadolivre.bootcamp.projeto_integrador.dto.salesman;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSalesmanDTO {
    private Long id;
    private String fullName;

    public Salesman map() {
        return Salesman.builder().fullName(this.fullName).id(this.id).build();
    }

    public static UpdateSalesmanDTO map(Salesman salesman) {
        return UpdateSalesmanDTO.builder().fullName(salesman.getFullName()).id(salesman.getId()).build();
    }
}
