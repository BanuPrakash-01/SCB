package com.scb.ashrama.Controller;

import com.scb.ashrama.Domain.ProductionItem;
import com.scb.ashrama.Service.ProductionItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-items")
@Slf4j
public class ProductionItemController {

    private final ProductionItemService productionItemService;

    public ProductionItemController(ProductionItemService productionItemService) {
        this.productionItemService = productionItemService;
    }

    @PostMapping
    public ProductionItem createProductionItem(@RequestBody ProductionItem productionItem) {
        log.info("Creating new production item: {}", productionItem);
        ProductionItem createdItem = productionItemService.saveProductionItem(productionItem);
        log.info("Production item created with ID: {}", createdItem.getId());
        return createdItem;
    }

    @GetMapping("/{id}")
    public ProductionItem getProductionItem(@PathVariable Long id) {
        log.info("Fetching production item with ID: {}", id);
        ProductionItem productionItem = productionItemService.getProductionItemById(id);
        log.info("Fetched production item: {}", productionItem);
        return productionItem;
    }

    @GetMapping
    public List<ProductionItem> getAllProductionItems() {
        log.info("Fetching all production items");
        List<ProductionItem> productionItems = productionItemService.getAllProductionItems();
        log.info("Fetched {} production items", productionItems.size());
        return productionItems;
    }

    @PutMapping("/{id}")
    public ProductionItem updateProductionItem(@PathVariable Long id, @RequestBody ProductionItem productionItem) {
        log.info("Updating production item with ID: {}", id);
        ProductionItem updatedItem = productionItemService.updateProductionItem(id, productionItem);
        log.info("Updated production item with ID: {}", updatedItem.getId());
        return updatedItem;
    }

    @DeleteMapping("/{id}")
    public void deleteProductionItem(@PathVariable Long id) {
        log.info("Request to delete production item with ID: {}", id);
        productionItemService.deleteProductionItem(id);
        log.info("Deleted production item with ID: {}", id);
    }
}
