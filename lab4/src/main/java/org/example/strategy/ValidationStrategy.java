package org.example.strategy;

import org.example.model.BudgetBreakdown;

import java.util.List;

/**
 * Strategy Pattern: Interface for different budget validation strategies.
 * Allows changing validation behavior at runtime.
 */
public interface ValidationStrategy {

    /**
     * Validates the budget breakdown according to specific rules.
     * 
     * @param breakdown   The budget breakdown to validate
     * @param totalIncome The total income for comparison
     * @return List of validation messages (empty if valid)
     */
    List<String> validate(BudgetBreakdown breakdown, double totalIncome);

    /**
     * Returns the name of this validation strategy.
     */
    String getStrategyName();
}
