package org.example.strategy;

import org.example.model.BudgetBreakdown;

import java.util.ArrayList;
import java.util.List;

/**
 * Flexible validation strategy - provides warnings but allows more freedom.
 * Suitable for users who understand their financial situation and prefer
 * flexibility.
 */
public class FlexibleValidationStrategy implements ValidationStrategy {

    private static final double SUGGESTED_SAVINGS_PERCENTAGE = 0.10; // 10% suggested savings
    private static final double HIGH_SPENDING_THRESHOLD = 0.95; // 95% of income

    @Override
    public List<String> validate(BudgetBreakdown breakdown, double totalIncome) {
        List<String> issues = new ArrayList<>();

        double total = breakdown.getFood() + breakdown.getHousing() +
                breakdown.getEntertainment() + breakdown.getSavings();

        // Only hard error if significantly over budget
        if (total > totalIncome * 1.05) { // Allow 5% overdraft
            issues.add("WARNING: Total budget ($" + String.format("%.2f", total) +
                    ") significantly exceeds income ($" + String.format("%.2f", totalIncome) + ")");
        }

        // Suggest savings if too low
        double savingsPercentage = breakdown.getSavings() / totalIncome;
        if (savingsPercentage < SUGGESTED_SAVINGS_PERCENTAGE) {
            issues.add("INFO: Consider increasing savings. Current: $" +
                    String.format("%.2f", breakdown.getSavings()) +
                    " (Suggested minimum: $" +
                    String.format("%.2f", totalIncome * SUGGESTED_SAVINGS_PERCENTAGE) + ")");
        }

        // Warn about high total spending
        double spendingPercentage = total / totalIncome;
        if (spendingPercentage > HIGH_SPENDING_THRESHOLD) {
            issues.add("INFO: You're allocating " + String.format("%.1f", spendingPercentage * 100) +
                    "% of your income. Consider leaving some buffer for unexpected expenses.");
        }

        // Check for extremely unbalanced allocations
        if (breakdown.getHousing() > totalIncome * 0.50) {
            issues.add("INFO: Housing costs are quite high (" +
                    String.format("%.1f", (breakdown.getHousing() / totalIncome) * 100) +
                    "% of income). This may limit financial flexibility.");
        }

        if (breakdown.getEntertainment() > totalIncome * 0.30) {
            issues.add("INFO: Entertainment spending is notably high (" +
                    String.format("%.1f", (breakdown.getEntertainment() / totalIncome) * 100) +
                    "% of income).");
        }

        return issues;
    }

    @Override
    public String getStrategyName() {
        return "Flexible Validation";
    }
}
