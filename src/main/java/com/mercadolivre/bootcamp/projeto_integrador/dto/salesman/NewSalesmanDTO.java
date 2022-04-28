package com.mercadolivre.bootcamp.projeto_integrador.dto.salesman;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewSalesmanDTO {
    private String fullName;

    public Salesman map() {
        return Salesman.builder().fullName(this.fullName).build();
    }

    public static NewSalesmanDTO map(Salesman salesman) {
        return NewSalesmanDTO.builder().fullName(salesman.getFullName()).build();
    }

    public static List<NewSalesmanDTO> map(List<Salesman> salesmanList) {
        return salesmanList.stream().map(NewSalesmanDTO::map).collect(Collectors.toList());
    }
}
