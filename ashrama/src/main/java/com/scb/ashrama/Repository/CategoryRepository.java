package com.scb.ashrama.Repository;

import com.scb.ashrama.Domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Additional query methods (if needed) can be added here
}
