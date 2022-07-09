package com.pultrax.reactdashfx.sale;

import com.pultrax.reactdashfx.sale.interfaces.ISaleCountByUnitPriceXQuantity;
import com.pultrax.reactdashfx.sale.interfaces.ISaleTotalAmountByAgentCodeAndYear;
import com.pultrax.reactdashfx.sale.interfaces.ISumUnitPriceXQuantitySaleByDate;
import com.pultrax.reactdashfx.sale.interfaces.IUnitPriceAndTotalQuantityByDateAndUnitPrice;
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

    public List<ISaleCountByUnitPriceXQuantity> getPieChartData() {
        return saleRepository.findSaleByUnitPriceXQuantity();
    }

    public List<ISumUnitPriceXQuantitySaleByDate> getLineChartData() {
        return saleRepository.findTotalAmountSaleByDate();
    }

    public List<ISaleTotalAmountByAgentCodeAndYear> getBarChartData(String year) {
        return saleRepository.findSaleTotalAmountByAgentCodeAndYear(year);
    }

    public List<IUnitPriceAndTotalQuantityByDateAndUnitPrice> getAreaChartData(){
        return saleRepository.findUnitPriceAndTotalQuantityByDateAndUnitPrice();
    }

    public List<String> getUnitPrice() {
        return saleRepository.findDistinctUnitPrice();
    }

    public List<String> getSaleYears() {
        return saleRepository.findDistinctYear();
    }

    public void addNewSale(List<Sale> sales) {
        saleRepository.saveAll(sales);
    }
}
