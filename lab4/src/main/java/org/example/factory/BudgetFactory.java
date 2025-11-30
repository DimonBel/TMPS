package org.example.factory;

public abstract class BudgetFactory {
    public abstract BudgetPlan create(String type, double totalIncome);

    public BudgetPlan create(String type) {
        return create(type, 5000.0);
    }
}
