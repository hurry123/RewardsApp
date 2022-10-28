package com.customer.rewards.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Model to maintain purchase data for a customer.
 */
public class Purchase {

    private final String customerId;

    private final String purchaseId;

    private final LocalDate purchaseDate;

    private final BigDecimal purchaseAmount;

    /**
     * Initialize the constructor with the given params.
     * @param customerId the customer id
     * @param purchaseId the purchase id
     * @param purchaseDate the purchase date
     * @param purchaseAmount the purchase amount
     */
    public Purchase(String customerId, String purchaseId, LocalDate purchaseDate, BigDecimal purchaseAmount) {
        this.customerId = customerId;
        this.purchaseId = purchaseId;
        this.purchaseDate = purchaseDate;
        this.purchaseAmount = purchaseAmount;
    }

    /**
     * Gets the customer id.
     * @return the customer id string
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Gets the purchase id.
     * @return the purchase id string
     */
    public String getPurchaseId() {
        return purchaseId;
    }

    /**
     * Gets the purchase date.
     * @return the purchase date string
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Gets the purchase amount.
     * @return the purchase amount string
     */
    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }
}
