package com.scb.ashrama.Service;

import com.scb.ashrama.Domain.ProductionItem;

import java.util.List;

public interface ProductionItemService {
    ProductionItem saveProductionItem(ProductionItem productionItem);
    ProductionItem getProductionItemById(Long id);
    List<ProductionItem> getAllProductionItems();
    ProductionItem updateProductionItem(Long id, ProductionItem productionItem);
    void deleteProductionItem(Long id);
}
