package org.example.factory;


//conservative budget allocation strategy.
public class ConservativeBudget implements BudgetPlan {
    private double totalIncome;

    public ConservativeBudget(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String allocate() {
        double food = totalIncome * 0.25;
        double housing = totalIncome * 0.35;
        double entertainment = totalIncome * 0.15;
        double savings = totalIncome * 0.25;

        return String.format("Conservative Budget Allocation:\n" +
                "Food: $%.2f\n" +
                "Housing: $%.2f\n" +
                "Entertainment: $%.2f\n" +
                "Savings: $%.2f\n" +
                "Total: $%.2f",
                food, housing, entertainment, savings, totalIncome);
    }
}
