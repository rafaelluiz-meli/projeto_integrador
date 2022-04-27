package com.mercadolivre.bootcamp.projeto_integrador.dto.salesmanDTO;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSalesmanDTO {
    private String fullName;

    public Salesman map() {
        return Salesman.builder().fullName(this.fullName).build();
    }

    public static UpdateSalesmanDTO map(Salesman salesman) {
        return UpdateSalesmanDTO.builder().fullName(salesman.getFullName()).build();
    }
}
