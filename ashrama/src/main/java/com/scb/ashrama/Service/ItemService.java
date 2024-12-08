package com.scb.ashrama.Service;

import com.scb.ashrama.Domain.Item;

import java.util.List;

public interface ItemService {
    Item saveItem(Item item);
    Item getItemById(Long id);
    List<Item> getAllItems();
    Item updateItem(Long id, Item item);
    void deleteItem(Long id);
}
