package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {

    List<BatchStock> findAllByProduct_Id(Long productId);
    List<BatchStock> findByDueDateIsLessThanEqualAndSection_SectionId(LocalDate dueDate, Long sectionId);
    List<BatchStock> findByDueDateLessThanEqualAndProduct_Category(LocalDate dueDate, @Nullable Category category);
    List<BatchStock> findAllByDueDate(LocalDate dueDate);
    List<BatchStock> findAllByProduct_IdAndAndDueDate(Long productId, LocalDate dueDate);
    @Query("select b from BatchStock b where b.dueDate >= ?1")
    List<BatchStock> findByDueDateIsGreaterThanEqual(LocalDate dueDate);
    @Query("select w.warehouseId, sum(bs.currentQuantity)\n" +
            "from Warehouse w\n" +
            "inner join Section s on w.warehouseId = s.warehouseId\n" +
            "inner join BatchStock bs on s.sectionId = bs.section.sectionId\n" +
            "inner join Product p on bs.product.id = p.id\n" +
            "where p.id = ?1\n" +
            "group by w.warehouseId")
    List findProductInAllWarehouse(Long productId);
}
