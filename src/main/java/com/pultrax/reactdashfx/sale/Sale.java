package com.pultrax.reactdashfx.sale;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Sale")
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale",
            nullable = false
    )
    private Long id;

    @Column(name = "date",
            nullable = false
    )
    private LocalDate date;

    @Column(name = "agent_code",
            nullable = false
    )
    private int agentCode;

    @Transient
    private String productCode;

    @Column(name = "unit_price",
            nullable = false
    )
    private int unitPrice;

    @Column(name = "quantity",
            nullable = false
    )
    private int quantity;

    @Transient
    private int amount;

    protected Sale() {

    }

    public Sale(LocalDate date, int agentCode, int unitPrice, int quantity) {
        this.date = date;
        this.agentCode = agentCode;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(int agentCode) {
        this.agentCode = agentCode;
    }

    public String getProductCode() {
        return "AB" + String.valueOf(unitPrice);
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return unitPrice * quantity;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", date=" + date +
                ", agentCode=" + agentCode +
                ", productCode='" + getProductCode() + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", amount=" + getAmount() +
                '}';
    }
}
