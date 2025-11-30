package org.example.strategy;

import org.example.model.BudgetBreakdown;

import java.util.List;

/**
 * Context class for Strategy Pattern.
 * Allows switching between different validation strategies at runtime.
 */
public class BudgetValidator {

    private ValidationStrategy strategy;

    public BudgetValidator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Change the validation strategy at runtime.
     */
    public void setStrategy(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Validate budget using the current strategy.
     */
    public List<String> validate(BudgetBreakdown breakdown, double totalIncome) {
        return strategy.validate(breakdown, totalIncome);
    }

    /**
     * Get the name of the current strategy.
     */
    public String getCurrentStrategyName() {
        return strategy.getStrategyName();
    }
}
