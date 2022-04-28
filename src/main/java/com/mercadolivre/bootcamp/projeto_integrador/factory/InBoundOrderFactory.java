package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.SectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order.InvalidInboundOrderException;
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

    public InBoundOrder createInBoundOrder(NewInBoundOrderDTO newInBoundOrderDTO) {
        SectionDTO inboundOrderSection = newInBoundOrderDTO.getSection();
        boolean isInboundOrderValid = this.isInboundOrderValid(newInBoundOrderDTO);
        boolean isBatchStockValid = this.isBatchStockValid(newInBoundOrderDTO.getBatchStock().map(), inboundOrderSection.getSectionId());

        if(!isInboundOrderValid || !isBatchStockValid) {
            throw new InvalidInboundOrderException();
        }
        
        return inBoundOrderService.addInBoundOrder(newInBoundOrderDTO);
    }

    private boolean isInboundOrderValid(NewInBoundOrderDTO newInBoundOrderDTO) {
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

        return isSectionValid && isWarehouseValid && isRepresentativeAssociatedWithSection;
    }

    private boolean isBatchStockValid(BatchStock batchStock, Long sectionId) {
        Category productCategory = batchStock.getProduct().getCategory();
        BigDecimal totalVolume = batchStockService.calculateTotalVolume(batchStock);

        boolean isSectionAppropriateForProductCategory = sectionService.sectionCorrespondsProductType(sectionId, productCategory);
        boolean sectionHasEnoughCapacity = sectionService.availableSectionCapacity(totalVolume, sectionId);

        return isSectionAppropriateForProductCategory && sectionHasEnoughCapacity;
    }

    public InBoundOrder updateInboundOrder(NewInBoundOrderDTO newInBoundOrderDTO) {
        // TODO: 28/04/22 CRIAR MÉTODO UPDATE
        Long inboundOrderId = newInBoundOrderDTO.getOrderNumber();
        inBoundOrderService.findById(inboundOrderId);
        return null;
    }
}
