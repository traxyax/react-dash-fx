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

    public void addNewSale(List<Sale> sales) {
        saleRepository.saveAll(sales);
    }
}
