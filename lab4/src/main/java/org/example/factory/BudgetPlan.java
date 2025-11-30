package org.example.factory;

import org.example.model.BudgetBreakdown;

// contract budget allocation
public interface BudgetPlan {

    String allocate();

    BudgetBreakdown getBreakdown();
}
