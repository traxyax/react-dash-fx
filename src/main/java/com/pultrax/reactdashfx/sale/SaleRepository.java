package com.pultrax.reactdashfx.sale;

import com.pultrax.reactdashfx.sale.interfaces.ISaleCountByUnitPriceXQuantity;
import com.pultrax.reactdashfx.sale.interfaces.ISaleTotalAmountByAgentCodeAndYear;
import com.pultrax.reactdashfx.sale.interfaces.ISumUnitPriceXQuantitySaleByDate;
import com.pultrax.reactdashfx.sale.interfaces.IUnitPriceAndTotalQuantityByDateAndUnitPrice;
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
            "SELECT DISTINCT unitPrice AS unitPrice FROM Sale"
    )
    List<String> findDistinctUnitPrice();

    @Query(
            "SELECT SUM(unitPrice * quantity) FROM Sale"
    )
    Long sumUnitPriceXQuantity();

    @Query(
            "SELECT unitPrice AS unitPrice, SUM(unitPrice * quantity) AS totalAmount FROM Sale"
            + " GROUP BY unitPrice"
    )
    List<ISaleCountByUnitPriceXQuantity> findSaleByUnitPriceXQuantity();

    @Query("SELECT SUM(unitPrice * quantity) AS totalAmount, date AS date FROM Sale " +
            "GROUP BY date ORDER BY date")
    List<ISumUnitPriceXQuantitySaleByDate> findTotalAmountSaleByDate();

    @Query("SELECT agentCode AS agentCode, SUM(unitPrice * quantity) AS totalAmount " +
            "FROM Sale WHERE DATE_FORMAT(date, '%Y') LIKE ?1 " +
            "GROUP BY agentCode")
    List<ISaleTotalAmountByAgentCodeAndYear> findSaleTotalAmountByAgentCodeAndYear(String year);

    @Query("SELECT DISTINCT DATE_FORMAT(date, '%Y') AS years FROM Sale")
    List<String> findDistinctYear();

    @Query("SELECT unitPrice AS unitPrice, SUM(quantity) AS totalQuantity, date AS date " +
            "FROM Sale " +
            "GROUP BY date, unitPrice")
    List<IUnitPriceAndTotalQuantityByDateAndUnitPrice> findUnitPriceAndTotalQuantityByDateAndUnitPrice();
}
