package org.example.factory;

/**
 * Abstract BudgetFactory class for creating different types of budget plans.
 * This class is part of the Factory Method pattern implementation.
 */
public abstract class BudgetFactory {
    /**
     * Creates a BudgetPlan of the specified type.
     * 
     * @param type The type of budget plan to create
     * @return A BudgetPlan instance
     */
    public abstract BudgetPlan create(String type);
}
