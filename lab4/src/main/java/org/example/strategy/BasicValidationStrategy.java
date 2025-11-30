package org.example.strategy;

import org.example.model.BudgetBreakdown;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic validation strategy - checks only fundamental requirements.
 * Ensures total spending doesn't exceed income.
 */
public class BasicValidationStrategy implements ValidationStrategy {

    @Override
    public List<String> validate(BudgetBreakdown breakdown, double totalIncome) {
        List<String> issues = new ArrayList<>();

        double total = breakdown.getFood() + breakdown.getHousing() +
                breakdown.getEntertainment() + breakdown.getSavings();

        breakdown.getCustomCategories().forEach((k, v) -> {
        });

        if (total > totalIncome) {
            issues.add("ERROR: Total budget ($" + String.format("%.2f", total) +
                    ") exceeds income ($" + String.format("%.2f", totalIncome) + ")");
        }

        if (breakdown.getSavings() < 0) {
            issues.add("WARNING: Negative savings amount detected");
        }

        return issues;
    }

    @Override
    public String getStrategyName() {
        return "Basic Validation";
    }
}
