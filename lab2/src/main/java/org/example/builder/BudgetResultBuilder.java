package org.example.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder class for constructing BudgetResult instances.
 * This class implements the Builder pattern for creating complex budget results
 * with different categories, priorities, and constraints.
 */
public class BudgetResultBuilder {
    // Delegate to the new inner Builder of BudgetResult to avoid accessing private
    // fields
    private BudgetResult.Builder builder;

    /**
     * Constructs a new BudgetResultBuilder.
     */
    public BudgetResultBuilder() {
        builder = new BudgetResult.Builder();
    }

    /**
     * Sets the food budget amount.
     * 
     * @param amount The food budget amount
     * @return This builder for method chaining
     */
    public BudgetResultBuilder setFood(double amount) {
        builder.setFood(amount);
        return this;
    }

    /**
     * Sets the housing budget amount.
     * 
     * @param amount The housing budget amount
     * @return This builder for method chaining
     */
    public BudgetResultBuilder setHousing(double amount) {
        builder.setHousing(amount);
        return this;
    }

    /**
     * Sets the entertainment budget amount.
     * 
     * @param amount The entertainment budget amount
     * @return This builder for method chaining
     */
    public BudgetResultBuilder setEntertainment(double amount) {
        builder.setEntertainment(amount);
        return this;
    }

    /**
     * Sets the savings budget amount.
     * 
     * @param amount The savings budget amount
     * @return This builder for method chaining
     */
    public BudgetResultBuilder setSavings(double amount) {
        builder.setSavings(amount);
        return this;
    }

    /**
     * Adds a custom category with a budget amount.
     * 
     * @param category The category name
     * @param amount   The budget amount
     * @return This builder for method chaining
     */
    public BudgetResultBuilder addCustomCategory(String category, double amount) {
        builder.addCustomCategory(category, amount);
        return this;
    }

    /**
     * Builds and returns the BudgetResult instance.
     * 
     * @return The constructed BudgetResult
     */
    public BudgetResult build() {
        return builder.build();
    }
}
