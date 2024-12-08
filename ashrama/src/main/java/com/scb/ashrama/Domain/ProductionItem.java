package com.scb.ashrama.Domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "production_item")
public class ProductionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "production_item_id")
    private Long id;

    @Column(name = "quantity_used")
    private Double quantityUsed;

    @ManyToOne
    @JoinColumn(name = "production_id", nullable = false)
    private Production production;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}
