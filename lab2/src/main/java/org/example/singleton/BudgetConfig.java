package org.example.singleton;

import java.util.HashMap;
import java.util.Map;


public class BudgetConfig {
    private static final BudgetConfig instance = new BudgetConfig();

    private Map<String, Double> limits;

//    to prevent instantiation from outside.

    private BudgetConfig() {
        limits = new HashMap<>();
        // Set default category limits
        limits.put("food", 1000.0);
        limits.put("housing", 2000.0);
        limits.put("entertainment", 500.0);
        limits.put("savings", 1000.0);
    }

    //Returns the singleton instance of BudgetConfig.
    public static BudgetConfig get() {
        return instance;
    }

    //Gets the limit for a specific category.
    public Double getLimit(String category) {
        return limits.get(category.toLowerCase());
    }

    public void setLimit(String category, Double limit) {
        limits.put(category.toLowerCase(), limit);
    }

    public Map<String, Double> getAllLimits() {
        return new HashMap<>(limits);
    }
}
