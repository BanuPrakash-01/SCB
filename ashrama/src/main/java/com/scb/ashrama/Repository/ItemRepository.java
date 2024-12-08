package com.scb.ashrama.Repository;

import com.scb.ashrama.Domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Additional query methods (if needed) can be added here
}
