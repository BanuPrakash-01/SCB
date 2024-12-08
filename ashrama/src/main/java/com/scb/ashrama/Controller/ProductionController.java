package com.scb.ashrama.Controller;

import com.scb.ashrama.Domain.Production;
import com.scb.ashrama.Service.ProductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productions")
@Slf4j
public class ProductionController {

    private final ProductionService productionService;

    @Autowired
    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping
    public ResponseEntity<Production> createProduction(@RequestBody Production production) {
        log.info("Request to create a new production: {}", production);
        Production newProduction = productionService.saveProduction(production);
        log.info("New production created with ID: {}", newProduction.getId());
        return new ResponseEntity<>(newProduction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Production> getProductionById(@PathVariable Long id) {
        log.info("Fetching production with ID: {}", id);
        Production production = productionService.getProductionById(id);
        log.info("Production fetched: {}", production);
        return ResponseEntity.ok(production);
    }

    @GetMapping
    public ResponseEntity<List<Production>> getAllProductions() {
        log.info("Fetching all productions");
        List<Production> productions = productionService.getAllProductions();
        log.info("Fetched {} productions", productions.size());
        return ResponseEntity.ok(productions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Production> updateProduction(@PathVariable Long id, @RequestBody Production productionDetails) {
        log.info("Request to update production with ID: {}", id);
        Production updatedProduction = productionService.updateProduction(id, productionDetails);
        log.info("Production with ID: {} updated successfully", id);
        return ResponseEntity.ok(updatedProduction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduction(@PathVariable Long id) {
        log.info("Request to delete production with ID: {}", id);
        productionService.deleteProduction(id);
        log.info("Production with ID: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}
