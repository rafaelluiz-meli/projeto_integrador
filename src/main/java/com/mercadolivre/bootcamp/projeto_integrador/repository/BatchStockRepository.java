package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse.WarehouseDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {

    List<BatchStock> findAllByProduct_Id(Long productId);
    List<BatchStock> findByDueDateIsLessThanEqualAndSection_SectionId(LocalDate dueDate, Long sectionId);
    List<BatchStock> findByDueDateLessThanEqualAndProduct_Category(LocalDate dueDate, @Nullable Category category);
    List<BatchStock> findAllByDueDate(LocalDate dueDate);
    List<BatchStock> findAllByProduct_IdAndAndDueDate(Long productId, LocalDate dueDate);
    @Query("select b from BatchStock b where b.dueDate >= ?1")
    List<BatchStock> findByDueDateIsGreaterThanEqual(LocalDate dueDate);
    @Query("select new com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse.WarehouseDTO(w.warehouseId, sum(bs.currentQuantity))\n" +
            "from Warehouse w\n" +
            "inner join Section s on w.warehouseId = s.warehouseId\n" +
            "inner join BatchStock bs on s.sectionId = bs.section.sectionId\n" +
            "inner join Product p on bs.product.id = p.id\n" +
            "where p.id = ?1\n" +
            "group by w.warehouseId")
    List<WarehouseDTO> findProductInAllWarehouse(Long productId);

    List<BatchStock> findBySection_SectionIdAndProduct_Id(Long sectionId, Long id);


}
