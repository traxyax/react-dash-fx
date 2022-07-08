package com.pultrax.reactdashfx.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void addNewSale(Sale sale) {
        saleRepository.save(sale);
    }

    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    public Long getNbAgent() {
        return saleRepository.countDistinctByAgentCode();
    }

    public Long getNbProduit() {
        return saleRepository.countDistinctByUnitPrice();
    }

    public Long getTurnover() {
        return saleRepository.sumUnitPriceXQuantity();
    }

    public void addNewSale(List<Sale> sales) {
        saleRepository.saveAll(sales);
    }
}
