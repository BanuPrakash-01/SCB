package com.scb.ashrama.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_description")
    private String description;

    @Column(name = "item_type")
    private String type;

    @Column(name = "unit")
    private String unit;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_categoryId")
    private Category category;

    @Column(name = "receivedby")
    private String receivedBy;

    @Column(name = "receiveddate", updatable = false)
    private LocalDateTime receivedDate;

    @Column(name = "updatedby")
    private String updatedBy;

    @Column(name = "updateddate")
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        receivedDate = LocalDateTime.now(); // Set received date on creation
        updatedDate = LocalDateTime.now(); // Set updated date on creation
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now(); // Update updated date on modification
    }
}
