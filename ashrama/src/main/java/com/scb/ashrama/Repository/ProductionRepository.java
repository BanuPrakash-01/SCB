package com.scb.ashrama.Repository;

import com.scb.ashrama.Domain.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {
    // Additional query methods (if needed) can be added here
}
