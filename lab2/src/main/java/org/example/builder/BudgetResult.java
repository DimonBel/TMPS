package org.example.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * BudgetResult represents the result of a budget allocation.
 * This class works with BudgetResultBuilder to implement the Builder pattern
 * for creating complex budget results with different categories, priorities,
 * and constraints.
 */
public class BudgetResult {
    private double food;
    private double housing;
    private double entertainment;
    private double savings;
    private Map<String, Double> customCategories;

    /**
     * Package-private constructor to be used by the BudgetResultBuilder.
     */
    BudgetResult() {
        this.customCategories = new HashMap<>();
    }

    // Getters for all budget categories

    public double getFood() {
        return food;
    }

    public double getHousing() {
        return housing;
    }

    public double getEntertainment() {
        return entertainment;
    }

    public double getSavings() {
        return savings;
    }

    public Map<String, Double> getCustomCategories() {
        return new HashMap<>(customCategories);
    }

    /**
     * Calculates the total budget amount.
     * 
     * @return The total budget amount
     */
    public double getTotal() {
        double total = food + housing + entertainment + savings;
        for (double amount : customCategories.values()) {
            total += amount;
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Budget Result:\n");
        sb.append(String.format("Food: $%.2f\n", food));
        sb.append(String.format("Housing: $%.2f\n", housing));
        sb.append(String.format("Entertainment: $%.2f\n", entertainment));
        sb.append(String.format("Savings: $%.2f\n", savings));

        if (!customCategories.isEmpty()) {
            sb.append("Custom Categories:\n");
            for (Map.Entry<String, Double> entry : customCategories.entrySet()) {
                sb.append(String.format("  %s: $%.2f\n", entry.getKey(), entry.getValue()));
            }
        }

        sb.append(String.format("Total: $%.2f", getTotal()));
        return sb.toString();
    }

    /**
     * Public static Builder so callers can use new BudgetResult.Builder()
     * The inner builder has access to the private fields of BudgetResult.
     */
    public static class Builder {
        private final BudgetResult instance;

        public Builder() {
            instance = new BudgetResult();
        }

        public Builder setFood(double amount) {
            instance.food = amount;
            return this;
        }

        public Builder setHousing(double amount) {
            instance.housing = amount;
            return this;
        }

        public Builder setEntertainment(double amount) {
            instance.entertainment = amount;
            return this;
        }

        public Builder setSavings(double amount) {
            instance.savings = amount;
            return this;
        }

        public Builder addCustomCategory(String category, double amount) {
            instance.customCategories.put(category, amount);
            return this;
        }

        public BudgetResult build() {
            return instance;
        }
    }
}
