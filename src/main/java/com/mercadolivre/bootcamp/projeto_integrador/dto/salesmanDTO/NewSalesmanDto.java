package com.mercadolivre.bootcamp.projeto_integrador.dto.salesmanDTO;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewSalesmanDto {
    private String fullName;

    public Salesman map() {
        return Salesman.builder().fullName(this.fullName).build();
    }

    public static NewSalesmanDto map(Salesman salesman) {
        return NewSalesmanDto.builder().fullName(salesman.getFullName()).build();
    }

    public static List<NewSalesmanDto> map(List<Salesman> salesmanList) {
        return salesmanList.stream().map(NewSalesmanDto::map).collect(Collectors.toList());
    }
}
