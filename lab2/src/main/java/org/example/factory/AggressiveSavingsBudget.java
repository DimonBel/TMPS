package org.example.factory;

/**
 * AggressiveSavingsBudget implements an aggressive savings budget allocation
 * strategy.
 * This class is part of the Factory Method pattern implementation.
 */
public class AggressiveSavingsBudget implements BudgetPlan {
    private double totalIncome;

    public AggressiveSavingsBudget(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String allocate() {
        double food = totalIncome * 0.20; // 20% for food
        double housing = totalIncome * 0.30; // 30% for housing
        double entertainment = totalIncome * 0.10; // 10% for entertainment
        double savings = totalIncome * 0.40; // 40% for savings

        return String.format("Aggressive Savings Budget Allocation:\n" +
                "Food: $%.2f\n" +
                "Housing: $%.2f\n" +
                "Entertainment: $%.2f\n" +
                "Savings: $%.2f\n" +
                "Total: $%.2f",
                food, housing, entertainment, savings, totalIncome);
    }
}
