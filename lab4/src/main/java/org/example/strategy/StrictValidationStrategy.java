package org.example.strategy;

import org.example.model.BudgetBreakdown;

import java.util.ArrayList;
import java.util.List;

/**
 * Strict validation strategy - enforces conservative financial rules.
 * Requires minimum savings, reasonable category allocations, and safety
 * margins.
 */
public class StrictValidationStrategy implements ValidationStrategy {

    private static final double MIN_SAVINGS_PERCENTAGE = 0.15; // 15% minimum savings
    private static final double MAX_HOUSING_PERCENTAGE = 0.30; // 30% max for housing
    private static final double MAX_ENTERTAINMENT_PERCENTAGE = 0.10; // 10% max for entertainment

    @Override
    public List<String> validate(BudgetBreakdown breakdown, double totalIncome) {
        List<String> issues = new ArrayList<>();

        double total = breakdown.getFood() + breakdown.getHousing() +
                breakdown.getEntertainment() + breakdown.getSavings();

        // Check total doesn't exceed income
        if (total > totalIncome) {
            issues.add("CRITICAL: Total budget ($" + String.format("%.2f", total) +
                    ") exceeds income ($" + String.format("%.2f", totalIncome) + ")");
        }

        // Check minimum savings
        double savingsPercentage = breakdown.getSavings() / totalIncome;
        if (savingsPercentage < MIN_SAVINGS_PERCENTAGE) {
            issues.add("ERROR: Savings ($" + String.format("%.2f", breakdown.getSavings()) +
                    ") is below required " + (MIN_SAVINGS_PERCENTAGE * 100) + "% of income. " +
                    "Minimum required: $" + String.format("%.2f", totalIncome * MIN_SAVINGS_PERCENTAGE));
        }

        // Check housing doesn't exceed limit
        double housingPercentage = breakdown.getHousing() / totalIncome;
        if (housingPercentage > MAX_HOUSING_PERCENTAGE) {
            issues.add("ERROR: Housing ($" + String.format("%.2f", breakdown.getHousing()) +
                    ") exceeds maximum " + (MAX_HOUSING_PERCENTAGE * 100) + "% of income. " +
                    "Maximum allowed: $" + String.format("%.2f", totalIncome * MAX_HOUSING_PERCENTAGE));
        }

        // Check entertainment doesn't exceed limit
        double entertainmentPercentage = breakdown.getEntertainment() / totalIncome;
        if (entertainmentPercentage > MAX_ENTERTAINMENT_PERCENTAGE) {
            issues.add("WARNING: Entertainment ($" + String.format("%.2f", breakdown.getEntertainment()) +
                    ") exceeds recommended " + (MAX_ENTERTAINMENT_PERCENTAGE * 100) + "% of income. " +
                    "Consider reducing to: $" + String.format("%.2f", totalIncome * MAX_ENTERTAINMENT_PERCENTAGE));
        }

        // Check for negative values
        if (breakdown.getFood() < 0 || breakdown.getHousing() < 0 ||
                breakdown.getEntertainment() < 0 || breakdown.getSavings() < 0) {
            issues.add("ERROR: Negative values detected in budget categories");
        }

        return issues;
    }

    @Override
    public String getStrategyName() {
        return "Strict Validation";
    }
}
