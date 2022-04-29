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

    /**
     *
     * @param newInBoundOrderDTO RequestBody received at controller level
     * @return The entity created @ database level
     */
    public InBoundOrder createInBoundOrder(NewInBoundOrderDTO newInBoundOrderDTO) {
        InboundOrderDTO inboundOrderDTO = this.createInboundOrderDTO(newInBoundOrderDTO);

        this.validateInboundOrder(newInBoundOrderDTO);
        this.validateBatchStock(inboundOrderDTO.getBatchStock(), inboundOrderDTO.getSection().getSectionId());

        return inBoundOrderService.addInBoundOrder(inboundOrderDTO);
    }

    /**
     *
     * @param newInBoundOrderDTO RequestBody received at controller level
     * @return The entity updated @ database level
     */
    public InBoundOrder updateInboundOrder(Long id, NewInBoundOrderDTO newInBoundOrderDTO) {

        // Create appropriate inboundOrderDTO
        InboundOrderDTO inboundOrderDTO = this.updateInboundOrderDTO(newInBoundOrderDTO);
        inboundOrderDTO.setOrderNumber(newInBoundOrderDTO.getOrderNumber());

        // Run validations
        this.validateInboundOrder(newInBoundOrderDTO);
        this.validateBatchStock(inboundOrderDTO.getBatchStock(), inboundOrderDTO.getSection().getSectionId());

        inBoundOrderService.findById(id);

        return inBoundOrderService.addInBoundOrder(inboundOrderDTO);
    }

    /**
     *
     * @param newInBoundOrderDTO
     * @return InboundOrderDTO
     * Runs the conversions needed to create an entity composed of a
     * BatchStock, Section and Product. It's needed to simplify the requestbody at controller level
     */
    private InboundOrderDTO createInboundOrderDTO(NewInBoundOrderDTO newInBoundOrderDTO) {
        BatchStock batchStock = newInBoundOrderDTO.getBatchStock().map();
        batchStock.setProduct(productService.findByProductId(newInBoundOrderDTO.getBatchStock().getProductId()));

        Section section = sectionService.getSectionById(newInBoundOrderDTO.getSection().getSectionId());

        return InboundOrderDTO.builder()
                .section(section)
                .batchStock(batchStock)
                .representativeId(newInBoundOrderDTO.getRepresentativeId())
                .build();
    }


    /**
     *
     * @param newInBoundOrderDTO RequestBody received at controller level
     * @return InboundOrderDTO
     * Runs the conversions needed to create an entity composed of a BatchStock, Section and Product.
     * It will only update the allowed parameters of a BatchStock.
     */
    private InboundOrderDTO updateInboundOrderDTO(NewInBoundOrderDTO newInBoundOrderDTO) {

        BatchStock currentBatchStock = inBoundOrderService.findById(newInBoundOrderDTO.getOrderNumber()).getBatchStock();
        BatchStock batchStock = newInBoundOrderDTO.getBatchStock().map(currentBatchStock);
        Section section = sectionService.getSectionById(newInBoundOrderDTO.getSection().getSectionId());

        return InboundOrderDTO.builder()
                .orderNumber(newInBoundOrderDTO.getOrderNumber())
                .section(section)
                .batchStock(batchStock)
                .representativeId(newInBoundOrderDTO.getRepresentativeId())
                .build();
    }

    private void validateInboundOrder(NewInBoundOrderDTO newInBoundOrderDTO) {
        /* Faz validação de sectionId, warehouseId e representativeId em seus respectivos
           services. Caso um deles não seja valido, a inboundOrder é inválida e o método retorna false.
         */

        Long sectionId = newInBoundOrderDTO.getSection().getSectionId();
        Long warehouseId = newInBoundOrderDTO.getSection().getWarehouseId();
        Long representativeId = newInBoundOrderDTO.getRepresentativeId();

        boolean isSectionValid = sectionService.isSectionValid(sectionId);
        warehouseService.isValidWarehouse(warehouseId);
        boolean isRepresentativeAssociatedWithSection = representativeService
                .isRepresentativeAssociatedWithSection(representativeId, sectionId);

        if(!isSectionValid) throw new IdNotFoundException(sectionId);
        if(!isRepresentativeAssociatedWithSection) throw new RepresentativeNotAssociatedWithSectionException(representativeId, sectionId);
    }

    private void validateBatchStock(BatchStock batchStock, Long sectionId) {
        Category productCategory = batchStock.getProduct().getCategory();
        BigDecimal totalVolume = batchStockService.calculateTotalVolume(batchStock);

        boolean isSectionAppropriateForProductCategory = sectionService.sectionCorrespondsProductType(sectionId, productCategory);
        boolean sectionHasEnoughCapacity = sectionService.availableSectionCapacity(totalVolume, sectionId);

        if (!isSectionAppropriateForProductCategory) throw new SectionNotAppropriateForProductException(sectionId, productCategory);
        if (!sectionHasEnoughCapacity) throw new SectionDoesNotHaveEnoughCapacityException(sectionId, totalVolume);
    }
}
