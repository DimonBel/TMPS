package org.example.factory;

/**
 * BudgetPlan interface defines the contract for different budget allocation
 * strategies.
 * This interface is part of the Factory Method pattern implementation.
 */
public interface BudgetPlan {
    /**
     * Allocates funds according to the specific budget strategy.
     * 
     * @return A string describing the allocation result
     */
    String allocate();
}
