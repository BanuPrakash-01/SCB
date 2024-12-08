package com.scb.ashrama.Controller;

import com.scb.ashrama.Domain.Item;
import com.scb.ashrama.Service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    // Create a new item
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        log.info("Received request to create item: {}", item);
        Item createdItem = itemService.saveItem(item);
        log.info("Item created successfully with ID: {}", createdItem.getId());
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    // Get all items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        log.info("Received request to fetch all items");
        List<Item> items = itemService.getAllItems();
        log.info("Fetched {} items", items.size());
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Get item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        log.info("Received request to fetch item with ID: {}", id);
        Item item = itemService.getItemById(id);
        log.info("Item with ID: {} fetched successfully", id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    // Update an item
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        log.info("Received request to update item with ID: {}", id);
        Item updatedItem = itemService.updateItem(id, itemDetails);
        log.info("Item with ID: {} updated successfully", id);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // Delete an item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        log.info("Received request to delete item with ID: {}", id);
        itemService.deleteItem(id);
        log.info("Item with ID: {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }
}
