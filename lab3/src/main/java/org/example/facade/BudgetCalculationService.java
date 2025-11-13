package org.example.facade;

import org.example.factory.BudgetFactory;
import org.example.factory.BudgetPlan;
import org.example.model.BudgetBreakdown;
import org.example.model.BudgetUserInput;

public class BudgetCalculationService {
    private final BudgetFactory budgetFactory;

    public BudgetCalculationService(BudgetFactory budgetFactory) {
        this.budgetFactory = budgetFactory;
    }

    public BudgetBreakdown calculate(BudgetUserInput input) {
        BudgetPlan plan = budgetFactory.create(input.getBudgetType(), input.getMonthlyIncome());
        return plan.getBreakdown();
    }
}
