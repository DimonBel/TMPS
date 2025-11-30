package org.example.observer;

/**
 * Enum representing different types of budget events.
 */
public enum BudgetEvent {
    BUDGET_CREATED("Budget Created"),
    BUDGET_UPDATED("Budget Updated"),
    BUDGET_EXCEEDED("Budget Exceeded"),
    SAVINGS_GOAL_REACHED("Savings Goal Reached"),
    VALIDATION_FAILED("Validation Failed"),
    APPROVAL_REQUESTED("Approval Requested"),
    APPROVAL_GRANTED("Approval Granted"),
    APPROVAL_DENIED("Approval Denied");

    private final String description;

    BudgetEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
