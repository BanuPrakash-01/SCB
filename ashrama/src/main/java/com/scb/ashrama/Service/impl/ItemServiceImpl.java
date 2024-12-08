package com.scb.ashrama.Service.impl;

import com.scb.ashrama.Domain.Item;
import com.scb.ashrama.Exception.ResourceNotFoundException;
import com.scb.ashrama.Repository.ItemRepository;
import com.scb.ashrama.Service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item saveItem(Item item) {
        log.info("Saving new item: {}", item);
        Item savedItem = itemRepository.save(item);
        log.info("Item saved with ID: {}", savedItem.getId());
        return savedItem;
    }

    @Override
    public Item getItemById(Long id) {
        log.info("Fetching item with ID: {}", id);
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with ID: " + id));
        log.info("Item fetched successfully: {}", item);
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        log.info("Fetching all items");
        List<Item> items = itemRepository.findAll();
        log.info("Fetched {} items", items.size());
        return items;
    }

    @Override
    public Item updateItem(Long id, Item itemDetails) {
        log.info("Updating item with ID: {}", id);
        Item existingItem = getItemById(id);
        existingItem.setName(itemDetails.getName());
        existingItem.setDescription(itemDetails.getDescription());
        existingItem.setType(itemDetails.getType());
        existingItem.setUnit(itemDetails.getUnit());
        existingItem.setQuantity(itemDetails.getQuantity());
        existingItem.setUpdatedBy(itemDetails.getUpdatedBy());
        Item updatedItem = itemRepository.save(existingItem);
        log.info("Item with ID: {} updated successfully", updatedItem.getId());
        return updatedItem;
    }

    @Override
    public void deleteItem(Long id) {
        log.info("Deleting item with ID: {}", id);
        Item existingItem = getItemById(id);
        itemRepository.delete(existingItem);
        log.info("Item with ID: {} deleted successfully", id);
    }
}
