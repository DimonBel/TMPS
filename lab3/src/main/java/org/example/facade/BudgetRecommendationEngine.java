package org.example.facade;

import org.example.adapter.Transaction;
import org.example.model.BudgetBreakdown;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetRecommendationEngine {

    public List<String> generate(BudgetBreakdown breakdown,
            List<Transaction> transactions,
            List<String> constraintNotes) {
        List<String> recommendations = new ArrayList<>();
        Map<String, Double> totals = aggregate(transactions);

        evaluateCategory("food", breakdown.getFood(), totals, recommendations);
        evaluateCategory("housing", breakdown.getHousing(), totals, recommendations);
        evaluateCategory("entertainment", breakdown.getEntertainment(), totals, recommendations);
        evaluateCategory("savings", breakdown.getSavings(), totals, recommendations);

        if (!constraintNotes.isEmpty()) {
            recommendations.addAll(constraintNotes);
        }

        boolean hasSavingsPositive = transactions.stream()
                .anyMatch(tx -> tx.category().equalsIgnoreCase("savings") && tx.amount() > 0);
        if (!hasSavingsPositive) {
            recommendations.add("Consider scheduling an automatic transfer to savings to stay on track.");
        }

        return recommendations;
    }

    private Map<String, Double> aggregate(List<Transaction> transactions) {
        Map<String, Double> totals = new HashMap<>();
        for (Transaction transaction : transactions) {
            String category = transaction.category().toLowerCase();
            double amount = transaction.amount();
            totals.merge(category, amount, Double::sum);
        }
        return totals;
    }

    private void evaluateCategory(String category,
            double planned,
            Map<String, Double> totals,
            List<String> recommendations) {
        double spent = -totals.getOrDefault(category.toLowerCase(), 0.0);
        if (spent > planned) {
            recommendations.add(String.format("Spending in %s exceeds plan by %.2f", category, spent - planned));
        }
    }
}
