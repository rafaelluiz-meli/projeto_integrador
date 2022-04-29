package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.InboundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.*;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order.RepresentativeNotAssociatedWithSectionException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order.SectionDoesNotHaveEnoughCapacityException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order.SectionNotAppropriateForProductException;
import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class InBoundOrderFactory {
    private final InBoundOrderService inBoundOrderService;
    private final SectionService sectionService;
    private final WarehouseService warehouseService;
    private final RepresentativeService representativeService;
    private final BatchStockService batchStockService;
    private final ProductService productService;

    public InBoundOrder createInBoundOrder(NewInBoundOrderDTO newInBoundOrderDTO) {
        InboundOrderDTO inboundOrderDTO = this.createInboundOrder(newInBoundOrderDTO);

        this.validateInboundOrderValid(newInBoundOrderDTO);
        this.validateStockValid(inboundOrderDTO.getBatchStock(), inboundOrderDTO.getSection().getSectionId());

        return inBoundOrderService.addInBoundOrder(inboundOrderDTO);
    }

    public InBoundOrder updateInboundOrder(NewInBoundOrderDTO newInBoundOrderDTO) {
        // TODO: 28/04/22 CRIAR MÉTODO UPDATE
        Long inboundOrderId = newInBoundOrderDTO.getOrderNumber();
        inBoundOrderService.findById(inboundOrderId);

        return createInBoundOrder(newInBoundOrderDTO);
    }


    private InboundOrderDTO createInboundOrder(NewInBoundOrderDTO newInBoundOrderDTO) {
        BatchStock batchStock = newInBoundOrderDTO.getBatchStock().map();
        batchStock.setProduct(productService.findByProductId(newInBoundOrderDTO.getBatchStock().getProductId()));

        Section section = sectionService.getSectionById(newInBoundOrderDTO.getSection().getSectionId());

        return InboundOrderDTO.builder()
                .orderNumber(newInBoundOrderDTO.getOrderNumber())
                .section(section)
                .batchStock(batchStock)
                .representativeId(newInBoundOrderDTO.getRepresentativeId())
                .build();
    }

    private void validateInboundOrderValid(NewInBoundOrderDTO newInBoundOrderDTO) {
        /* Faz validação de sectionId, warehouseId e representativeId em seus respectivos
           services. Caso um deles não seja valido, a inboundOrder é inválida e o método retorna false.
         */

        Long sectionId = newInBoundOrderDTO.getSection().getSectionId();
        Long warehouseId = newInBoundOrderDTO.getSection().getWarehouseId();
        Long representativeId = newInBoundOrderDTO.getRepresentativeId();

        boolean isSectionValid = sectionService.isSectionValid(sectionId);
        boolean isWarehouseValid = warehouseService.isValidWarehouse(warehouseId);
        boolean isRepresentativeAssociatedWithSection = representativeService
                .isRepresentativeAssociatedWithSection(representativeId, sectionId);

        if(!isSectionValid) throw new IdNotFoundException(sectionId);
        if(!isWarehouseValid) throw new IdNotFoundException(warehouseId);
        if(!isRepresentativeAssociatedWithSection) throw new RepresentativeNotAssociatedWithSectionException(representativeId, sectionId);
    }

    private void validateStockValid(BatchStock batchStock, Long sectionId) {
        Category productCategory = batchStock.getProduct().getCategory();
        BigDecimal totalVolume = batchStockService.calculateTotalVolume(batchStock);

        boolean isSectionAppropriateForProductCategory = sectionService.sectionCorrespondsProductType(sectionId, productCategory);
        boolean sectionHasEnoughCapacity = sectionService.availableSectionCapacity(totalVolume, sectionId);

        if (!isSectionAppropriateForProductCategory) throw new SectionNotAppropriateForProductException(sectionId, productCategory);
        if (!sectionHasEnoughCapacity) throw new SectionDoesNotHaveEnoughCapacityException(sectionId, totalVolume);
    }
}
