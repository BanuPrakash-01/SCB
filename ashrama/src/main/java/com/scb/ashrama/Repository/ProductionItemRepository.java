package com.scb.ashrama.Repository;

import com.scb.ashrama.Domain.ProductionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionItemRepository extends JpaRepository<ProductionItem, Long> {
    // Additional query methods (if needed) can be added here
}
