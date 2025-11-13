package org.example.decorator;

import org.example.build.BudgetResult;

public class PlainBudgetReport implements BudgetReport {
    private final BudgetResult budgetResult;

    public PlainBudgetReport(BudgetResult budgetResult) {
        this.budgetResult = budgetResult;
    }

    @Override
    public String render() {
        return budgetResult.toString();
    }
}
