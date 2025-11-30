package org.example.factory;

import org.example.model.BudgetBreakdown;

//conservative budget allocation strategy.
public class ConservativeBudget implements BudgetPlan {
    private double totalIncome;

    public ConservativeBudget(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String allocate() {
        BudgetBreakdown breakdown = getBreakdown();

        return String.format("Conservative Budget Allocation:\n" +
                "Food: $%.2f\n" +
                "Housing: $%.2f\n" +
                "Entertainment: $%.2f\n" +
                "Savings: $%.2f\n" +
                "Total: $%.2f",
                breakdown.getFood(), breakdown.getHousing(), breakdown.getEntertainment(),
                breakdown.getSavings(), breakdown.getTotal());
    }

    @Override
    public BudgetBreakdown getBreakdown() {
        return BudgetBreakdown.builder()
                .food(totalIncome * 0.25)
                .housing(totalIncome * 0.35)
                .entertainment(totalIncome * 0.15)
                .savings(totalIncome * 0.25)
                .build();
    }
}
