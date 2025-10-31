package org.example;

import org.example.builder.BudgetResult;
import org.example.factory.BudgetFactory;
import org.example.factory.BudgetPlan;
import org.example.factory.ConcreteBudgetFactory;
import org.example.singleton.BudgetConfig;

/**
 * Main class demonstrating the three design patterns in the Budgeting Service:
 * 1. Factory Method Pattern - for creating different budget plans
 * 2. Singleton Pattern - for global budget configuration
 * 3. Builder Pattern - for creating complex budget results
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Budgeting Service Design Patterns Demo ===\n");

        // 1. Factory Method Pattern Demo
        System.out.println("1. Factory Method Pattern Demo:");
        System.out.println("Creating different budget plans using the factory...\n");

        BudgetFactory factory = new ConcreteBudgetFactory();

        BudgetPlan conservativeBudget = factory.create("conservative");
        System.out.println(conservativeBudget.allocate());

        System.out.println("\n----------------------------------------\n");

        BudgetPlan aggressiveBudget = factory.create("aggressive");
        System.out.println(aggressiveBudget.allocate());

        System.out.println("\n----------------------------------------\n");

        // 2. Singleton Pattern Demo
        System.out.println("2. Singleton Pattern Demo:");
        System.out.println("Accessing global budget configuration...\n");

        BudgetConfig config1 = BudgetConfig.get();
        System.out.println("Default category limits:");
        config1.getAllLimits().forEach((category, limit) -> System.out.println(category + ": $" + limit));

        // Modify configuration
        config1.setLimit("food", 1500.0);
        config1.setLimit("entertainment", 750.0);

        System.out.println("\nModified category limits:");
        config1.getAllLimits().forEach((category, limit) -> System.out.println(category + ": $" + limit));

        // Verify singleton instance
        BudgetConfig config2 = BudgetConfig.get();
        System.out.println("\nAre config1 and config2 the same instance? " +
                (config1 == config2));

        System.out.println("\n----------------------------------------\n");

        // 3. Builder Pattern Demo
        System.out.println("3. Builder Pattern Demo:");
        System.out.println("Creating complex budget results using the builder...\n");

        BudgetResult budget = new BudgetResult.Builder()
                .setFood(1200.0)
                .setHousing(1800.0)
                .setEntertainment(600.0)
                .setSavings(1500.0)
                .addCustomCategory("Transportation", 400.0)
                .addCustomCategory("Healthcare", 300.0)
                .build();

        System.out.println(budget.toString());
        System.out.println("\nTotal budget: $" + budget.getTotal());

        System.out.println("\n----------------------------------------\n");

        // Combined Demo
        System.out.println("4. Combined Demo - All Patterns Working Together:");
        System.out.println("Creating a budget plan and using configuration to build a result...\n");

        // Create a budget plan using factory
        BudgetPlan budgetPlan = factory.create("conservative");
        System.out.println("Budget Plan Created:");
        System.out.println(budgetPlan.allocate());

        // Get configuration
        BudgetConfig config = BudgetConfig.get();
        System.out.println("\nUsing Configuration Limits:");
        System.out.println("Food limit: $" + config.getLimit("food"));
        System.out.println("Housing limit: $" + config.getLimit("housing"));

        // Build a budget result within limits
        BudgetResult customBudget = new BudgetResult.Builder()
                .setFood(Math.min(1500.0, config.getLimit("food")))
                .setHousing(Math.min(2000.0, config.getLimit("housing")))
                .setEntertainment(400.0)
                .setSavings(1000.0)
                .build();

        System.out.println("\nCustom Budget Result (respecting limits):");
        System.out.println(customBudget);
        System.out.println("Total: $" + customBudget.getTotal());
    }
}
