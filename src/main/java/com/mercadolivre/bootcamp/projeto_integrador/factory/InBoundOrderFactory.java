package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.InboundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.*;
import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.RequestInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 *  This class represents a Inbound order factory
 */
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
     * @param requestInBoundOrderDTO RequestBody received at controller level
     * @return The entity created @ database level
     */
    public InBoundOrder createInBoundOrder(RequestInBoundOrderDTO requestInBoundOrderDTO) {
        InboundOrderDTO inboundOrderDTO = this.createInboundOrderDTO(requestInBoundOrderDTO);

        this.validateInboundOrder(requestInBoundOrderDTO);
        this.validateBatchStock(inboundOrderDTO.getBatchStock(), inboundOrderDTO.getSection().getSectionId());

        return inBoundOrderService.addInBoundOrder(inboundOrderDTO);
    }

    /**
     *
     * @param id Inbound order id
     * @param requestInBoundOrderDTO RequestBody received at controller level
     * @return The entity updated @ database level
     */
    public InBoundOrder updateInboundOrder(Long id, RequestInBoundOrderDTO requestInBoundOrderDTO) {

        // Create appropriate inboundOrderDTO
        InboundOrderDTO inboundOrderDTO = this.updateInboundOrderDTO(requestInBoundOrderDTO);
        inboundOrderDTO.setOrderNumber(requestInBoundOrderDTO.getOrderNumber());

        // Run validations
        this.validateInboundOrder(requestInBoundOrderDTO);
        this.validateBatchStock(inboundOrderDTO.getBatchStock(), inboundOrderDTO.getSection().getSectionId());

        inBoundOrderService.findById(id);

        return inBoundOrderService.addInBoundOrder(inboundOrderDTO);
    }

    /**
     *
     * @param requestInBoundOrderDTO
     * @return InboundOrderDTO
     * Runs the conversions needed to create an entity composed of a
     * BatchStock, Section and Product. It's needed to simplify the requestbody at controller level
     */
    private InboundOrderDTO createInboundOrderDTO(RequestInBoundOrderDTO requestInBoundOrderDTO) {
        BatchStock batchStock = requestInBoundOrderDTO.getBatchStock().map();
        batchStock.setProduct(productService.findByProductId(requestInBoundOrderDTO.getBatchStock().getProductId()));

        Section section = sectionService.getSectionById(requestInBoundOrderDTO.getSection().getSectionId());
        batchStock.setSection(section);

        return InboundOrderDTO.builder()
                .section(section)
                .batchStock(batchStock)
                .representativeId(requestInBoundOrderDTO.getRepresentativeId())
                .build();
    }


    /**
     *
     * @param requestInBoundOrderDTO RequestBody received at controller level
     * @return InboundOrderDTO
     * Runs the conversions needed to create an entity composed of a BatchStock, Section and Product.
     * It will only update the allowed parameters of a BatchStock.
     */
    private InboundOrderDTO updateInboundOrderDTO(RequestInBoundOrderDTO requestInBoundOrderDTO) {

        BatchStock currentBatchStock = inBoundOrderService.findById(requestInBoundOrderDTO.getOrderNumber()).getBatchStock();
        BatchStock batchStock = requestInBoundOrderDTO.getBatchStock().map(currentBatchStock);
        Section section = sectionService.getSectionById(requestInBoundOrderDTO.getSection().getSectionId());

        return InboundOrderDTO.builder()
                .orderNumber(requestInBoundOrderDTO.getOrderNumber())
                .section(section)
                .batchStock(batchStock)
                .representativeId(requestInBoundOrderDTO.getRepresentativeId())
                .build();
    }

    /**
     *
     * @param requestInBoundOrderDTO Inbound Order to be validated
     * Validate sectiond ID, warehouseId and representativeId in the respectives services.
     * If some of them are not valid, the inbound order is invalidate and the method returns false.
     */
    private void validateInboundOrder(RequestInBoundOrderDTO requestInBoundOrderDTO) {
        Long sectionId = requestInBoundOrderDTO.getSection().getSectionId();
        Long warehouseId = requestInBoundOrderDTO.getSection().getWarehouseId();
        Long representativeId = requestInBoundOrderDTO.getRepresentativeId();

        sectionService.isSectionValid(sectionId);
        warehouseService.isValidWarehouse(warehouseId);
        representativeService.isRepresentativeAssociatedWithSection(representativeId, sectionId);
    }

    /**
     *
     * @param batchStock
     * @param sectionId
     */
    private void validateBatchStock(BatchStock batchStock, Long sectionId) {
        Category productCategory = batchStock.getProduct().getCategory();
        BigDecimal totalVolume = batchStockService.calculateTotalVolume(batchStock);

        sectionService.sectionCorrespondsProductType(sectionId, productCategory);
        sectionService.availableSectionCapacity(totalVolume, sectionId);
    }
}
