package com.scb.ashrama.Service.impl;

import com.scb.ashrama.Domain.Production;
import com.scb.ashrama.Exception.ResourceNotFoundException;
import com.scb.ashrama.Repository.ProductionRepository;
import com.scb.ashrama.Service.ProductionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductionServiceImpl implements ProductionService {

    private final ProductionRepository productionRepository;

    public ProductionServiceImpl(ProductionRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    @Override
    public Production saveProduction(Production production) {
        log.info("Saving new production: {}", production);
        Production savedProduction = productionRepository.save(production);
        log.info("New production saved with ID: {}", savedProduction.getId());
        return savedProduction;
    }

    @Override
    public Production getProductionById(Long id) {
        log.info("Fetching production with ID: {}", id);
        Production production = productionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Production not found with ID: {}", id);
                    return new ResourceNotFoundException("Production not found with id: " + id);
                });
        log.info("Fetched production: {}", production);
        return production;
    }

    @Override
    public List<Production> getAllProductions() {
        log.info("Fetching all productions");
        List<Production> productions = productionRepository.findAll();
        log.info("Fetched {} productions", productions.size());
        return productions;
    }

    @Override
    public Production updateProduction(Long id, Production productionDetails) {
        log.info("Updating production with ID: {}", id);
        Production existingProduction = getProductionById(id);
        existingProduction.setName(productionDetails.getName());
        existingProduction.setDescription(productionDetails.getDescription());
        existingProduction.setUpdatedBy(productionDetails.getUpdatedBy());
        Production updatedProduction = productionRepository.save(existingProduction);
        log.info("Updated production with ID: {}", updatedProduction.getId());
        return updatedProduction;
    }

    @Override
    public void deleteProduction(Long id) {
        log.info("Request to delete production with ID: {}", id);
        Production existingProduction = getProductionById(id);
        productionRepository.delete(existingProduction);
        log.info("Deleted production with ID: {}", id);
    }
}
