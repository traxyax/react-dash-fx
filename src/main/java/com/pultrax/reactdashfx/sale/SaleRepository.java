package com.pultrax.reactdashfx.sale;

import com.pultrax.reactdashfx.sale.interfaces.ISaleCountByUnitPriceXQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(
            "SELECT COUNT(DISTINCT agentCode) FROM Sale"
    )
    Long countDistinctByAgentCode();

    @Query(
            "SELECT COUNT(DISTINCT unitPrice) FROM Sale"
    )
    Long countDistinctByUnitPrice();

    @Query(
            "SELECT SUM(unitPrice * quantity) FROM Sale"
    )
    Long sumUnitPriceXQuantity();

    @Query(
            "SELECT unitPrice AS unitPrice, SUM(unitPrice * quantity) AS totalAmount FROM Sale"
            + " GROUP BY unitPrice"
    )
    List<ISaleCountByUnitPriceXQuantity> findSaleByUnitPriceXQuantity();
}
