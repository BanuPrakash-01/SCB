package com.scb.ashrama.Service;

import com.scb.ashrama.Domain.Production;

import java.util.List;

public interface ProductionService {
    Production saveProduction(Production production);
    Production getProductionById(Long id);
    List<Production> getAllProductions();
    Production updateProduction(Long id, Production productionDetails);
    void deleteProduction(Long id);
}
