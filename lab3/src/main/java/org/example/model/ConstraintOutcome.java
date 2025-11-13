package org.example.model;

import java.util.Collections;
import java.util.List;

public final class ConstraintOutcome {
    private final BudgetBreakdown breakdown;
    private final List<String> notes;

    public ConstraintOutcome(BudgetBreakdown breakdown, List<String> notes) {
        this.breakdown = breakdown;
        this.notes = Collections.unmodifiableList(notes);
    }

    public BudgetBreakdown getBreakdown() {
        return breakdown;
    }

    public List<String> getNotes() {
        return notes;
    }
}
