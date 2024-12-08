package com.scb.ashrama.Service.impl;

import com.scb.ashrama.Domain.ProductionItem;
import com.scb.ashrama.Repository.ProductionItemRepository;
import com.scb.ashrama.Service.ProductionItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductionItemServiceImpl implements ProductionItemService {

    private final ProductionItemRepository productionItemRepository;

    public ProductionItemServiceImpl(ProductionItemRepository productionItemRepository) {
        this.productionItemRepository = productionItemRepository;
    }

    @Override
    public ProductionItem saveProductionItem(ProductionItem productionItem) {
        log.info("Saving production item: {}", productionItem);
        ProductionItem savedItem = productionItemRepository.save(productionItem);
        log.info("Production item saved with ID: {}", savedItem.getId());
        return savedItem;
    }

    @Override
    public ProductionItem getProductionItemById(Long id) {
        log.info("Fetching production item with ID: {}", id);
        ProductionItem productionItem = productionItemRepository.findById(id).orElse(null);
        if (productionItem != null) {
            log.info("Fetched production item: {}", productionItem);
        } else {
            log.warn("Production item with ID: {} not found", id);
        }
        return productionItem;
    }

    @Override
    public List<ProductionItem> getAllProductionItems() {
        log.info("Fetching all production items");
        List<ProductionItem> productionItems = productionItemRepository.findAll();
        log.info("Fetched {} production items", productionItems.size());
        return productionItems;
    }

    @Override
    public ProductionItem updateProductionItem(Long id, ProductionItem productionItem) {
        log.info("Updating production item with ID: {}", id);
        Optional<ProductionItem> existingItem = productionItemRepository.findById(id);
        if (existingItem.isPresent()) {
            ProductionItem itemToUpdate = existingItem.get();
            itemToUpdate.setQuantityUsed(productionItem.getQuantityUsed());
            itemToUpdate.setProduction(productionItem.getProduction());
            itemToUpdate.setItem(productionItem.getItem());
            ProductionItem updatedItem = productionItemRepository.save(itemToUpdate);
            log.info("Updated production item with ID: {}", updatedItem.getId());
            return updatedItem;
        } else {
            log.warn("Production item with ID: {} not found for update", id);
        }
        return null; // Optionally throw an exception here
    }

    @Override
    public void deleteProductionItem(Long id) {
        log.info("Request to delete production item with ID: {}", id);
        productionItemRepository.deleteById(id);
        log.info("Deleted production item with ID: {}", id);
    }
}
