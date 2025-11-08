package org.example.factory;


public abstract class BudgetFactory {
    public abstract BudgetPlan create(String type);
}
