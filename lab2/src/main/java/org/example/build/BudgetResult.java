package org.example.build;

import java.util.HashMap;
import java.util.Map;

// ---------- Product ----------
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

    // Setters (package-private, used by builder)
    void setFood(double amount) { this.food = amount; }
    void setHousing(double amount) { this.housing = amount; }
    void setEntertainment(double amount) { this.entertainment = amount; }
    void setSavings(double amount) { this.savings = amount; }
    void addCustomCategory(String category, double amount) { this.customCategories.put(category, amount); }
}
