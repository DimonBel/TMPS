package org.example.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * BudgetConfig provides global configuration for budget categories.
 * This class implements the Singleton pattern to ensure only one instance
 * exists.
 * It contains category limits and other budget-related settings.
 */
public class BudgetConfig {
    private static final BudgetConfig instance = new BudgetConfig();

    private Map<String, Double> limits;

    /**
     * Private constructor to prevent instantiation from outside.
     */
    private BudgetConfig() {
        limits = new HashMap<>();
        // Set default category limits
        limits.put("food", 1000.0);
        limits.put("housing", 2000.0);
        limits.put("entertainment", 500.0);
        limits.put("savings", 1000.0);
    }

    /**
     * Returns the singleton instance of BudgetConfig.
     * 
     * @return The singleton BudgetConfig instance
     */
    public static BudgetConfig get() {
        return instance;
    }

    /**
     * Gets the limit for a specific category.
     * 
     * @param category The category name
     * @return The limit for the category
     */
    public Double getLimit(String category) {
        return limits.get(category.toLowerCase());
    }

    /**
     * Sets the limit for a specific category.
     * 
     * @param category The category name
     * @param limit    The new limit value
     */
    public void setLimit(String category, Double limit) {
        limits.put(category.toLowerCase(), limit);
    }

    /**
     * Returns all category limits.
     * 
     * @return A map of category names to their limits
     */
    public Map<String, Double> getAllLimits() {
        return new HashMap<>(limits);
    }
}
