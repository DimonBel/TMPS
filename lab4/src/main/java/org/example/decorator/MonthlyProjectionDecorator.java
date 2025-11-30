package org.example.decorator;

import org.example.model.BudgetBreakdown;

public class MonthlyProjectionDecorator extends BudgetReportDecorator {
    private final BudgetBreakdown breakdown;

    public MonthlyProjectionDecorator(BudgetReport delegate, BudgetBreakdown breakdown) {
        super(delegate);
        this.breakdown = breakdown;
    }

    @Override
    public String render() {
        StringBuilder builder = new StringBuilder(delegate.render());
        builder.append("\n\nProjection:\n");
        builder.append(String.format("Projected annual savings: $%.2f\n", breakdown.getSavings() * 12));
        builder.append(String.format("Projected annual food spending: $%.2f\n", breakdown.getFood() * 12));
        builder.append(
                String.format("Projected annual entertainment spending: $%.2f", breakdown.getEntertainment() * 12));
        return builder.toString();
    }
}
