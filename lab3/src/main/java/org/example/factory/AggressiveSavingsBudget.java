package org.example.factory;

import org.example.model.BudgetBreakdown;

/* AggressiveSavingsBudget implements an aggressive savings budget allocation
 * strategy.
 */
public class AggressiveSavingsBudget implements BudgetPlan {
    private double totalIncome;

    public AggressiveSavingsBudget(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String allocate() {
        BudgetBreakdown breakdown = getBreakdown();

        return String.format("Aggressive Savings Budget Allocation:\n" +
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
                .food(totalIncome * 0.20)
                .housing(totalIncome * 0.30)
                .entertainment(totalIncome * 0.10)
                .savings(totalIncome * 0.40)
                .build();
    }
}
