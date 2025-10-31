package org.example.factory;

/**
 * ConcreteBudgetFactory implements the BudgetFactory to create specific budget
 * plans.
 * This class is part of the Factory Method pattern implementation.
 */
public class ConcreteBudgetFactory extends BudgetFactory {

    @Override
    public BudgetPlan create(String type) {
        switch (type.toLowerCase()) {
            case "conservative":
                return new ConservativeBudget(5000.0); // Default income
            case "aggressive":
                return new AggressiveSavingsBudget(5000.0); // Default income
            default:
                throw new IllegalArgumentException("Unknown budget type: " + type);
        }
    }
}
