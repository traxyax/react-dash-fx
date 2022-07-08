package com.pultrax.reactdashfx.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
