package org.example.facade;

import org.example.model.BudgetBreakdown;
import org.example.model.ConstraintOutcome;
import org.example.singleton.BudgetConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleConsumer;

public class BudgetConstraintValidator {
    private final BudgetConfig config;

    public BudgetConstraintValidator(BudgetConfig config) {
        this.config = config;
    }

    public ConstraintOutcome applyConstraints(BudgetBreakdown breakdown) {
        Map<String, Double> limits = config.getAllLimits();
        List<String> notes = new ArrayList<>();
        BudgetBreakdown.Builder builder = breakdown.toBuilder();

        enforce("food", breakdown.getFood(), limits, builder::food, notes);
        enforce("housing", breakdown.getHousing(), limits, builder::housing, notes);
        enforce("entertainment", breakdown.getEntertainment(), limits, builder::entertainment, notes);
        enforce("savings", breakdown.getSavings(), limits, builder::savings, notes);

        breakdown.getCustomCategories().forEach((category, amount) -> {
            Double limit = limits.get(category.toLowerCase());
            if (limit != null && amount > limit) {
                builder.customCategory(category, limit);
                notes.add(String.format("Adjusted custom category '%s' from %.2f to limit %.2f", category, amount,
                        limit));
            }
        });

        return new ConstraintOutcome(builder.build(), notes);
    }

    private void enforce(String category,
            double amount,
            Map<String, Double> limits,
            DoubleConsumer setter,
            List<String> notes) {
        Double limit = limits.get(category.toLowerCase());
        if (limit != null && amount > limit) {
            setter.accept(limit);
            notes.add(String.format("Adjusted %s from %.2f to limit %.2f", category, amount, limit));
        }
    }
}
