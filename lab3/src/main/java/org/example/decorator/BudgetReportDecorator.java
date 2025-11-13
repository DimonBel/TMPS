package org.example.decorator;

public abstract class BudgetReportDecorator implements BudgetReport {
    protected final BudgetReport delegate;

    protected BudgetReportDecorator(BudgetReport delegate) {
        this.delegate = delegate;
    }
}
