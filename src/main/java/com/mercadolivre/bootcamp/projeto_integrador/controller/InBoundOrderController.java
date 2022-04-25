package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.service.InBoundOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inBoudnOrder")
@AllArgsConstructor
public class InBoundOrderController {
    private final InBoundOrderService inBoundOrderService;

}
