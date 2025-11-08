package org.example.factory;

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
        double food = totalIncome * 0.20;
        double housing = totalIncome * 0.30;
        double entertainment = totalIncome * 0.10;
        double savings = totalIncome * 0.40;

        return String.format("Aggressive Savings Budget Allocation:\n" +
                "Food: $%.2f\n" +
                "Housing: $%.2f\n" +
                "Entertainment: $%.2f\n" +
                "Savings: $%.2f\n" +
                "Total: $%.2f",
                food, housing, entertainment, savings, totalIncome);
    }
}
