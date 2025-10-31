package org.example.factory;

/**
 * ConservativeBudget implements a conservative budget allocation strategy.
 * This class is part of the Factory Method pattern implementation.
 */
public class ConservativeBudget implements BudgetPlan {
    private double totalIncome;

    public ConservativeBudget(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String allocate() {
        double food = totalIncome * 0.25; // 25% for food
        double housing = totalIncome * 0.35; // 35% for housing
        double entertainment = totalIncome * 0.15; // 15% for entertainment
        double savings = totalIncome * 0.25; // 25% for savings

        return String.format("Conservative Budget Allocation:\n" +
                "Food: $%.2f\n" +
                "Housing: $%.2f\n" +
                "Entertainment: $%.2f\n" +
                "Savings: $%.2f\n" +
                "Total: $%.2f",
                food, housing, entertainment, savings, totalIncome);
    }
}
