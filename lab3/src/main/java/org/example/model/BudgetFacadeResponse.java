package org.example.model;

import org.example.adapter.Transaction;
import org.example.build.BudgetResult;

import java.util.Collections;
import java.util.List;

public final class BudgetFacadeResponse {
    private final BudgetResult budgetResult;
    private final BudgetBreakdown breakdown;
    private final List<String> constraintNotes;
    private final List<String> recommendations;
    private final String report;
    private final List<Transaction> transactions;

    public BudgetFacadeResponse(BudgetResult budgetResult,
            BudgetBreakdown breakdown,
            List<String> constraintNotes,
            List<String> recommendations,
            String report,
            List<Transaction> transactions) {
        this.budgetResult = budgetResult;
        this.breakdown = breakdown;
        this.constraintNotes = Collections.unmodifiableList(constraintNotes);
        this.recommendations = Collections.unmodifiableList(recommendations);
        this.report = report;
        this.transactions = Collections.unmodifiableList(transactions);
    }

    public BudgetResult getBudgetResult() {
        return budgetResult;
    }

    public BudgetBreakdown getBreakdown() {
        return breakdown;
    }

    public List<String> getConstraintNotes() {
        return constraintNotes;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public String getReport() {
        return report;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
