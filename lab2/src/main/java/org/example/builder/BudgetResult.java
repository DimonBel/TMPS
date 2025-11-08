package org.example.builder;

import java.util.HashMap;
import java.util.Map;


public class BudgetResult {
    private double food;
    private double housing;
    private double entertainment;
    private double savings;
    private Map<String, Double> customCategories;


    BudgetResult() {
        this.customCategories = new HashMap<>();
    }

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

    //total budget amount.
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

    //inner builder has access fields of BudgetResult.
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
